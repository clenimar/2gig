package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Ad;
import models.Comment;
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
        List<Ad> queryResult = db.findByAttributeName("Ad", "feedback", "yes");
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
            JsonNode data = request().body().asJson();

            if (data.has("pass") && data.has("feedback")) {
                if (ad.checkPassword(data.get("pass").asText()))
                    ad.close();
                else
                    return unauthorized();
                ad.setFeedback(data.get("feedback").asText());
            }

            db.persist(ad);
            db.flush();
            return created(successMsg(CREATED));
        }
        return badRequest();
    }

    /**
     * Add a comment
     */
    @play.db.jpa.Transactional
    public Result addComment(long adId) {
        List<Ad> queryResult = db.findByAttributeName("Ad", "id", String.valueOf(adId));
        if (queryResult.isEmpty()) return badRequest();
        Ad ad = queryResult.get(0);

        JsonNode data = request().body().asJson();
        if (!data.has("content")) return badRequest();
        Comment comment = new Comment(data.get("content").asText());
        db.persist(comment);
        if (ad.addComent(comment)) {
            db.persist(ad);
            db.flush();
            return created(Json.toJson(successMsg(CREATED)));
        }

        return internalServerError();
    }

    /**
     * Remove a comment
     */
    @play.db.jpa.Transactional
    public Result removeComment(long adId, long commentId) {
        List<Ad> adQuery = db.findByAttributeName("Ad", "id", String.valueOf(adId));
        List<Comment> commentQuery = db.findByAttributeName("Comment", "id", String.valueOf(commentId));

        if (adQuery.isEmpty() || commentQuery.isEmpty()) return badRequest();

        JsonNode data = request().body().asJson();

        Ad ad = adQuery.get(0);
        Comment comment = commentQuery.get(0);

        if (!data.has("pass")) return badRequest();

        if (ad.checkPassword(data.get("pass").asText())) {
            ad.removeComment(comment);
            db.removeById(Comment.class, commentId);
            db.persist(ad);
            db.flush();
            return created(Json.toJson(successMsg(CREATED)));
        }

        return unauthorized();
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
