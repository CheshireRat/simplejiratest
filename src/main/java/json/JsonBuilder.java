package json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import pages.PropertyReader;

public class JsonBuilder {



    public String updateIssueJSON(String user) {
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonFields = new JSONObject();
        JSONObject jsonAssignee = new JSONObject();
        try {
            jsonAssignee.put("name", user );
            jsonFields.put("assignee",jsonAssignee );
            jsonObject.put("fields", jsonFields);
        } catch(JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
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

    public String addCommentJSON(String comment){
        JSONObject joUpdate = new JSONObject();
        JSONObject  joComment = new JSONObject ();
        JSONArray arr = new JSONArray();
        JSONObject joAdd = new JSONObject();
        JSONObject joBody = new JSONObject();
        try {
            joBody.put("body", comment);
            joAdd.put("add", joBody);
            arr.put(joAdd);
            joComment.put("comment", arr);
            joUpdate.put("update",joComment);
        } catch(JSONException e) {
            e.printStackTrace();
        }
        return joUpdate.toString();
    }

}
