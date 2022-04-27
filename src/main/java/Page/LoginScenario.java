package Page;

import Helper.Util;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginScenario {

    static By loginXpath = By.xpath("//input[@placeholder='Enter your username']");
    static By passwordXpath = By.xpath("//input[@placeholder='Enter your password']");
    static By loginBtn = By.xpath("/html/body/app-root/app-login-page/div/form/div/div[3]");

    public static void login(WebDriver driver, String username, String password) {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
        Util.sendKey(driver.findElement(loginXpath), username);
        Util.sendKey(driver.findElement(passwordXpath), password);
        Util.click(driver.findElement(loginBtn));

    }

    //alert function in login scenario
    public static void accept_alert(WebDriver driver)
    {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alt = driver.switchTo().alert();
        alt.accept();
        driver.navigate().refresh();
    }



}
