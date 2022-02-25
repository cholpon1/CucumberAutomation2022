package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HrAppLoginPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.JDBCUtils;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class HrAppProjectDaySteps {
    WebDriver driver= Driver.getDriver();
    HrAppLoginPage hrAppLoginPage= new HrAppLoginPage();




    @Given("user navigates to HrApp")
    public void user_navigates_to_HrApp() {

        driver.get(ConfigReader.getProperty("HrAppURL"));

    }


    @When("user logs in with username {string} and password {string}")
    public void user_logs_in_with_username_and_password(String username, String password) {
        hrAppLoginPage.username.sendKeys(username);
        hrAppLoginPage.password.sendKeys(password);
        hrAppLoginPage.loginButton.click();
    }



    @When("user search for employee with employee id {string}")
    public void user_search_for_employee_with_employee_id(String employeeId) throws InterruptedException {

        WebDriverWait wait=new WebDriverWait(Driver.getDriver(),10);
        wait.until(ExpectedConditions.elementToBeClickable(hrAppLoginPage.employeeIdSearch));
        Thread.sleep(3000);
        hrAppLoginPage.employeeIdSearch.clear();



        hrAppLoginPage.employeeIdSearch.sendKeys(employeeId);
        hrAppLoginPage.employeeSearchButton.click();
    }



    @Then("user validates that {string} data in UI matches with Database data")
    public void user_validates_that_data_in_UI_matches_with_Database_data(String employeeId) throws SQLException {

        JDBCUtils.establishConnection();

        String query="SELECT e.employee_id, e.first_name, e.last_name, d.department_name FROM employees e\n" +
                "JOIN departments d ON e.department_id= d.department_id WHERE employee_id="+employeeId;

        List<Map<String, Object>> data= JDBCUtils.runQuery(query);

        JDBCUtils.closeDatabase();

        Assert.assertEquals(data.get(0).get("employee_id").toString(), hrAppLoginPage.employeeID.getText());
        Assert.assertEquals(data.get(0).get("first_name").toString(),hrAppLoginPage.employeeFirstName.getText());
        Assert.assertEquals(data.get(0).get("last_name").toString(),hrAppLoginPage.employeeLastName.getText());
        Assert.assertEquals(data.get(0).get("department_name").toString(),hrAppLoginPage.employeeDepartmentName.getText());

    }


}
