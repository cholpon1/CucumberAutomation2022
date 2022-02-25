package pages;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

    public class HRAppHomePage {

        public HRAppHomePage() {
            WebDriver driver = Driver.getDriver();
            PageFactory.initElements(driver, this);
        }

        @FindBy(xpath = "//input[@placeholder='Employee ID..']")
        public WebElement searchBox;

        @FindBy(xpath = "//td[2]")
        public WebElement firstName;

        @FindBy(xpath = "//td[3]")
        public WebElement lastName;

        @FindBy(xpath = "//td[4]")
        public WebElement departmentName;

        @FindBy(id = "searchButton")
        public WebElement searchButton;

        @FindBy(id = "department")
        public WebElement departmentsDropdown;
    }


