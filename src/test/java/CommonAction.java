import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CommonAction {
    WebDriver driver;

    public CommonAction(WebDriver driver) {
        this.driver = driver;
    }

    public void Navigate(String url) {
        driver.get(url);
    }

    public void logIn(String email, String password) {
        WebElement emailField = driver.findElement(By.xpath("//input[@type='email']"));
        emailField.clear();
        emailField.sendKeys(email);
        WebElement passwordField = driver.findElement(By.xpath("//input[@type='password']"));
        passwordField.clear();
        passwordField.sendKeys(password);
        WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));
        loginButton.click();
    }

    public void searchSong(String song) {
        WebElement searchField = driver.findElement(By.xpath("//input[@name='q']"));
        searchField.click();
        searchField.clear();
        searchField.sendKeys(song);
    }

    public void clickViewAllButton() {
        WebElement viewAllButton = driver.findElement(By.xpath("//button[@data-test='view-all-songs-btn']"));
        viewAllButton.click();
    }

    public void clickFirstSong() {
        WebElement firstSong = driver.findElement(By.xpath("//section[@id='songResultsWrapper']//table[@class='items']//tr[1]"));
        firstSong.click();
    }

    public void clickAddToButton() {
        WebElement addToButton = driver.findElement(By.xpath("//button[@class='btn-add-to']"));
        addToButton.click();
    }
    public String clickTestProPlaylist() {
        WebElement playlistBtn = driver.findElement(By.xpath("//section[@id='songResultsWrapper']//li[contains(text(), 'Test Pro Playlist')]"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        playlistBtn.click();

        System.out.println("Element found, clicking now...");
        WebElement notification = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[contains(@class,'success') and contains(@class,'show')]")
                )
        );
        return notification.getText();
    }
}



