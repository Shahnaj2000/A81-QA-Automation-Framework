
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.internal.BaseTestMethod;

import java.time.Duration;

public class Homework17 extends BaseTest {

    WebDriver driver;

    @Test
    public void AddSongToPlaylist() throws InterruptedException {
        String expectedSongAddedMessage = "Added 1 song into \"Test Pro Playlist.\"";
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        //Pre-condition
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        navigateToPage();
        provideEmail("shahnaj.khatun@testpro.io");
        providePassword("Faizan@123");
        clickLoginButton();
        Thread.sleep(2000);
        searchsong("dark days");
        Thread.sleep(2000);
        clickViewAllBtn();
        Thread.sleep(2000);
        selectFirstsongResult();
        Thread.sleep(2000);
        clickAddToBtn();
        Thread.sleep(2000);
        choosePlaylist();
        Thread.sleep(2000);
        getAddToPlaylistSuccessMsg();
        Assert.assertEquals(getAddToPlaylistSuccessMsg(), expectedSongAddedMessage);
    }
        public void navigateToPage () {
            String url = "https://qa.koel.app/";
            driver.get(url);
        }
        public void provideEmail (String email){
            WebElement emailField = driver.findElement(By.xpath("//input[@type='email']"));
            emailField.click();
            emailField.clear();
            emailField.sendKeys(email);
        }
        public void providePassword (String password){
            WebElement passwordField = driver.findElement(By.xpath("//input[@type='password']"));
            passwordField.click();
            passwordField.clear();
            passwordField.sendKeys(password);
        }
        public void clickLoginButton () {
            WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));
            loginButton.click();
        }
        public void searchsong (String song){
            WebElement searchField = driver.findElement(By.xpath("//input[@name='q']"));
            searchField.click();
            searchField.clear();
            searchField.sendKeys(song);
        }
        public void clickViewAllBtn(){
            WebElement viewAllButton = driver.findElement(By.xpath("//button[@data-test='view-all-songs-btn']"));
            viewAllButton.click();
        }



        public void selectFirstsongResult () {
            WebElement firstSong = driver.findElement(By.xpath("//section[@id='songResultsWrapper']//table[@class='items']//tr[1]"));
            firstSong.click();
        }
        public void clickAddToBtn () {
            WebElement addToButton = driver.findElement(By.xpath("//button[@class='btn-add-to']"));
            addToButton.click();
        }
        public void choosePlaylist () {
            WebElement playlist = driver.findElement(
                    By.xpath("//section[@id='songResultsWrapper']//li[contains(text(),'Test Pro Playlist')]")
            );
            playlist.click();
        }
        public String getAddToPlaylistSuccessMsg () {
            WebElement notification = driver.findElement(By.xpath("//div[@class='success show']"));
            return notification.getText();
        }

}

























