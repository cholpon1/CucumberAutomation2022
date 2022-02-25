package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.junit.Assert;
import utilities.ApiUtils;

import java.util.HashMap;
import java.util.Map;

public class BankApiSteps {

    Response response;

    @Given("user gets all customers with api call with LIMIT {int}")
    public void user_gets_all_customers_with_api_call_with_LIMIT(Integer limit) {
        Map<String, Object> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        response = ApiUtils.apiGet("/api/customers?limit="+limit, headers);


    }

    @Then("user validates bank api status code is {int}")
    public void user_validates_bank_api_status_code_is(Integer expectedStatusCode) {
        response.then().statusCode(expectedStatusCode);

    }

    @Then("user validates that response includes {int} customers only")
    public void user_validates_that_response_includes_customers_only(int limit) {
       int numberOfCustomers =  response.body().jsonPath().getList("id").size();
        Assert.assertEquals(limit, numberOfCustomers);

    }

}
