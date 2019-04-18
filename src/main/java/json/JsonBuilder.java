package json;

import org.json.JSONException;
import org.json.JSONObject;
import pages.PropertyReader;

public class JsonBuilder {



    public String updateIssue() {


        JSONObject jsObj = new JSONObject();
        try {
            jsObj = new JSONObject("{ \"fields\": { \"assignee\":{\"name\":\"Nuzhin_Ivan\"} }}");
        } catch(JSONException e1) {
            e1.printStackTrace();
        }
        return jsObj.toString();
    }

    public String loginJSON(String name, String password) {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", name);
            jsonObject.put("password", password);
        } catch(JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

}
