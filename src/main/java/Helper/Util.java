package Helper;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Util {
    public static void zoomout(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.body.style.zoom='90%'");
    }
    public static void sendKey(WebElement ele, String key) {
        ele.sendKeys(key);
    }
    public static void sendKey(WebDriver driver, By xpath, String key) {
        sendKey(driver.findElement(xpath), key);
    }
    public static void click(WebElement ele) {
        ele.click();
    }

    public static void click(WebDriver driver, By xpath) {
        click(driver.findElement(xpath));
    }

    public static void jsClick(WebDriver driver, By xpath) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", driver.findElement(xpath));
    }

    public static void implicitWait(WebDriver driver, int milsec) {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(milsec));
    }
    public static void explicitWait_visibility(WebDriver driver, int milsec, By xpath) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(milsec));
        wait.until(ExpectedConditions.visibilityOfElementLocated(xpath));
    }
    public static void expectedWait_toClick(WebDriver driver, int milsec, By xpath) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(2000));
        wait.until(ExpectedConditions.elementToBeClickable(xpath)).click();
    }
}
