import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;
import java.time.Duration;



public class Homework16Test extends BaseTest {
    @Test
    public void registrationNavigation() {
        //Added ChromeOptions argument below to fix websocket error
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        //Pre-condition
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        //Opening browser
        String url = "https://qa.koel.app/";

        driver.get(url);

        //Locate web element for registration field by xpath
        WebElement registrationLink = driver.findElement(By.xpath("//a[@href='registration'"));
        registrationLink.click();

        //Comparing the registrationUrl with the current driver registration link
        String registrationUrl = "https//qa.koel.app/registration";
        Assert.assertEquals(driver.getCurrentUrl(), registrationUrl);

        //Close the browser
        driver.quit();
    }
}





