package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

    //Constructor
 public LoginPage(WebDriver givenDriver) {
     super(givenDriver);
 }

     //WebElement
     //By emailField = By.xpath("//input[@type='email']");
     //By passwordField = By.xpath("//input[@type='password']");
     //By LoginButton = By.xpath("//button[@type='submit']");

     @FindBy(xpath = "//input[@type='email']")
     WebElement emailField;
     @FindBy(xpath = "//input[@type='password']")
     WebElement passwordField;
     @FindBy(xpath = "//button[@type='submit']")
     WebElement LoginButton;

     public void provideEmail(String email) {
         emailField.sendKeys(email);
     }
     public void providePassword(String password){
         passwordField.sendKeys(password);
     }
     public void clickLoginButton(){
         LoginButton.click();
     }
 }
