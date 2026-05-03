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

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;

public class BaseTest {
    public static WebDriver driver;
    public String url;
    public static WebDriverWait wait = null;

    public static Actions actions;

    private static final ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();

    public static WebDriver getDriver(){
        return threadDriver.get();
    }
   /* @BeforeSuite
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();
        WebDriverManager.safaridriver().setup();

    }*/


    @BeforeMethod
    @Parameters({"BaseURL"})
    public void launchBrowser(String BaseURL, Method method) throws MalformedURLException {
        //Added ChromeOptions argument below to fix websocket error
       // ChromeOptions options = new ChromeOptions();
        //options.addArguments("--remote-allow-origins=*");
        //Pre-condition
        // driver = new ChromeDriver(options);
        //  driver = new FirefoxDriver();
        //driver = new SafariDriver();
        threadDriver.set(pickBrowser(System.getProperty("browser"),method));
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        getDriver().manage().window().maximize();
        actions= new Actions(getDriver());
        url = BaseURL;
        wait = new WebDriverWait(getDriver(), Duration.ofSeconds(40));
        navigateToPage();
    }

    @AfterMethod
    public void tearDown(){
        threadDriver.get().close();
        threadDriver.remove();
    }
//    public void closeBrowser() {
//        getDriver().quit();
//    }

    public void navigateToPage() {
        getDriver().get(url);
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

    public static WebDriver pickBrowser(String browser, Method method ) throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        String gridURL = "http://localhost:4444";
        switch (browser) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                return driver = new FirefoxDriver();
            case "safari":
                WebDriverManager.safaridriver().setup();
                return driver = new SafariDriver();
            //Grid Capable Browsers
            case "grid-firefox":
                //caps.setCapability("browserName","firefox");
                //return driver =new RemoteWebDriver(URI.create(gridURL).toURL(),caps);
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                return new RemoteWebDriver(URI.create(gridURL).toURL(), firefoxOptions);
            case "grid-safari":
                SafariOptions safariOptions = new SafariOptions();
                return new RemoteWebDriver(URI.create(gridURL).toURL(), safariOptions);
            case "grid-chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                return new RemoteWebDriver(URI.create(gridURL).toURL(), chromeOptions);
            //Cloud Execution
            case "cloud-chrome":
               return lambdaTest(method);
            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions browserOptions = new ChromeOptions();
                browserOptions.addArguments("--remote-allow-origins=*");
                return driver = new ChromeDriver(browserOptions);
        }
    }

     public static WebDriver lambdaTest(Method method) throws MalformedURLException{
         String hubURL = "https://hub.lambdatest.com/wd/hub";

         ChromeOptions browserOptions = new ChromeOptions();
         browserOptions.setPlatformName("Windows 10");
         browserOptions.setBrowserVersion("dev");
         HashMap<String, Object> ltOptions = new HashMap<>();
         ltOptions.put("username", "shahnajkhatun117");
         ltOptions.put("accessKey", "LT_deTm6W9rHJ1uF57MlG6Z1Jh0rzQNVClyCGHv7pQshsbHbrw");
         ltOptions.put("project", "Untitled");
         //Build Name
         ltOptions.put("build","TestPro Demo Suite - " + LocalDateTime.now());
         //Test Name inside the class
         String testName = method.getDeclaringClass().getSimpleName() + "-"+method.getName();
         ltOptions.put("name" , testName);

         ltOptions.put("selenium_version", "4.0.0");
         ltOptions.put("w3c", true);
         browserOptions.setCapability("LT:Options", ltOptions);

         return new RemoteWebDriver(new URL(hubURL),browserOptions);
     }
    }

