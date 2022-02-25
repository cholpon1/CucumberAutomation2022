package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import pages.WebOrdersHomePage;
import pages.WebOrdersLoginPage;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.Driver;

import java.io.ObjectInputFilter;

public class WebOrdersSteps {

    WebDriver driver =Driver.getDriver();
    WebOrdersLoginPage webOrdersLoginPage=new WebOrdersLoginPage();
    WebOrdersHomePage webOrdersHomePage=new WebOrdersHomePage();

    @Given("user navigates to the WebOrders Application")
    public void user_navigates_to_the_WebOrders_Application() {
        driver.get(ConfigReader.getProperty("WebOrderURL"));

    }

    @When("user provides username {string} and password {string} clicks on login button")
    public void user_provides_username_and_password_clicks_on_login_button(String username, String password) {

        webOrdersLoginPage.username.sendKeys(username);
        webOrdersLoginPage.password.sendKeys(password);
        webOrdersLoginPage.loginButton.click();

    }

    @Then("user validates application is logged in")
    public void user_validates_application_is_logged_in() {
        String actualTitle= driver.getTitle();
        String expectedTitle="Web Orders";
        Assert.assertEquals(expectedTitle,actualTitle);

    }

    @Then("user validates error message {string}")
    public void user_validates_error_message(String errorMessage) {
        String actualErrorMessage=webOrdersLoginPage.errorMessage.getText();
        Assert.assertEquals(errorMessage,actualErrorMessage);

    }


    @And("user clicks on Order module")
    public void userClicksOnOrderModule() {
        webOrdersHomePage.orderModule.click();
        
    }

    @And("user selects {string} product with {int} quantity")
    public void userSelectsProductWithQuantityQuantity(String product, int quantity) {
        BrowserUtils.selectDropDownByValue(webOrdersHomePage.productDropDown,product);
        //webOrdersHomePage.quantityEnter.clear(); or:
        webOrdersHomePage.quantityEnter.sendKeys(Keys.BACK_SPACE);
        webOrdersHomePage.quantityEnter.sendKeys(quantity+""+Keys.ENTER);
        
    }




    @Then("user validates total is calculated correctly for quantity {int}")
    public void userValidatesTotalIsCalculatedCorrectlyForQuantityQuantity(int quantity) {
        String pricePerUnit=webOrdersHomePage.pricePerUnit.getAttribute("value");
        String discount=webOrdersHomePage.discountAmount.getAttribute("value");
        int expectedTotal=0;
        String actualTotal=webOrdersHomePage.actualTotal.getAttribute("value");
        int discountInt=Integer.parseInt(discount);
        int pricePerUnitInt=Integer.parseInt(pricePerUnit);
        int actualTotalInt=Integer.parseInt(actualTotal);

        if(discountInt==0){
            expectedTotal=quantity*pricePerUnitInt;
        }else{
            expectedTotal=quantity*pricePerUnitInt;
            expectedTotal=expectedTotal-expectedTotal*discountInt/100;
        }

        Assert.assertEquals(actualTotalInt,expectedTotal);

    }



}
