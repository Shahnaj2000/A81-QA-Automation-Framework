import POM.HomePage;
import POM.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

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
      loginpage.clickLoginButton();
      Assert.assertTrue(homepage.getAvatarIcon().isDisplayed());

        }


    }

