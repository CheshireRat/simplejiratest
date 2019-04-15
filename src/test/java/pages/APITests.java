package pages;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class APITests {
    
    @Test
    public void login() {

    }

    @Test
    public void loginWrongPassword() {

    }

    @Test
    public void loginWrongUserName() {

    }

    @Test
    public void createIssue() {

        String issueID = "";


            deleteIssue( issueID);

            //curl -D- -u Nuzhin_Ivan:test -X POST --data '{"fields":{"project":{"key": "QAAUT7"},"summary": "Summary test","description": "descr","issuetype": {"name": "Bug"}}}' -H "Content-Type: application/json"  http://jira.hillel.it:8080/rest/api/2/issue/

        //response
        //{"id":"48710","key":"QAAUT7-732","self":"http://jira.hillel.it:8080/rest/api/2/issue/48710"}

    }

    //String issueID = "QAAUT7-732"; //this is already deleted
    @Test
    public void deleteIssue(String issueID ) {
        Response response = RestAssured.given()
                .auth().preemptive().basic(PropertyReader.readValue("login"), PropertyReader.readValue("password"))
                .header("Content-Type", "application/json")
                .delete(String.format("http://jira.hillel.it:8080/rest/api/2/issue/", issueID));
                //.delete("http://jira.hillel.it:8080/rest/api/2/issue/QAAUT7-731");

//        System.out.println(response.statusCode());
//        System.out.println(response.statusLine());

        Assert.assertTrue(response.statusCode() == 204);
        //curl -X DELETE -u Nuzhin_Ivan:test --header 'Accept: application/json' --url 'http://jira.hillel.it:8080/rest/api/2/issue/QAAUT7-729'
    }

    @Test
    public void updateIssue() {
        //update assignee
        //curl -D- -u Nuzhin_Ivan:test -X PUT --data '{ "fields": { "assignee":{"name":"Nuzhin_Ivan"} }}' -H "Content-Type: application/json" http://jira.hillel.it:8080/rest/api/2/issue/QAAUT7-732
    }

    @Test
    public void addComment() {


        //curl -D- -u Nuzhin_Ivan:test -X PUT -d '{"update": {"comment": [{"add": {"body": "Comment test"}}]}}' -H "Content-Type: application/json" http://jira.hillel.it:8080/rest/api/2/issue/QAAUT7-732
    }

    @Test
    public void deleteComment() {

    }

    @Test
    public void JQLSearchIssue() {

        //TODO - check request
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

}