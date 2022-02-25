package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class PorscheAppPage {
    public PorscheAppPage(){
        WebDriver driver= Driver.getDriver();
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//img[@alt='Porsche 718 Cayman']")
    public WebElement cayman;

    @FindBy(xpath = "(//span[@class='close-button'])[2]")
    public WebElement closeButton;


    @FindBy(xpath = "(//div[@class='m-14-model-price'])[1]")
    public WebElement listedPrice;

    @FindBy(xpath = "(//section[@id='s_iccCca']//div[@class='cca-price'])[3]")
    public WebElement basePrice;

    @FindBy(xpath = "(//section[@id='s_iccCca']//span[@class='expandButton'])[2]")
    public WebElement expandButton;

    @FindBy(id="s_interieur_x_73_x_PP06_x_shorttext")
    public WebElement seatsButton;

    @FindBy(xpath = "(//div[contains(text(),'$2,330')])[2]")
    public WebElement seatPrice;

    @FindBy(xpath = "(//div[contains(text(),'$2,330')])[1]")
    public WebElement addedToCartSeatPrice;









}
