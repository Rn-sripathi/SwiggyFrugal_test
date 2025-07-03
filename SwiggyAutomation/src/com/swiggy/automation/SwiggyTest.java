package com.swiggy.automation;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SwiggyTest {

    public static void main(String[] args) throws InterruptedException, IOException {
        System.setProperty("webdriver.chrome.driver",
                "/Users/rishwantharyansripathi/Downloads/chromedriver-mac-arm64/chromedriver");

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        runSwiggyFlow(driver, wait);

        System.out.println("✅ Automation complete. Browser is still open.");
    }

    public static void captureScreenshot(WebDriver driver, String filename) throws IOException {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File dest = new File("/Users/rishwantharyansripathi/eclipse-workspace/SwiggyAutomation/screenshots/" + filename + "_" + timestamp + ".png");
        Files.copy(src.toPath(), dest.toPath());
        System.out.println("📸 Screenshot saved: " + dest.getName());
    }

    public static void runSwiggyFlow(WebDriver driver, WebDriverWait wait) throws InterruptedException, IOException {
        driver.get("https://www.swiggy.com");
        captureScreenshot(driver, "01_homepage");

        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@class, '_5-C04')]")));
        loginBtn.click();

        WebElement phoneInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mobile")));
        phoneInput.sendKeys("9290444461");

        WebElement loginWithOtpBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Login' or contains(text(),'login')]")));
        loginWithOtpBtn.click();

        Thread.sleep(45000);  // Manual OTP input

        WebElement verifyOtpBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'VERIFY OTP') or contains(text(),'Continue') or contains(text(),'Login')]")));
        verifyOtpBtn.click();
        captureScreenshot(driver, "02_after_login");

        WebElement locationInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("location")));
        locationInput.clear();
        locationInput.sendKeys("Bengaluru");

        WebElement locationSuggestion = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='kuQWc']/span[contains(text(), 'Bengaluru, Karnataka, India')]")));
        locationSuggestion.click();

        Thread.sleep(4000);

        WebElement searchBar = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@type='button'][.//div[contains(text(),'Search for restaurant, item or more')]]")));
        searchBar.click();

        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Search for restaurants and food']")));
        searchBox.sendKeys("Meghana Foods");
        Thread.sleep(2000);

        WebElement firstSuggestion = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@data-testid='autosuggest-item'])[1]")));
        firstSuggestion.click();

        WebElement meghanaCard = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href,'meghana-foods')]")));
        meghanaCard.click();

        Thread.sleep(5000);
        captureScreenshot(driver, "03_meghana_foods_page");

        WebElement secondItem = driver.findElements(By.xpath("//div[@data-testid='normal-dish-item']")).get(1);
        WebElement addBtn = secondItem.findElement(By.xpath(".//button[contains(@class,'add-button-center-container')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addBtn);
        Thread.sleep(500);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addBtn);

        WebElement secondItemName = secondItem.findElement(By.xpath(".//div[contains(@class,'eqSzsP')]"));
        Thread.sleep(5000);
        captureScreenshot(driver, "04_added_item");

        WebElement navbarCartIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class,'_2mTTM')]//a[@href='/checkout']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", navbarCartIcon);
        navbarCartIcon.click();

        Thread.sleep(3000);

        try {
            WebElement addBtn1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'ADD') and contains(@class,'zKoxV')]")));
            addBtn1.click();
        } catch (TimeoutException e) {
            WebElement plusBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class,'BbiqG') and text()='+']")));
            plusBtn.click();
        }

        Thread.sleep(3000);
        captureScreenshot(driver, "05_cart_page");

        try {
            WebElement addNewAddressDiv = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class,'_3kHjz') and contains(@class,'_6RaiM')]")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addNewAddressDiv);
            Thread.sleep(500);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addNewAddressDiv);
            Thread.sleep(2000);
        } catch (Exception ignored) {}

        try {
            WebElement buildingInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("building")));
            buildingInput.clear();
            buildingInput.sendKeys("Flat No. 5B, XYZ Apartments");
        } catch (Exception ignored) {}

        try {
            WebElement landmarkInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("landmark")));
            landmarkInput.clear();
            landmarkInput.sendKeys("Near ABC Park");
        } catch (Exception ignored) {}

        try {
            WebElement scrollContainer = driver.findElement(By.className("_2qrkp"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollTop = arguments[0].scrollHeight", scrollContainer);
            Thread.sleep(1000);
        } catch (Exception ignored) {}

        try {
            WebElement otherOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class,'1qiSu')]//div[text()='Other']")));
            otherOption.click();
        } catch (Exception ignored) {}

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'SAVE ADDRESS & PROCEED')]")));
            WebElement saveAddressBtn = driver.findElement(By.xpath("//div[contains(text(), 'SAVE ADDRESS & PROCEED')]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", saveAddressBtn);
            Thread.sleep(1000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveAddressBtn);
        } catch (Exception ignored) {}

        captureScreenshot(driver, "06_address_filled");

        try {
            WebElement finalProceedBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class,'_4dnMB') and contains(text(),'Proceed to Pay')]")));
            finalProceedBtn.click();
        } catch (Exception ignored) {}

        captureScreenshot(driver, "07_final_payment_page");
    }
}
