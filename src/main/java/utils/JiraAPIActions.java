package utils;

import json.JsonBuilder;
import io.restassured.response.Response;

public class JiraAPIActions {
    HttpSender httpSender;
    JsonBuilder jsonBuilder;

    public JiraAPIActions() {
        jsonBuilder  = new JsonBuilder();
        httpSender = new HttpSender();
    }

    public Response login(String name, String password){
        String jsonForLogin = jsonBuilder.loginJSON(name,password);
        return httpSender.postAuth(jsonForLogin, ApiPATH.LOGIN);
    }

    public Response createIssue(String project, String summary, String description, String issueType){
        String createIssueJSON = jsonBuilder.createIssueJSON(project,summary,description,issueType);
        return httpSender.post(createIssueJSON, ApiPATH.ISSUE);
    }

    public Response deleteIssue(String issueID){
        return httpSender.delete(ApiPATH.ISSUE, issueID);
    }
    public Response updateIssue(String user, String issueID){
        String updateIssueJSON = jsonBuilder.updateIssueJSON(user);
        return httpSender.put(updateIssueJSON, ApiPATH.ISSUE,issueID );
    }
    public Response addComment(String comment, String issueID){
        String addCommentJSON = jsonBuilder.addCommentJSON(comment);
        return httpSender.put(addCommentJSON, ApiPATH.ISSUE,issueID );
    }



}
