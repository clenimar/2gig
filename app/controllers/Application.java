package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Ad;
import models.Address;
import models.Contact;
import models.Instrument;
import org.apache.commons.codec.digest.DigestUtils;
import play.libs.Json;
import play.mvc.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import models.dao.GenericDAO;

public class Application extends Controller {

    private static final GenericDAO db = new GenericDAO();

    public Result index() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("name", "Clenimar");
        map.put("age", "18");
        map.put("motto", "Lifes good");

        return ok(Json.toJson(map));
    }

    /**
     * Return all the advertisements
     */
    @play.db.jpa.Transactional
    public Result getAds() {
        List<Ad> queryResult = db.findAllByClass(Ad.class);
        HashMap<String, Ad> map = new HashMap<>();
        if (queryResult == null) {
            return ok(Json.toJson(map));
        }
        for (Ad ad : queryResult) {
            map.put(String.valueOf(ad.getId()), ad);
        }
        return ok(Json.toJson(map));
    }

    /**
     * Create a new advertisement
     */
    @play.db.jpa.Transactional
    public Result createAds() {
        JsonNode json = request().body().asJson();

        if (json == null)
            return badRequest();

        String author = json.get("author").asText();
        String title = json.get("title").asText();
        String description = json.get("description").asText();

        Address address = new Address(
                json.get("street").asText(),
                json.get("number").asText(),
                json.get("neighbourhood").asText(),
                json.get("city").asText(),
                json.get("state").asText(),
                json.get("country").asText()
        );

        Contact contact = new Contact(
                json.get("email").asText(),
                json.get("phone1").asText()
        );

        if (json.has("facebook_url"))
            contact.setFacebookUrl(json.get("facebook_url").asText());
        if (json.has("phone2"))
            contact.setPhone2(json.get("phone2").asText());

        List<Instrument> instruments = new ArrayList<>();
        for (JsonNode instrumentName : json.get("instrument")) {
            instruments.add(new Instrument(instrumentName.asText()));
        }
        List<String> desiredStyles = new ArrayList<>();
        for (JsonNode styleName : json.get("desired_styles")) {
            desiredStyles.add(styleName.asText());
        }
        List<String> undesiredStyles = new ArrayList<>();
        for (JsonNode styleName : json.get("undesired_styles")) {
            undesiredStyles.add(styleName.asText());
        }
        int interest = json.get("interest").asInt();
        String passwd = DigestUtils.sha1Hex(json.get("passwd").asText());
        Ad ad = new Ad(author, title, description, address, contact, instruments,
                desiredStyles, undesiredStyles, interest, passwd);

        if (db.persist(ad)) {
            db.flush();
            return created(successMsg(CREATED));
        }

        return internalServerError();
    }

    private JsonNode successMsg(int status) {
        HashMap<String, String> map = new HashMap<>();
        map.put("message", "Success!");
        map.put("status", String.valueOf(status));
        return (Json.toJson(map));
    }
}
