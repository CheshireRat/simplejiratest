package utils;

import io.restassured.response.Response;
import pages.PropertyReader;
import static io.restassured.RestAssured.given;

public class HttpSender {
    static String baseUrl = PropertyReader.readValue("baseUrl") ;

    //TODO - support JSESSIONID and remove postAuth. all requests - delete .auth().preemptive().basic(
    //static String JSESSIONID;

    public Response post(String body, String uri){
        Response response = given()
                .auth().preemptive().basic(PropertyReader.readValue("login"), PropertyReader.readValue("password"))
                .relaxedHTTPSValidation()
                .header("Content-Type", "application/json")
                .body(body)
                .post(baseUrl + uri);
        return response;
    }

    public Response postAuth(String body, String uri){
        Response response = given()
                //.auth().preemptive().basic(PropertyReader.readValue("login"), PropertyReader.readValue("password"))
                .relaxedHTTPSValidation()
                .header("Content-Type", "application/json")
                .body(body)
                .post(baseUrl + uri);
        return response;
    }

    public Response get( String uri){
        Response response = given()
                .auth().preemptive().basic(PropertyReader.readValue("login"), PropertyReader.readValue("password"))
                .relaxedHTTPSValidation()
                .header("Content-Type", "application/json")
                .get(baseUrl + uri);
        return response;
    }

    public Response delete(String uri, String issueID){
        Response response = given()
                .auth().preemptive().basic(PropertyReader.readValue("login"), PropertyReader.readValue("password"))
                .relaxedHTTPSValidation()
                .header("Content-Type", "application/json")
                .delete(baseUrl + uri + issueID);
        return response;
    }

    public Response put(String body,String uri, String issueID){
        Response response = given()
                .auth().preemptive().basic(PropertyReader.readValue("login"), PropertyReader.readValue("password"))
                .relaxedHTTPSValidation()
                .header("Content-Type", "application/json")
                .body(body)
                .put(baseUrl + uri+  issueID);
        return response;
    }
}
