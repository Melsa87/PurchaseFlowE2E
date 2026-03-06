package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginActions {

    //public WebElement inventoryForm;
    WebDriver driver;
    WebDriverWait wait;

    // 1. Declare your credentials as class-level variables
    String email = "melsa@gmail.com";
    String password = "Pass@123";

    public LoginActions(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }
    @FindBy(id = "inventory-title")
    public WebElement inventoryForm;

    @FindBy(xpath = "//button[contains(., 'Login')]")
    WebElement loginButton;

    @FindBy(id = "login-email")
    WebElement loginEmailField;

    @FindBy(id = "login-password")
    WebElement loginPasswordField;

    @FindBy(id = "login-submit")
    WebElement loginSubmitButton;

    @FindBy(xpath = "//h2[contains(., 'Welcome back, melsa!')]")
    WebElement welcomeBackMessage;

    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    // 2. Methods can now access 'email' and 'password' defined above
    public void enterEmailAddress(String mail) {
        wait.until(ExpectedConditions.visibilityOf(loginEmailField));
        loginEmailField.sendKeys(email);
    }

    public void enterPassword(String s) {
        wait.until(ExpectedConditions.visibilityOf(loginPasswordField));
        loginPasswordField.sendKeys(password);
    }

    public void clickSubmitButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginSubmitButton)).click();
    }

    public void verifyLoginSuccess(String expectedMessage) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"app-main-content\"]/section/div[1]/p")));
        String actualMessage = element.getText();
        if (!actualMessage.equals(expectedMessage)) {
            throw new AssertionError("Expected message: " + expectedMessage + ", but got: " + actualMessage);
        }
    }
}
