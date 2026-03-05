package Base;

import Pages.LoginActions;
import Utilities.BrowserSetup;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.time.Duration;

public class WebTestBase {

    // 1. Declare
    public WebDriver driver;
    public WebDriverWait wait;
    public LoginActions loginActions;

    public final String url = "https://ndosisimplifiedautomation.vercel.app/";
    public final String browserChoice = "chrome";

    @BeforeMethod
    public void setUp() {
        driver = BrowserSetup.startBrowser(browserChoice, url);

        // 2. Initialize wait here so it's never null in your tests
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // 3. Initialize loginActions
        loginActions = new LoginActions(driver);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            BrowserSetup.closeBrowser(driver);
        }
    }
}