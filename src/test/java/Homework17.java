import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;




  public class Homework17 extends BaseTest {
      @Test
      public void addSongToPlaylist() throws InterruptedException  {

          String expectedSongNotificationMessage ="Added 1 song into \"Test Pro Playlist.\"";
          ChromeOptions options = new ChromeOptions();
          options.addArguments("--remote-allow-origins=*");

          WebDriver driver = new ChromeDriver(options);
          driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


          CommonAction actions = new CommonAction(driver);

          //Navigate to the URL
          actions.Navigate("https://qa.koel.app/");
          //logIn
          actions.logIn("shahnaj.khatun@testpro.io", "Faizan@123");
         //search for a song
          actions.searchSong("dark days");
        //Click on view all button
          actions.clickViewAllButton();
         //Click on first song
         actions.clickFirstSong();
         //Click on add to button
          actions.clickAddToButton();
          String notification = actions.clickTestProPlaylist();
        Assert.assertEquals(notification, expectedSongNotificationMessage);




      }
  }