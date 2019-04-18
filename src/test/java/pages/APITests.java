package pages;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import json.JsonBuilder;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.JiraAPIActions;

import static io.restassured.RestAssured.given;

public class APITests {

    JsonBuilder jsonBuilder ;
    String cookie;
    String issueID;

    @Test
    public void login() {
        //TODO - move login to base
        JiraAPIActions jiraAPIActions = new JiraAPIActions();
        Response response = jiraAPIActions.login(
                                (PropertyReader.readValue("login"))
                               ,(PropertyReader.readValue("password")));
        cookie = response.then().extract().path("session.value");
        Assert.assertTrue(response.statusCode() == 200);
    }

    @Test
    public void loginWrongPassword() {

        Response response = given()
                //.auth().preemptive().basic(PropertyReader.readValue("login"), PropertyReader.readValue("password"))
                .header("Content-Type", "application/json")
                .body("{ \"username\": \"Nuzhin_ivan\", \"password\": \"wrongpassword\" }")
                .post("http://jira.hillel.it:8080/rest/auth/1/session");

        //System.out.println(response.prettyPrint());

        Assert.assertTrue(response.prettyPrint().contains("Login failed"));
    }

    @Test
    public void loginWrongUserName() {
        Response response = given()
                //.auth().preemptive().basic(PropertyReader.readValue("login"), PropertyReader.readValue("password"))
                .header("Content-Type", "application/json")
                .body("{ \"username\": \"wrongUserName\", \"password\": \"wrongpassword\" }")
                .post("http://jira.hillel.it:8080/rest/auth/1/session");

        Assert.assertTrue(response.prettyPrint().contains("Login failed"));
    }

    @Test
    public void createIssue() {
        //RestAssured.baseURI = "http://jira.hillel.it:8080/rest/api/2/issue/";
        Response response = given()
                .auth().preemptive().basic(PropertyReader.readValue("login"), PropertyReader.readValue("password"))
                .header("Content-Type", "application/json")
                .body("{\"fields\":{\"project\":{\"key\": \"QAAUT7\"},\"summary\": \"Summary test\",\"description\": \"descr\",\"issuetype\": {\"name\": \"Bug\"}}}")
                .post("http://jira.hillel.it:8080/rest/api/2/issue/");

        JSONObject jObj = null;
        try {
            jObj = new JSONObject(response.asString());
            issueID = jObj.getString("key");
        } catch(JSONException e) {
            e.printStackTrace();
        }

        System.out.println(response.statusCode());

        Assert.assertTrue(response.statusCode() == 201);

        //curl -D- -u Nuzhin_Ivan:test -X POST --data '{"fields":{"project":{"key": "QAAUT7"},"summary": "Summary test","description": "descr","issuetype": {"name": "Bug"}}}' -H "Content-Type: application/json"  http://jira.hillel.it:8080/rest/api/2/issue/

        //response
        //{"id":"48710","key":"QAAUT7-732","self":"http://jira.hillel.it:8080/rest/api/2/issue/48710"}

        deleteIssue();
        }

    @Test
    public void deleteIssue( ) {
        StringBuilder builder = new StringBuilder();
        String baseUrl = "http://jira.hillel.it:8080/rest/api/2/issue/";

        Response response = given()
                .auth().preemptive().basic(PropertyReader.readValue("login"), PropertyReader.readValue("password"))
                .header("Content-Type", "application/json")
                .delete(builder
                        .append(baseUrl)
                        .append(issueID)
                        .toString()
                        );
       // System.out.println(builder.append(baseUrl).append(issueID).toString());
       // System.out.println(response.statusCode());
       // System.out.println(response.statusLine());

        Assert.assertTrue(response.statusCode() == 204);
        //curl -X DELETE -u Nuzhin_Ivan:test --header 'Accept: application/json' --url 'http://jira.hillel.it:8080/rest/api/2/issue/QAAUT7-729'
    }

    @Test
    public void updateIssue() {
        jsonBuilder = new JsonBuilder();

        RestAssured.baseURI  = "http://jira.hillel.it:8080/rest/api/2/issue/QAAUT7-732";
        Response response = given()
                .auth().preemptive().basic(PropertyReader.readValue("login"), PropertyReader.readValue("password"))
                .header("Content-Type", "application/json")
                .body(jsonBuilder.updateIssue())
                .put();

        Assert.assertTrue(response.statusCode() == 204);

        //curl -D- -u Nuzhin_Ivan:test -X PUT --data '{ "fields": { "assignee":{"name":"Nuzhin_Ivan"} }}' -H "Content-Type: application/json" http://jira.hillel.it:8080/rest/api/2/issue/QAAUT7-732
    }

