package utilities;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Random;

import static io.restassured.RestAssured.given;

public class RestAPITest {
    public static void main(String[] args) {

        /*
        Get list of Books
        1. URL (Base + Endpoint)
        2. Headers (what type of data we're accepting? in Key & Value Key="Accept",
        Value= "application/json" or XML)
         */

        Response response=
                given().baseUri("https://simple-books-api.glitch.me")
                .and().header("Accept","application/json")
                .when().get("/books");

        System.out.println(response.statusCode());
        System.out.println(response.body().asString());

        Random random=new Random();
        int randomNum=random.nextInt();


        Response authResponse=
                given().baseUri("https://simple-books-api.glitch.me")
                .and().header("Content-Type","application/json")
                .and().header("Accept","application/json")
                .and().body("{\n" +
                                "   \"clientName\": \"{{$randomFirstName}}\",\n" +
                                "   \"clientEmail\": \"alish"+randomNum+"@example.com\"\n" +
                                "}")
                .when().post("/api-clients/");

        System.out.println(authResponse.statusCode());
        System.out.println(authResponse.body().asString());

        String token=authResponse.body().jsonPath().getString("accessToken");

        Response postResponse=
                given().baseUri("https://simple-books-api.glitch.me")
                .and().header("Content-Type", "application/json")
                .and().header("Authorization","Bearer "+token)
                .and().body("{\n" +
                                "  \"bookId\": 1,\n" +
                                "  \"customerName\": \"{{$randomFullName}}\"\n" +
                                "}")
                .when().post("/orders");

        System.out.println(postResponse.getStatusCode());




    }
}
