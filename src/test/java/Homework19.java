
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

    public class Homework19 extends BaseTest {
        @Test
        public void deletePlaylist() {
            String ExpectedPlaylistDeleteMessage = "Deleted playlist \"Test Pro Playlist.\"";
            provideEmail("shahnaj.khatun@testpro.io");
            providePassword("Faizan@123");
            clickLoginButton();
            openPlaylistbtn();
            clickDeletePlaylistbtn();
            Assert.assertEquals(getDeletePlaylistMsg(), ExpectedPlaylistDeleteMessage);
        }

        public void openPlaylistbtn() {
            WebElement emptyPlaylist = driver.findElement(By.cssSelector(".playlist:nth-child(3)"));
            emptyPlaylist.click();
        }

        private void clickDeletePlaylistbtn() {
            WebElement deletePlaylist = driver.findElement(By.cssSelector(".btn-delete-playlist"));
            deletePlaylist.click();
        }

        public String getDeletePlaylistMsg() {
            WebElement notificationMsg = driver.findElement(By.cssSelector("div.success.show"));
            return notificationMsg.getText();
        }
    }

