package pages;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class APITests {



//    @Test
//    public void login() {
//        given()
//                .header("X-Atlassian-Token", "nocheck")
//                .get("http://jira.hillel.it:8080/rest/api/2/issue/createmeta")
//                .then()
//                .statusCode(200)
//                .log()
//                .all();
//
//    }

    @Test
    public void loginWrongPassword() {

    }

    @Test
    public void loginWrongUserName() {

    }

    @Test
    public void createIssue() {

    }

String issueID = "QAAUT7-732"; //this is already deleted
    @Test
    public void deleteIssue() {
        Response response = RestAssured.given()
                .auth().preemptive().basic("Nuzhin_Ivan", "test")
                .header("Content-Type", "application/json")
                .delete(String.format("http://jira.hillel.it:8080/rest/api/2/issue/", issueID));
                //.delete("http://jira.hillel.it:8080/rest/api/2/issue/QAAUT7-731");

        System.out.println(response.statusCode());
        System.out.println(response.statusLine());

        Assert.assertTrue(response.statusCode() == 204);

        //curl -X DELETE -u Nuzhin_Ivan:test --header 'Accept: application/json' --url 'http://jira.hillel.it:8080/rest/api/2/issue/QAAUT7-729'
    }


    @Test
    public void updateIssue() {

    }

    @Test
    public void addComment() {

    }

    @Test
    public void JQLSearchIssue() {

    }

    @Test
    public void getUser() {

    }

    @Test
    public void getProject() {

    }

    @Test
    public void getGroups() {

    }
}
