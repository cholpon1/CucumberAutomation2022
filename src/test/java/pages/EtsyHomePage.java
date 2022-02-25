package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class EtsyHomePage {
    public EtsyHomePage(){
        WebDriver driver= Driver.getDriver();
        PageFactory.initElements(driver,this);
    }

    @FindBy(id="global-enhancements-search-query")
    public WebElement searchBox;

    @FindBy(xpath = "//span[@class='wt-hide-xs wt-show-md filter-expander']")
    public WebElement expandFilters;

    @FindBy(xpath = "//label[@for='price-input-4']")
    public WebElement over1000Filter;

    @FindBy(id="catnav-primary-link-10855")
    public WebElement jewelrySection;

    @FindBy(xpath = "//span[@class='wt-display-table-cell wt-text-left-xs']")
    public WebElement endOfYearSection;

    @FindBy(id="catnav-primary-link-10923")
    public WebElement clothingAndShoesSection;

    @FindBy(id="catnav-primary-link-891")
    public WebElement homeAndLivingSection;

    @FindBy(id="catnav-primary-link-10983")
    public WebElement weddingAndPartySection;

    @FindBy(id="catnav-primary-link-11049")
    public WebElement toysAndEntertainmentSection;

    @FindBy(id="catnav-primary-link-66")
    public WebElement artAndCollectiblesSection;

    @FindBy(id="catnav-primary-link-562")
    public WebElement craftSuppliesSection;

    @FindBy(id="catnav-primary-link--10")
    public WebElement giftsAndGiftCardsSection;



}
