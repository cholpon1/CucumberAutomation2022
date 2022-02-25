package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.HrAppLoginPage;
import pages.HrAppNewEmployeePage;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.JDBCUtils;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class HrAppBackEndSteps {

    WebDriver driver= Driver.getDriver();
    HrAppLoginPage hrAppLoginPage= new HrAppLoginPage();
    HrAppNewEmployeePage hrAppNewEmployeePage= new HrAppNewEmployeePage();

    @Given("User navigates to HR App Login Page")
    public void user_navigates_to_HR_App_Login_Page() {
        driver.get(ConfigReader.getProperty("HrAppURL"));
        Assert.assertEquals("HrApp",driver.getTitle());
    }

    @When("user logs in to HR App")
    public void user_logs_in_to_HR_App() {
        hrAppLoginPage.username.sendKeys("Mindtek");
        hrAppLoginPage.password.sendKeys("MindtekStudent");
        hrAppLoginPage.loginButton.click();

    }

    @When("creates the new employee")
    public void creates_the_new_employee() {
        System.out.println("New employee Steven was added");


    }

    @Then("user validates new employee is created in database")
    public void user_validates_new_employee_is_created_in_database() throws SQLException {

        //String actualFirstName=hrAppNewEmployeePage.firstName1.getText();
        String actualLastName=hrAppNewEmployeePage.lastName1.getText();
        String actualDepartmentName=hrAppNewEmployeePage.departmentName.getText();

        String query="SELECT e.first_name, e.last_name, d.department_name FROM employees e\n" +
                "JOIN departments d ON e.department_id= d.department_id WHERE e.employee_id=100";

        JDBCUtils.establishConnection();
        List<Map<String,Object>> data=JDBCUtils.runQuery(query);

        JDBCUtils.closeDatabase();

        Assert.assertEquals(data.get(0).get("first_name"),hrAppNewEmployeePage.firstName1.getText());
        Assert.assertEquals(data.get(0).get("last_name").toString(),actualLastName);
        Assert.assertEquals(data.get(0).get("department_name").toString(),actualDepartmentName);

        System.out.println(data.get(0).get("department_name") +actualDepartmentName);





    }

}
