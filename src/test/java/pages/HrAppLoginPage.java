package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class HrAppLoginPage {
    public HrAppLoginPage(){
        WebDriver driver=Driver.getDriver();
        PageFactory.initElements(driver,this);
    }


    @FindBy(name = "username")
    public WebElement username;

    @FindBy(name = "password")
    public WebElement password;

    @FindBy(xpath = "//button[@class='btn btn-success']")
    public WebElement loginButton;

    @FindBy(xpath = "//div[@class='alert alert-waring']")
    public WebElement errorMessage;

    @FindBy(xpath = "//a[@href='/logout']")
    public WebElement logoutButton;

    @FindBy(tagName = "h1")
    public WebElement actualMessage;

    @FindBy(xpath = "(//button[@class='btn btn-success'])[2]")
    public WebElement editButton;

    @FindBy(xpath = "//input[@name='firstName']")
    public WebElement firstName;

    @FindBy(xpath = "//input[@name='lastName']")
    public WebElement lastName;

    @FindBy(id="department")
    public WebElement department;

    @FindBy(id="job")
    public WebElement jobTitle;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement saveButton;

    @FindBy(xpath = "(//button[@class='btn btn-warning'])[2]")
    public WebElement deleteButton;

    @FindBy(xpath = "(//td[@_ngcontent-c2])[2]")
    public WebElement firstNameValidation;

    @FindBy(xpath = "(//td[@_ngcontent-c2])[3]")
    public WebElement lastNameValidation;

    @FindBy(xpath = "(//td[@_ngcontent-c2])[4]")
    public WebElement departmentValidation;

    @FindBy(xpath = "(//td[@_ngcontent-c2])[7]")
    public WebElement idToDelete;

    @FindBy(xpath = "/html/body/app-root/div[1]/app-employee-details/div[2]")
    public WebElement successfullyDeletedMessage;

    @FindBy(xpath = "//input[@placeholder='Employee ID..']")
    public WebElement employeeIdSearch;

    @FindBy(id="searchButton")
    public WebElement employeeSearchButton;

    @FindBy(xpath = "(//td)[1]")
    public WebElement employeeID;

    @FindBy(xpath = "(//td)[2]")
    public WebElement employeeFirstName;

    @FindBy(xpath = "(//td)[3]")
    public  WebElement employeeLastName;

    @FindBy(xpath = "(//td)[4]")
    public WebElement employeeDepartmentName;

    @FindBy(id = "department")
    public WebElement departmentDropDown;


    @FindBy(xpath = "//input[@name='username']")
    public WebElement login;


}
