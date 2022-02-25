package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import utilities.ConfigReader;
import utilities.Driver;

import java.util.Map;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class BooksAPISteps {

    String orderId;
    Response response;
    String token=getToken();


    public String getToken(){
        Random random=new Random();
        int randomNum=random.nextInt();
        Response response=
                given().baseUri(ConfigReader.getProperty("BooksApiBaseURI"))
                        .and().header("Content-type","application/json")
                        .and().header("Accept","application/json")
                        .and().body("{\n" +
                                "   \"clientName\": \"{{$randomFirstName}}\",\n" +
                                "   \"clientEmail\": \"test"+randomNum+"@example.com\"\n" +
                                "}")
                        .when().post("/api-clients/");

        return response.body().jsonPath().getString("accessToken");

    }


    @Given("user sends POST order API call with data")
    public void user_sends_POST_order_API_call_with_data(DataTable dataTable) {

        Map<String, Object> data= dataTable.asMap(String.class, Object.class);

       Response response=
               given().baseUri(ConfigReader.getProperty("BooksApiBaseURI"))
                       .and().header("Content-type","application/json")
                       .and().header("Authorization","Bearer "+token)
                       .and().body(data)
                       .when().post("/orders");

        System.out.println("Order created with status code "+response.getStatusCode());
        orderId=response.body().jsonPath().getString("orderId");

    }


    @When("user sends GET order API call")
    public void user_sends_GET_order_API_call() {
        response=
                given().baseUri(ConfigReader.getProperty("BooksApiBaseURI"))
                        .and().header("Accept","application/json")
                        .and().header("Authorization","Bearer "+token)
                        .when().get("/orders/"+orderId);

    }

    @Then("user validates status code is {int}")
    public void user_validates_status_code_is(Integer statusCode) {
        Integer actualStatusCode=response.getStatusCode();
        Assert.assertEquals(statusCode,actualStatusCode);


    }

    @When("user sends PATCH order API call with data")
    public void user_sends_PATCH_order_API_call_with_data(DataTable dataTable) {
        Map<String, Object> data=dataTable.asMap(String.class,Object.class);
        response=
                given().baseUri(ConfigReader.getProperty("BooksApiBaseURI"))
                        .and().header("Content-Type", "application/json")
                        .and().header("Accept","application/json")
                        .and().header("Authorization","Bearer "+token)
                        .and().body(data)
                        .when().patch("/orders/"+orderId);

    }

    @When("user sends DELETE order API call")
    public void user_sends_DELETE_order_API_call() {
        response=
                given().baseUri(ConfigReader.getProperty("BooksApiBaseURI"))
                        .and().header("Content-Type","application/json")
                        .and().header("Application","application/json")
                        .and().header("Authorization","Bearer "+token)
                        .when().delete("/orders/"+orderId);

    }

}
