package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage{

    public HomePage(WebDriver givenDriver){
        super(givenDriver);
    }
    By avatarIcon=By.xpath("//img[@class='avatar']");
    By clickPlaylistBtn=By.cssSelector(".playlist:nth-child(3)");
    By notificationMsg =By.cssSelector("div.success.show");
    By PlaylistInput =By.cssSelector("[name='name']");
    String newRenamePlaylist = "Sample Edited Playlist";


    public WebElement getAvatarIcon(){
        return findElement(avatarIcon);
    }
    public void dbClickPlaylist() {
        WebElement playlist =findElement(clickPlaylistBtn);
        actions.doubleClick(playlist).perform();
    }

    public void enterNewPlaylist() {
        WebElement playlistInputField =findElement(PlaylistInput);
        playlistInputField.sendKeys(Keys.chord(Keys.COMMAND, "A", Keys.BACK_SPACE));
        playlistInputField.sendKeys(newRenamePlaylist);
        playlistInputField.sendKeys(Keys.ENTER);
    }

    public String getRenamePlaylistSuccessMsg() {
        WebElement notification = findElement(notificationMsg);
        return notification.getText();
    }
}
