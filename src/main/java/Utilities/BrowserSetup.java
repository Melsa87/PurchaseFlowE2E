package Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class BrowserSetup {

    public static WebDriver startBrowser(String browserChoice, String url) {
        WebDriver driver;
        if (browserChoice.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browserChoice.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browserChoice);
        }
        driver.manage().window().maximize();
        driver.get(url);
        return driver;
    }

    public static void closeBrowser(WebDriver driver) {
        if (driver != null) {
            driver.quit();
        }
    }

    public static void initElements(WebDriver driver) {

    }

}
