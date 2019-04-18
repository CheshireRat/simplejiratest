package utils;

import json.JsonBuilder;
import io.restassured.response.Response;
import pages.PropertyReader;

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
        return httpSender.post(jsonForLogin, ApiPATH.LOGIN);
    }
}
