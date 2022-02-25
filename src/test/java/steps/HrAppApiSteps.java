package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.junit.Assert;
import utilities.ConfigReader;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class HrAppApiSteps {

    Response response;

    @Given("user sends GET location API call with locationId {int}")
    public void user_sends_GET_location_API_call_with_locationId(Integer locationId) {
         response=
                given().baseUri(ConfigReader.getProperty("HRApiBaseURL"))
                        .and().header("Accept","application/json")
                        .when().get("/api/location/"+locationId);

    }

    @Then("user validates {int} status code")
    public void user_validates_status_code_is(Integer expectedStatusCode) {
        Integer actualStatusCode=response.getStatusCode();
        Assert.assertEquals(expectedStatusCode,actualStatusCode);

    }

    @Then("user validates response body with data")
    public void user_validates_response_body_with_data(DataTable dataTable) {

        Map<String, Object> data=dataTable.asMap(String.class,Object.class);

       for(String key: data.keySet()){
           String expectedValue=data.get(key).toString();
           String actualValue=response.body().jsonPath().getString(key);
           Assert.assertEquals(expectedValue,actualValue);
       }

    }

}
