package Tests;

import Base.WebTestBase;
import Pages.LoginActions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;



public class PurchaseFlow extends WebTestBase {



    @Test
    public void FlowTest() throws InterruptedException {
        // Sets up an "Explicit Wait" that pauses the test for up to 10 seconds until specific conditions (like visibility) are met
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2000));

        // Initialises the LoginActions page object, passing the driver so it can perform actions in the browser
        LoginActions loginActions = new LoginActions(driver);

         // Step 1: Login
        loginActions.clickLoginButton();
        loginActions.enterEmailAddress("melsa@gmail.co.za");
        loginActions.enterPassword("Pass@1234");
        loginActions.clickSubmitButton();
        loginActions.verifyLoginSuccess("Here's who's working today");


        //Step 3. Click Learn
        wait.until(ExpectedConditions.elementToBeClickable( By.xpath("//span[contains(text(), 'Learn')]"))).click();
        Thread.sleep(1000);

        // Step 2: Navigate to Learning Materials
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".nav-dropdown-trigger"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='app-root']//span[contains(text(), 'Learning Materials')]"))).click();
        Thread.sleep(1000);

        // Step 3: Click Web Automation Advance
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"tab-btn-web\"]/span[2]"))).click();
        wait.until(ExpectedConditions.visibilityOf(loginActions.inventoryForm));
        Assert.assertTrue(loginActions.inventoryForm.isDisplayed(), "Inventory Form not displayed!");
        Thread.sleep(1000);

        // Step 4: Select Device Type

        Select deviceTypeDropdown = new Select(wait.until(ExpectedConditions.elementToBeClickable(By.id("deviceType"))));
        deviceTypeDropdown.selectByVisibleText("Phone");
        Thread.sleep(1000);

        // Step 5: Wait for Brand and Select

        WebElement brandDropdownElement = wait.until(ExpectedConditions.elementToBeClickable(By.id("brand")));
        new Select(brandDropdownElement).selectByVisibleText("Apple");
        Thread.sleep(1000);

        // Step 6: Wait for preview
        // wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("devicePreview")));

        // Step 7: Radio Button (Replace "xpath-here")
        wait.until(ExpectedConditions.elementToBeClickable(By.id("storage-128GB"))).click();
        Thread.sleep(1000);

        // Step 8: Color Dropdown
        WebElement colorDropdownElement = driver.findElement(By.id("color"));
        Select colorDropdown = new Select(colorDropdownElement);
        colorDropdown.selectByVisibleText("Blue");
        Assert.assertEquals(colorDropdown.getFirstSelectedOption().getText(), "Blue");
        Thread.sleep(1000);

        // Step 9: Enter Quantity (Fixed syntax error here)
        WebElement quantityInput = driver.findElement(By.id("quantity"));
        quantityInput.clear();
        quantityInput.sendKeys("2");
        Thread.sleep(1000);


        // Step 10: Verify Subtotal

        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("subtotal-label"), "R960.00"));
        String subtotalText = driver.findElement(By.id("subtotal-label")).getText();
        Assert.assertEquals(subtotalText, "Subtotal: R960.00");
        Thread.sleep(1000);

        // Step 11: Address and Finish

        wait.until(ExpectedConditions.elementToBeClickable(By.id("address"))).sendKeys("123 Test Street");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("inventory-next-btn"))).click();
        Thread.sleep(1000);

        //Step 12.Select Express Shipping
        wait.until(ExpectedConditions.elementToBeClickable(By.id("shipping-express"))).click();
        Thread.sleep(1000);

        //step 13.1 year Warranty
        wait.until(ExpectedConditions.elementToBeClickable(By.id("warranty-1yr"))).click();
       Thread.sleep(1000);

        //step 14.Apply discount code

        wait.until(ExpectedConditions.elementToBeClickable(By.id("discount-code"))).sendKeys("SAVE10");

        //  Wait for the discount code field and enter "SAVE10"
        WebElement discountInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("discount-code")));
        discountInput.clear();
        // Good practice to clear existing text first
        discountInput.sendKeys("SAVE10");
        Thread.sleep(1000);


        // 2. Wait for the "Apply" button to be ready and then click it
        WebElement applyBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("apply-discount-btn")));
        applyBtn.click();
        Thread.sleep(1000);

       //step 15.Click Confirm Purchase
        wait.until(ExpectedConditions.elementToBeClickable(By.id("purchase-device-btn"))).click();
        Thread.sleep(1000);

        //Step 16.Click View Invoice
        wait.until(ExpectedConditions.elementToBeClickable(By.id("view-history-btn"))).click();
        Thread.sleep(1000);

        //Step 17.Click View on invoice


        WebElement viewButton = driver.findElement(By.id("view-history-btn"));
        // rolls the button to the middle of the screen instead of the top
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", viewButton);
        viewButton.click();
    }
}