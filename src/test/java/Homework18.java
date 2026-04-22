import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.interactions.Actions;


public class Homework18 extends BaseTest  {
    @Test
    public void playSong()  {
        navigateToPage();
        provideEmail("shahnaj.khatun@testpro.io");
        providePassword("Faizan@123");
        clickLoginButton();
        clickPlay();
        Assert.assertTrue(isSongPlaying());
    }
////div[@class='side player-controls']
    public void clickPlay() {
        Actions actions = new Actions(driver);
        WebElement playerControl = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[@class='side player-controls']")
                )
        );
        actions.moveToElement(playerControl).perform();
        WebElement playButton =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='play']")));
        WebElement playNextSong = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@data-testid='play-next-btn']")));
        playNextSong.click();
        playButton.click();

    }
  public boolean isSongPlaying(){
     //WebElement soundbar=driver.findElement(By.xpath("//div[@data-testid='sound-bar-play']"));
      WebElement soundbar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-testid='sound-bar-play']")));
    return soundbar.isDisplayed();
  }

    }



