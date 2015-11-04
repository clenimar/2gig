package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Ad;
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
        String street = json.get("street").asText();
        String number = json.get("number").asText();
        String neighbourhood = json.get("neighbourhood").asText();
        String city = json.get("city").asText();
        String state = json.get("state").asText();
        String country = json.get("country").asText();
        String email = json.get("email").asText();
        String phone = json.get("phone1").asText();

        List<String> instruments = new ArrayList<>();
        for (String instrument : json.get("instrument").asText().split(",")) {
            instruments.add(instrument);
        }

        List<String> desiredStyles = new ArrayList<>();
        for (String style : json.get("desired_styles").asText().split(",")) {
            desiredStyles.add(style);
        }

        List<String> undesiredStyles = new ArrayList<>();
        for (String style : json.get("undesired_styles").asText().split(",")) {
            undesiredStyles.add(style);
        }

        String interest = json.get("interest").asText();
        String passwd = DigestUtils.sha1Hex(json.get("passwd").asText());

        Ad ad = new Ad(author, title, description, street, number, neighbourhood,
                city, state, country, email, phone, instruments,
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
