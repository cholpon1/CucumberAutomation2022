package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pojos.hr_api_pojos.Department;
import pojos.hr_api_pojos.Location;
import utilities.ConfigReader;
import utilities.Driver;

import java.util.Random;

import static io.restassured.RestAssured.given;

public class HrAppDepartmentsApiSteps {

    WebDriver driver = Driver.getDriver();
    Response response;
    Department departmentBody= new Department();
    Location location= new Location();
    Random random =new Random();
    Integer departmentId= random.nextInt();



    @Given("user creates department with departments POST call having random data")
    public void user_creates_department_with_departments_POST_call_having_random_data() {



        departmentBody.setDepartmentId(departmentId);
        departmentBody.setDepartmentName("Universe"+departmentId);
        location.setLocationCity("Osaka");
        location.setLocationCountry("JP");
        location.setLocationId(7571);
        location.setLocationState("Osaka_Fu");
        departmentBody.setLocation(location);

        response=
                given().baseUri(ConfigReader.getProperty("HRApiBaseURL"))
                        .and().header("Content-Type","application/json")
                        .and().body(departmentBody) //Converting String/Map to json called Serialization
                        .when().post("/api/departments");
    }

    @Then("user validates status code {int}")
    public void userValidatesStatusCode(int statusCode) {

        //response.then().statusCode(statusCode);
    }

    @When("user GETS created department")
    public void user_GETS_created_department() {
        response=
                given().baseUri(ConfigReader.getProperty("HRApiBaseURL"))
                        .and().header("Accept","application/json")
                        .when().get("/api/departments/"+departmentId);

    }

    @Then("user validates that created department in Departments dropdown")
    public void user_validates_that_created_department_in_Departments_dropdown() {

        String uiDepartment=driver.findElement(By.xpath("//option[@value='"+departmentId+"']")).getText();
        Assert.assertEquals("Universe"+departmentId,uiDepartment);


    }

    @When("user DELETES created department")
    public void user_DELETES_created_department() {
        response=given().baseUri(ConfigReader.getProperty("HRApiBaseURL"))
                .when().delete("/api/departments/"+departmentId);

    }



}
