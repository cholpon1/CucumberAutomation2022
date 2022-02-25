package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.eo.Do;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.EtsyAppSearchPage;
import pages.EtsyHomePage;
import utilities.ConfigReader;
import utilities.Driver;

import java.util.List;
import java.util.Locale;

public class EtsyAppSteps {

    WebDriver driver= Driver.getDriver();
    EtsyHomePage etsyHomePage=new EtsyHomePage();
    EtsyAppSearchPage etsyAppSearchPage=new EtsyAppSearchPage();

    @Given("user navigates to Etsy application")
    public void user_navigates_to_Etsy_application() {
        driver.get(ConfigReader.getProperty("EtsyAppURL"));

    }

    @When("user searches for {string}")
    public void user_searches_for(String carpet) {
        etsyHomePage.searchBox.sendKeys(carpet+ Keys.ENTER);

    }
    @When("user applies price filter over {int}")
    public void user_applies_price_filter_over(Integer price) {
        etsyHomePage.expandFilters.click();
        etsyAppSearchPage.over1000Filter.click();
        etsyAppSearchPage.submitButton.click();

    }

    @Then("user validates that listed product names are containing the word {string} and user validates that item prices are over {int}")
    public void userValidatesThatListedProductNamesAreContainingTheWordAndUserValidatesThatItemPricesAreOver(String item, int price) throws InterruptedException {
        Thread.sleep(3000);
        List<WebElement> pricesList=  etsyAppSearchPage.pricesList;

        for(WebElement element: pricesList){
            // System.out.println(element.getText());
            String priceStr=element.getText().replace(",","");
            double doublePrice= Double.parseDouble(priceStr);
            //  System.out.println(doublePrice);
            Assert.assertTrue(doublePrice>=price);
        }
        StringBuilder newItemNames=new StringBuilder();
        Thread.sleep(3000);
        List<WebElement> itemNames=etsyAppSearchPage.itemNamesList;
        for(WebElement element: itemNames){
            if(element.getText().toLowerCase().contains(item) || element.getText().toLowerCase().contains("rug"));
            newItemNames.append(element.getText());
        }
        Assert.assertTrue(newItemNames.toString().toLowerCase().contains(item) || newItemNames.toString().toLowerCase().contains("rug"));
    }




    @When("user clicks on {string} section")
    public void userClicksOnSection(String section) {

        if(section.equalsIgnoreCase("Jewelry and Accessories")){
            etsyHomePage.jewelrySection.click();
        }else if(section.equalsIgnoreCase("End of Year Sales Event")){
            etsyHomePage.endOfYearSection.click();
        }else if(section.equalsIgnoreCase("Clothing and shoes")){
            etsyHomePage.clothingAndShoesSection.click();
        }else if(section.equalsIgnoreCase("Home and Living")){
            etsyHomePage.homeAndLivingSection.click();
        }else if(section.equalsIgnoreCase("Wedding and Party")){
            etsyHomePage.weddingAndPartySection.click();
        }else if(section.equalsIgnoreCase("Toys and Entertainment")){
            etsyHomePage.toysAndEntertainmentSection.click();
        }else if(section.equalsIgnoreCase("Art and Collectibles")){
            etsyHomePage.artAndCollectiblesSection.click();
        }else if(section.equalsIgnoreCase("Craft Supplies and Tools")){
            etsyHomePage.craftSuppliesSection.click();
        }else if(section.equalsIgnoreCase("Gifts and Gift Cards ")){
            etsyHomePage.giftsAndGiftCardsSection.click();
        }

    }

    @Then("user validates that title is {string}")
    public void userValidatesThatTitleIs(String expectedTitle) {
        String actualTitle=driver.getTitle();
        Assert.assertEquals(actualTitle,expectedTitle);
    }







}
