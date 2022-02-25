package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;
import org.junit.Assert;
import pojos.bookings_api_pojo.Booking;
import pojos.bookings_api_pojo.BookingDates;
import utilities.ConfigReader;


import java.time.LocalDate;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class BookingAPISteps {
    Response response;
    int bookingid;
    Map<String, Object> data;

    @Given("user sends create booking api POST call with data")
    public void user_sends_create_booking_api_POST_call_with_data(io.cucumber.datatable.DataTable dataTable) {
         data = dataTable.asMap(String.class, Object.class);

        Booking booking = new Booking();
        booking.setFirstname(data.get("firstName").toString());
        booking.setLastname(data.get("lastName").toString());
        booking.setTotalprice(Integer.valueOf(data.get("totalPrice").toString()));
        booking.setDepositpaid(Boolean.valueOf(data.get("depositPaid").toString()));

        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckin(data.get("checkIn").toString());
        bookingDates.setCheckout(data.get("checkOut").toString());
        booking.setBookingdates(bookingDates);
        booking.setAdditionalneeds(data.get("additionalNeeds").toString());


        response =
                given().baseUri(ConfigReader.getProperty("BookingAPIBaseURI"))
                        .and().header("Content-Type", "application/json")
                        .and().header("Accept","application/json")
                        .and().body(booking) // POJO
                        .when().post("/booking");

        bookingid = response.body().jsonPath().getInt("bookingid");
    }
    @Then("user validates status code {int} for booking")
    public void user_validates_status_code_for_booking(Integer expectedStatusCode) {
        response.then().statusCode(expectedStatusCode);


    }
    @Then("user validates that booking is created with api GET call")
    public void user_validates_that_booking_is_created_with_api_GET_call() {
        response =
                given().baseUri(ConfigReader.getProperty("BookingAPIBaseURI"))
                        .and().header("Accept", "application/json")
                        .when().get("/booking/"+bookingid);

        Booking responseBody = response.body().as(Booking.class);

        Assert.assertEquals(data.get("firstName").toString(), responseBody.getFirstname());
        Assert.assertEquals(data.get("lastName").toString(), responseBody.getLastname());
        Assert.assertEquals(data.get("checkIn").toString(), responseBody.getBookingdates().getCheckin().toString());
        Assert.assertEquals(data.get("checkOut").toString(), responseBody.getBookingdates().getCheckout().toString());

    }
    @When("user updates booking with api PATCH call with data")
    public void user_updates_booking_with_api_PATCH_call_with_data(io.cucumber.datatable.DataTable dataTable) {
        Map<String, Object> updatedData = dataTable.asMap(String.class, Object.class);
       // data.put("firstName", updatedData.get("firstName"));

        response =
                given().baseUri(ConfigReader.getProperty("BookingAPIBaseURI"))
                        .and().header("Content-Type", "application/json")
                        .and().header("Accept","application/json")
                        .and().header("Authorization", "Basic")
                        .and().header("cookie", "token="+getToken()) // both of them static , in the same class
                        .and().body(updatedData) // Map
                        .when().patch("/booking/"+bookingid);

        System.out.println(response.body().asString());


    }
    @When("user deletes booking with api DELETE call")
    public void user_deletes_booking_with_api_DELETE_call() {
        response =
    given().baseUri(ConfigReader.getProperty("BookingAPIBaseURI"))
                .and().header("Authorization", "basic")
            .and().header("Cookie", "token="+getToken())
            .and().delete("/booking/"+bookingid);

    }
    @Then("user validates that booking is deleted and Get call has {int} status code")
    public void user_validates_that_booking_is_deleted_and_Get_call_has_status_code(Integer expectedStatusCode) {
        response =
                given().baseUri(ConfigReader.getProperty("BookingAPIBaseURI"))
                        .and().header("Accept", "application/json")
                        .when().get("/booking/"+bookingid);

        response.then().statusCode(expectedStatusCode);

    }

    public String getToken(){ // string = token
        return given().baseUri(ConfigReader.getProperty("BookingAPIBaseURI"))
                .and().header("Content-Type", "application/json")
                .and().body("{\n" +  // POJO | String | Map
                        "    \"username\" : \"admin\",\n" +
                        "    \"password\" : \"password123\"\n" +
                        "}")
                .when().post("/auth")
                .body().jsonPath().getString("token");

    }

}
