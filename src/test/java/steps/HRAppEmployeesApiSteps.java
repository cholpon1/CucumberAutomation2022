package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.HrAppLoginPage;
import pojos.hr_api_pojos.Department;
import pojos.hr_api_pojos.Employee;
import pojos.hr_api_pojos.Job;
import pojos.hr_api_pojos.Location;
import utilities.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class HRAppEmployeesApiSteps {

    WebDriver driver= Driver.getDriver();
    HrAppLoginPage hrAppLoginPage=new HrAppLoginPage();
    List<Map<String,Object>> data;
    Response response;
    String employeeId;
    Integer numberOfDepartmentEmployeesDB;


    @Given("user gets employee from database with employeeId {int}")
    public void user_gets_employee_from_database_with_employeeId(Integer employeeId) throws SQLException {
        JDBCUtils.establishConnection();

        String query="SELECT e.employee_id, e.first_name, e.last_name, d.department_name FROM employees e\n" +
                "JOIN departments d ON e.department_id= d.department_id WHERE employee_id="+employeeId;

         data =JDBCUtils.runQuery(query);

    }

    @When("user search for employee with employee id {int}")
    public void user_search_for_employee_with_employee_id(Integer employeeId) {
        //hrAppLoginPage.employeeIdSearch.clear();
        hrAppLoginPage.employeeIdSearch.sendKeys(employeeId.toString());
        hrAppLoginPage.employeeSearchButton.click();

    }

    @Then("user validates that {int} data in UI matches with Database data")
    public void user_validates_that_data_in_UI_matches_with_Database_data(Integer employeeId) {
        String uiFirstName=hrAppLoginPage.employeeFirstName.getText();
        String uiLastName=hrAppLoginPage.employeeLastName.getText();
        String uiDepartmentName=hrAppLoginPage.employeeDepartmentName.getText();

        Assert.assertEquals(data.get(0).get("first_name"),uiFirstName);
        Assert.assertEquals(data.get(0).get("last_name"),uiLastName);
        Assert.assertEquals(data.get(0).get("department_name"),uiDepartmentName);

    }

    @When("user GETS employee with {int}")
    public void user_GETS_employee_with(Integer employeeId) {
        response=
                given().baseUri(ConfigReader.getProperty("HRApiBaseURL"))
                        .and().header("Accept","application/json")
                        .when().get("/api/employees/"+employeeId);


    }

    @Then("user validates API data matched with DB data")
    public void user_validates_API_data_matched_with_DB_data() {
        String apiFirstName=response.body().jsonPath().getString("firstName");
        String apiLastName=response.body().jsonPath().getString("lastName");
        String apiDepartmentName=response.body().jsonPath().getString("department.departmentName"); // IMPORTANT NOTE!!!

        Assert.assertEquals(data.get(0).get("first_name"),apiFirstName);
        Assert.assertEquals(data.get(0).get("last_name"),apiLastName);
        Assert.assertEquals(data.get(0).get("department_name"),apiDepartmentName);

    }
    @Given("user create employee with post api call with data")
    public void user_create_employee_with_post_api_call_with_data(io.cucumber.datatable.DataTable dataTable) {
        Map<String, Object> data = dataTable.asMap(String.class, Object.class);
        // LOMBOK >> generates Getters/Setters
        /*
        Request
        1. URL
        2. Headers
        3. Body
         */
        Employee employee = new Employee();
        Department department = new Department();
        department.setDepartmentId(10);
        department.setDepartmentName(data.get("departmentName").toString()); // get from dataTable(feature)
        Location location = new Location();
        location.setLocationCity("MAUI");
        location.setLocationCountry("US");
        location.setLocationId(333);
        location.setLocationState("HI");
        department.setLocation(location);
        employee.setDepartment(department);
        employee.setFirstName(data.get("firstName").toString());
        employee.setEmployeeId(0);
        Job job = new Job();
        job.setJobId("IT_PROG");
        job.setSalary(100000.0);
        job.setTitle("SDET");
        employee.setJob(job);
        employee.setLastName(data.get("lastName").toString());

        response =
                given().baseUri(ConfigReader.getProperty("HRApiBaseURL"))
                        .and().header("Content-Type", "application/json")
                        .and().header("Accept", "application/json")
                        .and().body(employee) // > serilization takes object from pojo
                        .when().post("/api/employees");

        employeeId = response.body().jsonPath().getString("employeeId");



    }

    @When("user searches for created employee")
    public void user_searches_for_created_employee() {
    hrAppLoginPage.employeeIdSearch.sendKeys(employeeId);
        hrAppLoginPage.employeeSearchButton.click();

    }

    @Then("user validates employee is created in UI with data")
    public void user_validates_employee_is_created_in_UI_with_data(io.cucumber.datatable.DataTable dataTable) {
        String uiFirstName = hrAppLoginPage.employeeFirstName.getText();
        String uiLastName = hrAppLoginPage.employeeLastName.getText();
        String uiDepartmentName = hrAppLoginPage.employeeDepartmentName.getText();

        Map<String, Object> data=dataTable.asMap(String.class, Object.class);
        String expectedFirstName = data.get("firstName").toString();
        String expectedLastName = data.get("lastName").toString();
        String expectedDepartmentName = data.get("departmentName").toString();

        Assert.assertEquals(expectedDepartmentName, uiDepartmentName);
        Assert.assertEquals(expectedLastName, uiLastName);
        Assert.assertEquals(expectedFirstName, uiFirstName);

    }
    @Given("user gets number of employees in {string} department from DB")
    public void user_gets_number_of_employees_in_department_from_DB(String departmentName) throws SQLException {
        JDBCUtils.establishConnection();
//        long query = JDBCUtils.runQuery("SELECT COUNT(employee_id) \n" +
//                "FROM employees e JOIN departments d\n" +
//                "ON e.department_id = d.department_id\n" +
//                "WHERE department_name='"+departmentName+"'").stream().count(); // will return list of Maps
//        System.out.println(query);

        data = JDBCUtils.runQuery("SELECT COUNT(employee_id) \n" +
                "FROM employees e JOIN departments d\n" +
                "ON e.department_id = d.department_id\n" +
                "WHERE department_name='"+departmentName+"'"); // will return list of Maps
        numberOfDepartmentEmployeesDB =Integer.parseInt(data.get(0).get("count").toString());
    }

    @When("user selects {string} department from dropdown")
    public void user_selects_department_from_dropdown(String departmentName) throws InterruptedException {
        BrowserUtils.selectDropDownByText(hrAppLoginPage.departmentDropDown, departmentName);
        Thread.sleep(2000);
    }

    @Then("user validates UI number of employees matches with DB number")
    public void user_validates_UI_number_of_employees_matches_with_DB_number() {
        Integer numberOfDepartmentEmployeesUI = Driver.getDriver().findElements(By.xpath("//tr")).size()-1; // get the number of rows from UI
        Assert.assertEquals(numberOfDepartmentEmployeesDB,numberOfDepartmentEmployeesUI);
    }

    @When("user gets  employees from {string} department with api call")
    public void user_gets_employees_from_department_with_api_call(String departmentName) throws SQLException {
        JDBCUtils.establishConnection();
        String departmentID= JDBCUtils.runQuery("select department_id \n" +  // to get dep_ID
                "from departments \n" +
                "where department_name = '"+departmentName+"'").get(0).get("department_id").toString();
        response =
                given().baseUri(ConfigReader.getProperty("HRApiBaseURL"))
                        .and().header("Accept", "application/json")
                        .and().get("/api/departments/"+departmentID+"0/employees");
    }

    @Then("user validates number of employees in API matches with DB number")
    public void user_validates_number_of_employees_in_API_matches_with_DB_number() {
        Employee[] employees = response.body().as(Employee[].class); // from JSON>POJO deserialization
        Integer numberOfDepartmentEmployeesAPI = employees.length;

        Assert.assertEquals(numberOfDepartmentEmployeesDB, numberOfDepartmentEmployeesAPI);


    }


}
