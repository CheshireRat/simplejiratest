package json;

import org.json.JSONArray;
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

    //TODO - rebuild params as class
    public String createIssueJSON(String project, String summary, String description, String issueType) {
        //{"fields":{"project":{"key": "QAAUT7"},"summary": "Summary test","description": "descr","issuetype": {"name": "Bug"}}}
        JSONObject jsonObject = new JSONObject();
        JSONObject joFields = new JSONObject();
        JSONObject joProject = new JSONObject();
        JSONObject joIssueType = new JSONObject();
        try {
            joProject.put( "key",project);
            joIssueType.put( "name",issueType);
            joFields.put("project", joProject);
            joFields.put("summary", summary);
            joFields.put("description", description);
            joFields.put("issuetype", joIssueType);
            jsonObject.put("fields",joFields);
        } catch(JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }
}
