package controllers;

import play.libs.Json;
import play.mvc.*;
import java.util.HashMap;

public class Application extends Controller {

    public Result index() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("name", "Clenimar");
        map.put("age", "18");
        map.put("motto", "Lifes good");

        return ok(Json.toJson(map));
    }

    public Result getClassifieds() {

    }
}
