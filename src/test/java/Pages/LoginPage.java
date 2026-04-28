package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{

    //Constructor
 public LoginPage(WebDriver givenDriver) {
     super(givenDriver);
 }

     //WebElement
     By emailField = By.xpath("//input[@type='email']");
     By passwordField = By.xpath("//input[@type='password']");
     By clickLOginButton = By.xpath("//button[@type='submit']");

     public void provideEmail(String email){
        findElement(emailField).clear();
        findElement(emailField).sendKeys(email);
     }
     public void providePassword(String password){
         findElement(passwordField).clear();
         findElement(passwordField).sendKeys(password);
     }
     public void clickLOginButton(){
         findElement(clickLOginButton).click();
     }
 }
