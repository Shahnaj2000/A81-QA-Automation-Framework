import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.Duration;

public class BaseTest {
    public static WebDriver driver;
    public String url;
    public static WebDriverWait wait = null;
    public static Actions actions;

   /* @BeforeSuite
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();
        WebDriverManager.safaridriver().setup();

    }*/


    @BeforeMethod
    @Parameters({"BaseURL"})
    public void launchBrowser(String BaseURL) throws MalformedURLException {
        //Added ChromeOptions argument below to fix websocket error
       // ChromeOptions options = new ChromeOptions();
        //options.addArguments("--remote-allow-origins=*");
        //Pre-condition
        // driver = new ChromeDriver(options);
        //  driver = new FirefoxDriver();
        //driver = new SafariDriver();
        driver = pickBrowser(System.getProperty("browser"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        url = BaseURL;
        wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        navigateToPage();
    }

    @AfterMethod
    public void closeBrowser() {
        driver.quit();

    }

    public void navigateToPage() {
        driver.get(url);
    }

    public void provideEmail(String email) {
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

    public static WebDriver pickBrowser(String browser) throws MalformedURLException {
        DesiredCapabilities caps =new DesiredCapabilities();
        String gridURL="http://localhost:4444";
        switch (browser) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                return driver = new FirefoxDriver();
            case "safari":
                WebDriverManager.safaridriver().setup();
                return driver = new SafariDriver();
                //Grid Capable Browsers
            case"grid-firefox":
                //caps.setCapability("browserName","firefox");
                //return driver =new RemoteWebDriver(URI.create(gridURL).toURL(),caps);
                FirefoxOptions firefoxOptions =new FirefoxOptions();
                return new RemoteWebDriver(URI.create(gridURL).toURL(),firefoxOptions);
            case "grid-safari":
                SafariOptions safariOptions = new SafariOptions();
                return new RemoteWebDriver(URI.create(gridURL).toURL(),safariOptions);
            case "grid-chrome":
                ChromeOptions chromeOptions=new ChromeOptions();
                return new RemoteWebDriver(URI.create(gridURL).toURL(),chromeOptions);
            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions browserOptions = new ChromeOptions();
                browserOptions.addArguments("--remote-allow-origins=*");
                return driver = new ChromeDriver(browserOptions);

        }
    }
    }

