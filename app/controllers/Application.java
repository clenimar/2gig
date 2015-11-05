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

//    /**
//     * Return all the active advertisements
//     */
//    @play.db.jpa.Transactional
//    public Result getActiveAds() {
//        List<Ad> queryResult = db.findByAttributeName("Ad", "closed", "false");
//        HashMap<String, Ad> map = new HashMap<>();
//        if (queryResult == null)
//            return ok(Json.toJson(map));
//
//        for (Ad ad : queryResult)
//            map.put(String.valueOf(ad.getId()), ad);
//
//        return ok(Json.toJson(map));
//    }

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
    public Ad getAds(long id) {
        List<Ad> queryResult = db.findByAttributeName("Ad", "id", String.valueOf(id));
        if (queryResult.size() > 1 || queryResult.size() < 0) {
            return null;
        }

        return queryResult.get(0);
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
                city, state,  email, phone, instruments,
                desiredStyles, undesiredStyles, interest, passwd);

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
            // check pass
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
}
