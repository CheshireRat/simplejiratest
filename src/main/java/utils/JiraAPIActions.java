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
        //TODO - remove toString and fix ApiPATH
        return httpSender.postAuth(jsonForLogin, ApiPATH.LOGIN);
    }

    public Response createIssue(String project, String summary, String description, String issueType){
        String createIssueJSON = jsonBuilder.createIssueJSON(project,summary,description,issueType);
        //TODO - remove toString and fix ApiPATH
        return httpSender.post(createIssueJSON, ApiPATH.ISSUE);



    }


}
