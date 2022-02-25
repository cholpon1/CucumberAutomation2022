package utilities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiUtils {
    /*
Utility methods:

.getCall(String endpoint); -> return response;
.postCall(String endpoint, Object body); -> return response
.putCall(String endpoint, Object body); -> return response
.deleteCall(String endpoint);

We will make them all a static methods, that we can call them without creating an Object.
     */

//    public static Response putCall(String endpoint, Object body) {
//        Response response = given().baseUri(ConfigReader.getProperty("ApiBaseURL"))
//                .and().accept(ContentType.JSON)
//                .and().contentType(ContentType.JSON)
//                .when().body(body)
//                .and().put(endpoint);
//        return response;
//    }

    public static Response apiGet(String endpoint, Map<String, Object> headers) {
        Response response = given().baseUri(ConfigReader.getProperty("BankAPIBaseURL"))
                .and().headers(headers)
                .and().auth().oauth2(getToken()) // will put auth key
                .when().get(endpoint);
        response.then().log().all();
        return response;

    }

    public static Response apiPost(String endpoint, Map<String, Object> headers, Object body) {
        Response response = given().baseUri(ConfigReader.getProperty("BankAPIBaseURL"))
                .and().headers(headers)
                .and().auth().oauth2(getToken())
                .and().body(body)
                .when().post(endpoint);
        response.then().log().all();
        return response;
    }

    public static Response apiPut(String endpoint, Map<String, Object> headers, Object body) {
        Response response = given().baseUri(ConfigReader.getProperty("BankAPIBaseURL"))
                .and().headers(headers)
                .and().auth().oauth2(getToken())
                .and().body(body)
                .when().post(endpoint);
        response.then().log().all();
        return response;
    }

    public static Response apiDelete(String endpoint, Map<String, Object> headers, Object body) {
        Response response = given().baseUri(ConfigReader.getProperty("BankAPIBaseURL"))
                .and().headers(headers)
                .and().auth().oauth2(getToken())
                .when().delete(endpoint);
        response.then().log().all();
        return response;
    }
    public static String getToken(){
       Response response =  given().baseUri(ConfigReader.getProperty("BankAPIBaseURL"))
                .and().header("Content-Type", "application/json")
                .and().header("Accept", "application/json")

                .and().body("{\n" +
                        "    \"password\": \"MindtekStudent\",\n" +
                        "    \"username\": \"Mindtek\"\n" +
                        "}")
                .when().post("authenticate"); // /authenticate?
                response.then().log().all();
//                .body().jsonPath().getString("jwt");
        return response.body().jsonPath().getString("jwt");


    }
}
