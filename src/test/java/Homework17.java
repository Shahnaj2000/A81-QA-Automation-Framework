
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.internal.BaseTestMethod;

import java.time.Duration;

public class Homework17 extends BaseTest {

    WebDriver driver;

    @Test
    public void AddSongToPlaylist()  {
        String expectedSongAddedMessage = "Added 1 song into \"Test Pro Playlist.\"";

        navigateToPage();
        provideEmail("shahnaj.khatun@testpro.io");
        providePassword("Faizan@123");
        clickLoginButton();
        searchsong("dark days");
        clickViewAllBtn();
        selectFirstsongResult();
        clickAddToBtn();
        choosePlaylist();
        getAddToPlaylistSuccessMsg();
        Assert.assertEquals(getAddToPlaylistSuccessMsg(), expectedSongAddedMessage);
    }

        public void searchsong (String song){
            //WebElement searchField = driver.findElement(By.xpath("//input[@name='q']"));
            WebElement searchField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='q']")));
            searchField.click();
            searchField.clear();
            searchField.sendKeys(song);
        }
        public void clickViewAllBtn(){
        //WebElement viewAllButton = driver.findElement(By.xpath("//button[@data-test='view-all-songs-btn']"));
            WebElement viewAllButton= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@data-test='view-all-songs-btn']")));
            viewAllButton.click();
        }
        public void selectFirstsongResult () {
           //WebElement firstSong = driver.findElement(By.xpath("//section[@id='songResultsWrapper']//table[@class='items']//tr[1]"));
            WebElement firstSong= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[@id='songResultsWrapper']//table[@class='items']//tr[1]")));
            firstSong.click();
        }
        public void clickAddToBtn () {
            //WebElement addToButton = driver.findElement(By.xpath("//button[@class='btn-add-to']"));
            WebElement addToButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='btn-add-to']")));
            addToButton.click();
        }
        public void choosePlaylist () {
           // WebElement playlist = driver.findElement(By.xpath("//section[@id='songResultsWrapper']//li[contains(text(),'Test Pro Playlist')]"));
            WebElement playlist = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[@id='songResultsWrapper']//li[contains(text(),'Test Pro Playlist')]")));
            playlist.click();
        }
        public String getAddToPlaylistSuccessMsg () {
            //WebElement notification = driver.findElement(By.xpath("//div[@class='success show']"));
            WebElement notification = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='success show']")));
            return notification.getText();
        }

}

























