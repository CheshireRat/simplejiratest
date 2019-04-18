package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class HttpSender {
    static String baseUrl = "http://jira.hillel.it:8080";
    static String JSESSIONID;

    public Response post(String body, String uri){
        Response response = given()
                //.auth().preemptive().basic(PropertyReader.readValue("login"), PropertyReader.readValue("password"))
                .header("Content-Type", "application/json")
                .body(body)
                .post(baseUrl + uri);
        return response;
    }


}
