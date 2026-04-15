import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Homework18 extends BaseTest  {
    @Test
    public void playSong() throws InterruptedException {
        navigateToPage();
        provideEmail("shahnaj.khatun@testpro.io");
        providePassword("Faizan@123");
        clickLoginButton();
        clickPlay();
        Thread.sleep(2000);
        boolean isSoundBarDisplayed = isSongPlaying();
        Assert.assertTrue(isSoundBarDisplayed);
    }

    public void clickPlay() {
        WebElement playNextSong = driver.findElement(By.xpath(" //i[@data-testid='play-next-btn']"));
        WebElement playButton = driver.findElement(By.xpath("//span[@class='play']"));
        playNextSong.click();
        playButton.click();
    }
  public boolean isSongPlaying(){
     WebElement soundbar=driver.findElement(By.xpath("//div[@data-testid='sound-bar-play']"));
    return soundbar.isDisplayed();
  }

    }



