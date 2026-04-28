import Pages.HomePage;
import Pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTests extends BaseTest {
    @Test
    public void loginEmptyEmailPassword() {
        navigateToPage();
        provideEmail("");
        providePassword("");
    }

   @Test
   public void loginCorrectEmailandPassword(){
      LoginPage loginpage = new LoginPage(driver);
      HomePage homepage = new HomePage(driver);
      loginpage.provideEmail("shahnaj.khatun@testpro.io");
      loginpage.providePassword("Faizan@123");
      clickLoginButton();
      Assert.assertTrue(homepage.getAvatarIcon().isDisplayed());

        }


    }

