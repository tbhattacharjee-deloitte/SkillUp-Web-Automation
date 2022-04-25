package Page;

import Base.BaseClass;
import Helper.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import java.time.Duration;

public class Login extends BaseClass{
    static By loginXpath = By.xpath("//input[@placeholder='Enter your username']");
    static By passwordXpath = By.xpath("//input[@placeholder='Enter your password']");
    static By loginBtn = By.xpath("//button[@class='lgbt']");

    public static void login(WebDriver driver, String username, String password) {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
        Util.sendKey(driver.findElement(loginXpath), username);
        Util.sendKey(driver.findElement(passwordXpath), password);
        Util.click(driver.findElement(loginBtn));

    }
}
