package json;

import org.json.JSONException;
import org.json.JSONObject;

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
}
