import Pages.HomePage;
import Pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Homework21 extends BaseTest{
    String newRenamePlaylist = "Sample Edited Playlist";
    @Test
    public void renamePlaylist(){
        String updatedMsg ="Updated playlist \"Sample Edited Playlist.\"";
        LoginPage loginpage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        loginpage .provideEmail("shahnaj.khatun@testpro.io");
        loginpage .providePassword("Faizan@123");
        clickLoginButton();
        homePage.dbClickPlaylist();
        homePage.enterNewPlaylist();
      Assert.assertEquals(homePage.getRenamePlaylistSuccessMsg(),updatedMsg);

    }

    }






