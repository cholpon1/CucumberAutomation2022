package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import pages.DepartmentHomePage;
import pages.HrAppLoginPage;
import pojos.Department;
import pojos.Location;
import utilities.ConfigReader;
import utilities.Driver;

import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class DepartmentApiCallsSteps {
    WebDriver driver = Driver.getDriver();
    Response response;
    int departmentId;

    HrAppLoginPage loginPage = new HrAppLoginPage();

    public int getRandomNumber(int max, int min) {
        return new Random().nextInt(max - min) + min;
    }

    @Given("User sends POST API call to create a department with provided data")
    public void createDepartment(DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);
        departmentId = getRandomNumber(10000, 500);
        Department department = new Department();
        department.setDepartmentId(departmentId);
        department.setDepartmentName(data.get("departmentName"));
        Location location = new Location();
        location.setLocationCity("Chicago");
        location.setLocationCountry("US");
        location.setLocationState("Illinois");
        department.setLocation(location);

        response = given().
                baseUri(ConfigReader.getProperty("HRApiBaseURL")).and().
                header("Content-Type", "application/json").and().
                header("Accept", "application/json").body(department)
                .when().post("/api/departments");
        response.prettyPrint();
    }

    @Then("User validates status code is {int}")
    public void user_validates_status_code_is(int statusCode) {
        Assert.assertEquals(statusCode, response.statusCode());
    }

    @Then("Validating that department is created in UI dropdown")
    public void validating_that_department_is_created_in_ui_dropdown() throws InterruptedException {
        driver.get(ConfigReader.getProperty("HrAppURL"));
        loginPage.login.sendKeys(ConfigReader.getProperty("user"));
        loginPage.password.sendKeys(ConfigReader.getProperty("password"));
        loginPage.loginButton.click();

        Thread.sleep(500);
        String xpath = String.format("//option[@value='%s']", departmentId);
        WebElement element = driver.findElement(By.xpath(xpath));
        Assert.assertEquals(element.getAttribute("value"), String.valueOf(departmentId));

    }

    @When("User sends GET API call to validate department is exists in department list")
    public void getDepartmentFromList() {
        response = given().baseUri(ConfigReader.getProperty("HRApiBaseURL")).
                and().header("Accept", "application/json").
                when().get("/api/departments");
        Assert.assertTrue(response.body().asString().contains(String.valueOf(departmentId)));
    }

    @When("User sends GET API call to validate department is created")
    public void getDepartmentById() {
        response = given().baseUri(ConfigReader.getProperty("HRApiBaseURL")).
                and().header("Accept", "application/json").
                when().get("/api/departments/" + departmentId);

        int responseDepartmentId = response.body().jsonPath().get("departmentId");
        Assert.assertEquals(departmentId, responseDepartmentId);
    }

    @Then("User validates response status code is {int}")
    public void validateResponseStatusCode(int statusCode) {
        Assert.assertEquals(statusCode, response.statusCode());
    }

    @When("User sends DELETE API call to delete department")
    public void user_sends_delete_api_call_to_delete_department() {
        response = given().baseUri(ConfigReader.getProperty("HRApiBaseURL")).
                and().header("Accept", "application/json").
                when().delete("/api/departments/" + departmentId);
    }

    @Then("User validates DELETE call status code is {int}")
    public void validateDeleteCallStatusCode(int statusCode) {
        Assert.assertEquals(statusCode, response.statusCode());
    }

    @Then("User validates department is not shown in UI dropdown")
    public void user_validates_department_is_not_shown_in_ui_dropdown() throws InterruptedException {
        Thread.sleep(500);
        DepartmentHomePage homePage = new DepartmentHomePage(driver);
        Select select = new Select(homePage.departmentDropDown);
        List<Integer> values = new ArrayList<>();
        for (WebElement element : select.getOptions()) {
            values.add(Integer.parseInt(element.getAttribute("value")));
        }
        Assert.assertTrue(values.contains(departmentId));
    }

    @When("User sends GET API call to validate department is deleted")
    public void user_sends_get_api_call_to_validate_department_is_deleted() {
        response = given().baseUri(ConfigReader.getProperty("HRApiBaseURL")).
                and().header("Accept", "application/json").
                when().get("/api/departments/" + departmentId);
    }

    @Then("User validates GET status code is {int}")
    public void validateGetStatusCode(int statusCode) {
        Assert.assertEquals(statusCode, response.statusCode());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}