    @Test
    public void addComment() {
        RestAssured.baseURI  = "http://jira.hillel.it:8080/rest/api/2/issue/QAAUT7-732";
        Response response = given()
                .auth().preemptive().basic(PropertyReader.readValue("login"), PropertyReader.readValue("password"))
                .header("Content-Type", "application/json")
                .body("{\"update\": {\"comment\": [{\"add\": {\"body\": \"Comment test\"}}]}}")
                .put();

        Assert.assertTrue(response.statusCode() == 204);

        //curl -D- -u Nuzhin_Ivan:test -X PUT -d '{"update": {"comment": [{"add": {"body": "Comment test"}}]}}' -H "Content-Type: application/json" http://jira.hillel.it:8080/rest/api/2/issue/QAAUT7-732
    }

    @Test
    public void deleteComment() {

    }

    @Test
    public void JQLSearchIssue() {
        String baseUrl = "http://jira.hillel.it:8080/rest/api/2/search?jql=";
        String jqlString = "project=QAAUT7&maxResults=1&creator=currentUser()";
        String  totalReported = null;
        StringBuilder builder = new StringBuilder();
        Response response = given()
                .auth().preemptive().basic(PropertyReader.readValue("login"), PropertyReader.readValue("password"))
                .header("Content-Type", "application/json")
                .get(builder
                        .append(baseUrl)
                        .append(jqlString)
                        .toString());

        JSONObject jObj = null;
        try {
            jObj = new JSONObject(response.asString());
            totalReported = jObj.getString("total");
        } catch(JSONException e) {
            e.printStackTrace();
        }
        int result = Integer.parseInt(totalReported);

        Assert.assertTrue(response.statusCode() == 200);
        Assert.assertTrue(result > 50);


        //not worked request!!! just as an example
        //curl -D- -u $usernameData:$passwordData -X GET -H "Content-Type: application/json" "http://jira.alm.mentorg.com:8080/rest/api/2/search?jql=project=HDS&maxResults=1000&fields=summary"
    }

    @Test
    public void getUser() {

        //curl  -u Nuzhin_Ivan:test   --header 'Accept: application/json'   --url 'http://jira.hillel.it:8080/rest/api/2/user?username=Nuzhin_Ivan&expand=groups,applicationRoles'

        //{"self":"http://jira.hillel.it:8080/rest/api/2/user?username=Nuzhin_Ivan","key":"nuzhin_ivan","name":"Nuzhin_Ivan","emailAddress":"nuzhin.ivan@gmail.com","avatarUrls":{"48x48":"https://www.gravatar.com/avatar/ee6c1b396807230ced8e053aca5e87c2?d=mm&s=48","24x24":"https://www.gravatar.com/avatar/ee6c1b396807230ced8e053aca5e87c2?d=mm&s=24","16x16":"https://www.gravatar.com/avatar/ee6c1b396807230ced8e053aca5e87c2?d=mm&s=16","32x32":"https://www.gravatar.com/avatar/ee6c1b396807230ced8e053aca5e87c2?d=mm&s=32"},"displayName":"Nuzhin_Ivan","active":true,"timeZone":"Europe/Kiev","locale":"en_US","groups":{"size":2,"items":[{"name":"jira-software-users","self":"http://jira.hillel.it:8080/rest/api/2/group?groupname=jira-software-users"},{"name":"webinar","self":"http://jira.hillel.it:8080/rest/api/2/group?groupname=webinar"}]},"applicationRoles":{"size":1,"items":[{"key":"jira-software","name":"JIRA Software"}]},"expand":"groups,applicationRoles"}

    }

    @Test
    public void getProject() {

        //get ALL projects
        //curl -u Nuzhin_Ivan:test   --header 'Accept: application/json'   --url 'http://jira.hillel.it:8080/rest/api/2/project'
    }

    @Test
    public void getGroups() {

    }

    @Test
    public void setPriority() {

    }


}