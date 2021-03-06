package Page;

import Base.BaseClass;
import Helper.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class Login {
    static By loginXpath = By.xpath("//input[@placeholder='Enter your username']");
    static By passwordXpath = By.xpath("//input[@placeholder='Enter your password']");
    static By loginBtn = By.xpath("//button[@class='lgbt']");
    static By nameassert= By.xpath("//p[@class='name']");

    public static void login(WebDriver driver, String username, String password) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
        Util.sendKey(driver.findElement(loginXpath), username);
        Util.sendKey(driver.findElement(passwordXpath), password);
//        Util.click(driver.findElement(loginBtn));
        Util.jsClick(driver,loginBtn);

//        Util.threadSleep(2000);
    }

    public static String actualtext(WebDriver driver) throws InterruptedException {
        driver.navigate().refresh();
        Util.threadSleep(2000);
        return driver.findElement(nameassert).getText();

    }
}
