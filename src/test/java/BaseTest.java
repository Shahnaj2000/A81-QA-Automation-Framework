import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.time.Duration;

public class BaseTest {
    public WebDriver driver;
    public String url ="https://qa.koel.app/" ;
    public WebDriverWait wait = null;
    Actions actions;

    @BeforeSuite
    static void setupClass() {WebDriverManager.chromedriver().setup();
    }
    @BeforeMethod
    @Parameters({"BaseURL"})
    public void launchBrowser(String BaseURL) {
        //Added ChromeOptions argument below to fix websocket error
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        //Pre-condition
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        url=BaseURL;
        wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        navigateToPage();
        actions =new Actions(driver);
    }
    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }
    public void navigateToPage () {
        driver.get(url);
    }
    public  void provideEmail(String email ) {
        //WebElement emailField = driver.findElement(By.xpath("//input[@type='email']"));
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='email']")));
        emailField.click();
        emailField.clear();
        emailField.sendKeys(email);
    }
    public void providePassword(String password) {
        //WebElement passwordField = driver.findElement(By.xpath("//input[@type='password']"));
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='password']")));
        passwordField.click();
        passwordField.clear();
        passwordField.sendKeys(password);
    }
    public void clickLoginButton() {
       // WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));
        WebElement loginButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));
        loginButton.click();
    }
}