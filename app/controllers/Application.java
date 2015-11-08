package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Ad;
import org.apache.commons.codec.digest.DigestUtils;
import play.libs.Json;
import play.mvc.*;
import java.util.HashMap;
import java.util.List;
import models.dao.GenericDAO;

public class Application extends Controller {

    private static final GenericDAO db = new GenericDAO();

    /**
     * Return all the active advertisements
     */
    @play.db.jpa.Transactional
    public Result getActiveAds() {
        List<Ad> queryResult = db.findByAttributeName("Ad", "closed", "false");

        if (queryResult == null)
            return ok(Json.toJson(queryResult));

        return ok(Json.toJson(queryResult));
    }

    /**
     * Return how many closed advertisements there are in DB
     */
    @play.db.jpa.Transactional
    public Result getCountClosed(){
        List<Ad> queryResult = db.findByAttributeName("Ad", "closed", "true");
        HashMap<String, String> map = new HashMap<>();
        if (queryResult == null)
            return ok(Json.toJson(map));

        map.put("count", String.valueOf(queryResult.size()));

        return ok(Json.toJson(map));
    }

    /**
     * Return an Ad specified by :id parameter
     */
    @play.db.jpa.Transactional
    public Result getAds(long id) {
        List<Ad> queryResult = db.findByAttributeName("Ad", "id", String.valueOf(id));
        if (queryResult.size() > 1 || queryResult.size() < 0) {
            return null;
        }

        return ok(Json.toJson(queryResult));
    }

    /**
     * Create a new advertisement
     */
    @play.db.jpa.Transactional
    public Result createAds() {
        JsonNode json = request().body().asJson();

        if (json == null)
            return badRequest();

        HashMap<String, String> contact = new HashMap<>();
        HashMap<String, String> stylePreferences = new HashMap<>();
        stylePreferences.put("desired_styles", "");
        stylePreferences.put("undesired_styles", "");

        String author = json.get("author").asText();
        String title = json.get("title").asText();
        String description = json.get("description").asText();
        String street = json.get("street").asText();
        String number = json.get("number").asText();
        String neighbourhood = json.get("neighbourhood").asText();
        String city = json.get("city").asText();
        String state = json.get("state").asText();

        if (json.has("email"))
            contact.put("email", json.get("email").asText());

        if (json.has("phone1"))
            contact.put("phone1", json.get("phone1").asText());

        if (json.has("facebook"))
            contact.put("facebook", json.get("facebook").asText());

        String instruments = json.get("instrument").asText();

        if (json.has("desired_styles"))
             stylePreferences.put("desired_styles", json.get("desired_styles").asText());

        if (json.has("undesired_styles"))
            stylePreferences.put("undesired_styles", json.get("undesired_styles").asText());

        String interest = json.get("interest").asText();
        String passwd = DigestUtils.sha1Hex(json.get("passwd").asText());

        if (!isContactValid(contact))
            return badRequest();

        Ad ad = new Ad(author, title, description, street, number, neighbourhood,
                city, state, contact, instruments,
                stylePreferences.get("desired_styles"), stylePreferences.get("undesired_styles"), interest, passwd);

        if (db.persist(ad)) {
            db.flush();
            return created(successMsg(CREATED));
        }

        return internalServerError();
    }

    /**
     * Close an advertisement
     */
    @play.db.jpa.Transactional
    public Result closeAds(long id) {
        List<Ad> queryResult = db.findByAttributeName("Ad", "id", String.valueOf(id));
        int size = queryResult.size();
        if (size == 1) {
            Ad ad = queryResult.get(0);
            //check pass
            ad.close();
            db.persist(ad);
            db.flush();
            return created(successMsg(CREATED));
        }
        return badRequest();
    }

    private JsonNode successMsg(int status) {
        HashMap<String, String> map = new HashMap<>();
        map.put("message", "Success!");
        map.put("status", String.valueOf(status));
        return (Json.toJson(map));
    }

    private boolean isContactValid(HashMap<String, String> contact) {
        for (String info : contact.values()) {
            if (info != null && !info.isEmpty())
                return true;
        }
        return false;
    }
}
