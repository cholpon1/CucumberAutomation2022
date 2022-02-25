package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utilities.Driver;

import java.util.concurrent.TimeUnit;

public class Hooks {

    WebDriver driver= Driver.getDriver();

    @Before
    public void setUp(){

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Before");
    }

    @After
    public void tearDown(Scenario scenario) throws InterruptedException {
        if(scenario.isFailed()){
            byte[] screenShot=((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenShot,"image/png");

        }

        Thread.sleep(3000);
        driver.quit();
        System.out.println("After");
    }

}
