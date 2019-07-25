//import com.sun.javafx.PlatformUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.hamcrest.core.StringStartsWith.startsWith;

public class SignInTest {

    WebDriver driver;
    @BeforeTest
    public void setup(){
        setDriverPath();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterSuite
    public void tearDown() throws InterruptedException {
        if(driver != null) {
            driver.close();
            driver.quit();
            driver=null;
        }
    }

    @Test
    public void shouldThrowAnErrorIfSignInDetailsAreMissing() {

        driver.get("https://www.cleartrip.com/");
        waitFor(2000);

        driver.findElement(By.linkText("Your trips")).click();

        driver.findElement(By.id("SignIn")).click();
        waitFor(2000);
        driver.switchTo().frame("modal_window");
        driver.findElement(By.id("signInButton")).click();

        String errors1 = driver.findElement(By.id("errors1")).getText();
        driver.switchTo().defaultContent();
        Assert.assertTrue(errors1.contains("There were errors in your submission"));

    }

    private void waitFor(int durationInMilliSeconds) {
        try {
            Thread.sleep(durationInMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    private void setDriverPath() {
        if (System.getProperty("os.name").startsWith("Mac")) {
            System.setProperty("webdriver.chrome.driver", "chromedriver");
        }
        if (System.getProperty("os.name").startsWith("Windows")){
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        }
        if (System.getProperty("os.name").startsWith("Linux")){
            System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
        }
    }


}
