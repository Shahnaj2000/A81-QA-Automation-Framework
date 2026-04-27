import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Homework21 extends BaseTest{
    String newRenamePlaylist = "Playlist2";
    @Test
    public void renamePlaylist(){
        String expectedMsg ="Updated playlist \"Playlist2.\"";
      provideEmail("shahnaj.khatun@testpro.io");
      providePassword("Faizan@123");
      clickLoginButton();
      dbClickPlaylist();
      enterNewPlaylist();
      Assert.assertEquals(expectedMsg,getNotificationMsg());
    }
    public void dbClickPlaylist() {
      WebElement  playlist =wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".playlist:nth-child(3)")));
      actions.doubleClick(playlist).perform();
    }
    public void enterNewPlaylist() {
       WebElement playlistInputField =wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[name='name']")));
        playlistInputField.sendKeys(Keys.chord(Keys.COMMAND,"A",Keys.BACK_SPACE));
        playlistInputField.sendKeys(newRenamePlaylist);
        playlistInputField.sendKeys(Keys.ENTER);
    }
    public String getNotificationMsg(){
      WebElement notification =wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.success.show")));
      return notification.getText();
    }





}
