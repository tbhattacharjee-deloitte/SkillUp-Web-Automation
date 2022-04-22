package Base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Driver;
import java.time.Duration;
import java.util.Properties;

public class BaseClass {
    public static Properties prop;
    static {
        try{
            prop = new Properties();
            FileInputStream ip=new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\config\\config.properties");
            prop.load(ip);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static WebDriver init() {
        String browserName=prop.getProperty("browser");
        if(browserName.equals("chrome")){
            System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + "\\src\\main\\resources\\chromedriver.exe");
        }
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(prop.getProperty("url"));
        return driver;
    }
    public static void zoomout(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.body.style.zoom='90%'");
    }
    public static void sendKey(WebElement ele, String key) {
        ele.sendKeys(key);
    }
    public static void click(WebElement ele) {
        ele.click();
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
