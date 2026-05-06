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
      LoginPage loginPage = new LoginPage(getDriver());
      HomePage homePage = new HomePage(getDriver());
      loginPage.provideEmail("shahnaj.khatun@testpro.io");
      loginPage.providePassword("Faizan@123");
      loginPage.clickLoginButton();
      Assert.assertTrue(homePage.getAvatarIcon().isDisplayed());

        }


    }

