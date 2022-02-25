package utilities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiTest {
    public static void main(String[] args) {
// Look last line
        /*
        Do GET call
        get employee with employee id 100
        /api/employees/100
         */

        /*
        GET call:
        URL+ Headers
        URL=BaseURL+Endpoint
        Given BaseURL
        AND Headers (Accept -> application/json
        WHEN Endpoint (make a get call with endpoint)
         */

        Response response = given().baseUri("https://devenv-apihr-arslan.herokuapp.com") // base URL
                .and().accept(ContentType.JSON) // Header (Accept)
                .when().get("/api/employees/100"); // call type endpoint

        response.then().log().all();

        System.out.println(response.statusCode());
        System.out.println(response.body().asString());
        System.out.println(response.body().as(HashMap.class));

        Map<String,Object> responseData=response.body().as(HashMap.class);

        System.out.println(responseData.get("employeeId"));

        // log -> print information in console

        /* Post employee
        Request: BaseURL + EndPoint + Headers +Jason body
        Given BaseURL
        AND Accept -> application Json
        And Content Type -> application Json
        When Json body
        And send POST call

        Response: StatusCode ->201
         */

        Response postResponse = given().baseUri("https://devenv-apihr-arslan.herokuapp.com")
                .and().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .when().body("{\n" +
                        "    \"firstName\": \"Lorem aliquip commodo sint\",\n" +
                        "    \"lastName\": \"occaecat laborum\",\n" +
                        "    \"employeeId\": 208,\n" +
                        "    \"department\": {\n" +
                        "        \"departmentId\": 22466751,\n" +
                        "        \"departmentName\": \"officia in aute\",\n" +
                        "        \"location\": {\n" +
                        "            \"locationCountry\": \"sunt d\",\n" +
                        "            \"locationState\": \"commodo incididunt\",\n" +
                        "            \"locationCity\": \"qui laborum aute velit\",\n" +
                        "            \"locationId\": -58196885\n" +
                        "        }\n" +
                        "    },\n" +
                        "    \"job\": {\n" +
                        "        \"title\": \"adipisicing cillum\",\n" +
                        "        \"jobId\": \"non Duis dolore\",\n" +
                        "        \"salary\": -8.40930582423801E7\n" +
                        "    }\n" +
                        "}")
                .and().post("/api/employees");

        System.out.println(postResponse.statusCode());

        /*
        DELETE call:
        Given Base URL
        When delete call (Endpoint)

        RESPONSE
        Status code 204 No content
         */

        Response deleteResponse=given().baseUri("https://devenv-apihr-arslan.herokuapp.com")
                .when().delete("/api/employees/208");

        System.out.println(deleteResponse.statusCode());


      //  Response response1=ApiUtils.apiGet("/api/departments/10");
       // System.out.println(response1);











    }

}
