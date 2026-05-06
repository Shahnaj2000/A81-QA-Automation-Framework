package stepDefinitions;

import POM.HomePage;
import POM.LoginPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class LoginStepDefinitions {
    WebDriver driver;
    WebDriverWait  wait;
    //@Given("I open the browser")
    @Before
    public void openBrowser(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
       wait = new WebDriverWait(driver, Duration.ofSeconds(50));
    }
    //@And("I am in koel login page")
    @Given("I am in koel login page")
    public void iAmInKoelLoginPag () {
        // Write code here that turns the phrase above into concrete actions
        driver.get("https://qa.koel.app/");
    }

    @When("I input email {string}")
    public void iInputEmail(String email) {
        // Write code here that turns the phrase above into concrete actions
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='email']"))).sendKeys(email);
        LoginPage loginPage =new LoginPage(driver);
        loginPage.provideEmail(email);
    }

    @And("I input password {string}")
    public void iInputPassword(String password) {
        // Write code here that turns the phrase above into concrete actions
       // wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='password']"))).sendKeys(password);
        LoginPage loginPage =new LoginPage(driver);
        loginPage.providePassword(password);
    }


    @And("I click log In")
    public void iClickLogIn() {
        // Write code here that turns the phrase above into concrete actions
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']"))).click();
        LoginPage loginPage =new LoginPage(driver);
        loginPage.clickLoginButton();
    }

    @Then("I am Logged in")
    public void iAmLoggedIn() {
        // Write code here that turns the phrase above into concrete actions
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("img.avatar"))).isDisplayed();
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.getAvatarIcon().isDisplayed());
    }
    @After
    public void closeBrowser(){
        driver.quit();
    }
}
