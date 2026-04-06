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
          String url = "https://qa.koel.app/";
          driver.get(url);

          WebElement emailField = driver.findElement(By.xpath("//input[@type='email']"));
          emailField.click();
          emailField.clear();
          emailField.sendKeys("shahnaj.khatun@testpro.io");

          WebElement passwordField =driver.findElement(By.xpath("//input[@type='password']"));
          passwordField.click();
          passwordField.clear();
          passwordField.sendKeys("Faizan@123");

          WebElement loginButton =driver.findElement(By.xpath("//button[@type='submit']"));
          loginButton.click();

         //search for a song
          WebElement searchField =driver.findElement(By.xpath("//input[@name='q']"));
          searchField.click();
          searchField.clear();
          searchField.sendKeys("dark days");

          WebElement viewAllButton =driver.findElement(By.xpath("//button[@data-test='view-all-songs-btn']"));
          viewAllButton.click();

          WebElement firstSong = driver.findElement(By.xpath("//section[@id='songResultsWrapper']//table[@class='items']//tr[1]"));
          firstSong.click();

          WebElement addToButton = driver.findElement(By.xpath("//button[@class='btn-add-to']"));
          addToButton.click();

          //WebElement playlistBtn = driver.findElement(By.xpath("//section[@class='existing-playlists]//licontains(text(),'Test Pro Playlist')]"));
          // WebElement playlistBtn = driver.findElement(By.xpath("(//section[@class='existing-playlists']//li[@class='playlist'])[1]"));
          System.out.println("Waiting for playlist element...");

           WebElement playlistBtn = driver.findElement(By.xpath("//section[@id='songResultsWrapper']//li[contains(text(), 'Test Pro Playlist')]"));

          WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
//          WebElement playlistBtn = wait.until(
//                  ExpectedConditions.visibilityOfElementLocated(
//                          By.xpath("//section[@id='songResultsWrapper']//li[contains(text(), 'Test Pro Playlist')]")
//                  )
//          );

          playlistBtn.click();

//          WebElement notification = driver.findElement(By.xpath("//div[contains(@class,'success') and contains(@class,'show')]"));

          System.out.println("Element found, clicking now...");
          WebElement notification = wait.until(
                  ExpectedConditions.visibilityOfElementLocated(
                          By.xpath("//div[contains(@class,'success') and contains(@class,'show')]")
                  )
          );
          String message = notification.getText();
          System.out.println("message" + message);
        Assert.assertEquals(message, expectedSongNotificationMessage);




      }
  }