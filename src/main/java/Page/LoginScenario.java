package Page;

import Helper.Util;
import org.apache.logging.log4j.core.util.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;

public class LoginScenario {


    public static void accept_alert(WebDriver driver)
    {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alt = driver.switchTo().alert();
        alt.accept();
        driver.navigate().refresh();
    }




}
