package utilities ;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;

public class BrowserUtils {




    public static void selectDropDownByValue(WebElement element, String value) {
        Select select = new Select(element);
        select.selectByValue(value);

    }

    public static void selectDropDownByText(WebElement element, String text) {
        Select select = new Select(element);
        select.selectByVisibleText(text);


    }



    /**
     This method will scroll the page.
     Ex:
     .scroll(300);
     */

    public static void scroll(int pixels) {
        WebDriver driver = Driver.getDriver();
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("window.scrollBy(0," + pixels + ")");
    }

    /**
    This method will hover Over to element in Browser
    Ex:
    .hoverOver(element)
     */

    public static void hoverOver(WebElement element){
        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(element).perform();

    }

    /**
    This will make sure that elements are visible.
    Method will explicitly wait for element to be visible.
     */

    public static void waitElementToBeVisible(WebElement element){
        WebDriverWait wait=new WebDriverWait(Driver.getDriver(),10);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     This will make sure that elements are clickable.
     Method will explicitly wait for element to be clickable.
     */
    public static void waitElementToBeClickable(WebElement element){
        WebDriverWait wait=new WebDriverWait(Driver.getDriver(),10);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void takeScreenshot(String name) throws IOException {
        WebDriver driver=Driver.getDriver();
        File screenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String path=System.getProperty("user.dir")+"\\src\\test\\resources\\screenshots"+name+System.currentTimeMillis()+
                ".png";
        File file=new File(path);
        FileUtils.copyFile(screenshot,file);

    }

}



