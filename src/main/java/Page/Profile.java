package Page;


import Base.BaseClass;
import Helper.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class Profile {
    static By profileIcon=By.xpath("//img[@class='drop-icon']");
    static By profiletext=By.xpath("//a[@routerlink='./../profile']");
//    static By verifyName=By.xpath("//td[normalize-space()='Vivek']");
    static By verifyName=By.xpath("    //p[@class='name']");
    public static String actualname;
    public static  String expectedname;
    public static void profileview(WebDriver driver)  {

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));

        Util.click(driver.findElement(profileIcon));
        Util.click(driver.findElement(profiletext));
        driver.navigate().refresh();
        driver.navigate().refresh();
        driver.navigate().refresh();
        driver.navigate().refresh();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        actualname=driver.findElement(verifyName).getText();
        expectedname= BaseClass.prop.getProperty("name");
    }
}
