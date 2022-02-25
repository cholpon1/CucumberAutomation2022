package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en_old.Ac;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.PorscheAppPage;
import utilities.ConfigReader;
import utilities.Driver;

import java.util.Set;

public class PorscheAppSteps {
    WebDriver driver = Driver.getDriver();
    PorscheAppPage porscheAppPage = new PorscheAppPage();
    String listedPrice;
    String listedPrice1;
    WebDriverWait wait = new WebDriverWait(driver, 10);


    @Given("user navigates to Porsche App URL")
    public void user_navigates_to_Porsche_App_URL() {
        driver.get(ConfigReader.getProperty("PorscheAppURL"));

    }

    @When("user stores the price and selects the model {int} Cayman")
    public void user_stores_the_price_and_selects_the_model_Cayman(Integer int1) {
        listedPrice = porscheAppPage.listedPrice.getText();
        listedPrice1 = listedPrice.substring(listedPrice.indexOf("$"), listedPrice.indexOf("*")).trim().replaceAll(" ", "");

        porscheAppPage.cayman.click();


    }

    @Then("user validates Base price is matched with listed price")
    public void user_validates_Base_price_is_matched_with_listed_price() throws InterruptedException {

        Set<String> windowHandles = driver.getWindowHandles();
        String currentWindowId = driver.getWindowHandle();
        for (String id : windowHandles) {
            if (id != currentWindowId) {
                driver.switchTo().window(id);
            }
        }


        porscheAppPage.closeButton.click();

        wait.until(ExpectedConditions.elementToBeClickable(porscheAppPage.expandButton));

        porscheAppPage.expandButton.click();

        wait.until(ExpectedConditions.textToBePresentInElement(porscheAppPage.basePrice, "$60,500"));

        String basePrice1 = porscheAppPage.basePrice.getText();

        Assert.assertEquals(listedPrice1, basePrice1);

    }

    @When("user adds Power Sport Seats \\({int}-way) with Memory Package")
    public void user_adds_Power_Sport_Seats_way_with_Memory_Package(Integer int1) {
        Set<String> windowHandles = driver.getWindowHandles();
        String currentWindowId = driver.getWindowHandle();
        for (String id : windowHandles) {
            if (id != currentWindowId) {
                driver.switchTo().window(id);
            }
        }
        wait.until(ExpectedConditions.elementToBeClickable(porscheAppPage.seatsButton));
        porscheAppPage.seatsButton.click();

    }

    @Then("user validates that Price For Equipment is added and price matches")
    public void user_validates_that_Price_For_Equipment_is_added_and_price_matches() {

        wait.until(ExpectedConditions.textToBePresentInElement(porscheAppPage.seatPrice, "$2,330"));
        String seatPrice1 = porscheAppPage.seatPrice.getText();
        String seatPriceAdded = porscheAppPage.addedToCartSeatPrice.getText();
        Assert.assertEquals(seatPrice1, seatPriceAdded);

    }

}
