//import com.sun.javafx.PlatformUtil;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class HotelBookingTest {

    @FindBy(linkText = "Hotels")
    private WebElement hotelLink;

    @FindBy(id = "Tags")
    private WebElement localityTextBox;

    @FindBy(id = "SearchHotelsButton")
    private WebElement searchButton;

    @FindBy(id = "travellersOnhome")
    private WebElement travellerSelection;


    WebDriver driver;
    @BeforeTest
    public void setup(){
        setDriverPath();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        PageFactory.initElements(driver, this);
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
    public void shouldBeAbleToSearchForHotels() {

        driver.get("https://www.cleartrip.com/");
        //javaClick(hotelLink);
        hotelLink.click();
        localityTextBox.sendKeys("Indiranagar, Bangalore");

        new Select(travellerSelection).selectByVisibleText("1 room, 2 adults");
        searchButton.click();
    }

    private void javaClick(WebElement element){
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();",element);
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
