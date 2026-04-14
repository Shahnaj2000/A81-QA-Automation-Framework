import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class Homework18 extends BaseTest  {
    WebDriver driver;
    @Test
    public void playSong() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        navigateToPage();
        provideEmail("shahnaj.khatun@testpro.io");
        providePassword("Faizan@123");
        clickLoginButton();
        clickPlay();
        boolean isSoundBarDisplayed = isSongPlaying();
        Assert.assertTrue(isSoundBarDisplayed);

    }
    public void navigateToPage () {
        String url = "https://qa.koel.app/";
        driver.get(url);
    }
    public  void provideEmail(String email ) {
        WebElement emailField = driver.findElement(By.xpath("//input[@type='email']"));
        emailField.click();
        emailField.clear();
        emailField.sendKeys(email);
    }
       public void providePassword(String password) {
        WebElement passwordField = driver.findElement(By.xpath("//input[@type='password']"));
        passwordField.click();
        passwordField.clear();
        passwordField.sendKeys(password);
    }
    public void clickLoginButton() {
        WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));
        loginButton.click();
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



