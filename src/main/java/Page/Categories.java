package Page;

import Helper.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import java.time.Duration;

public class Categories {
    static By categorybtn = By.xpath("//mat-icon[normalize-space()='keyboard_arrow_down']");
    static By categorybtn1= By.xpath("//li[1]//div[1]//mat-icon[1]");
    static By createrequest= By.xpath("//div[@id='mat-select-value-1']");
    static By createreq= By.xpath("//a[normalize-space()='Adobe xd']");
    static By selectreact= By.xpath("//span[normalize-space()='React.js']");
    static By selectdate = By.xpath("//input[@placeholder='Select your date']");
    static By discription = By.xpath("//textarea[@placeholder='Enter request details']");
    static By Durationadd = By.xpath("//input[@placeholder='Enter duration time']");
    static By start_time= By.xpath("/html/body/app-root/app-sidenav/div/div[2]/app-create-request/div/form/div[4]/div[1]/input");
    static By avail_endtime = By.xpath("//div[@class='main-container']//div[2]//input[1]");
    static By createbtn = By.xpath("//button[@class='req-button']");
    public  static By toassert= By.xpath("//span[@class='mat-select-placeholder mat-select-min-line ng-tns-c52-0 ng-star-inserted']");
    public static void category(WebDriver driver)  {

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
        Util.click(driver.findElement(categorybtn));
        driver.navigate().refresh();
        Util.click(driver.findElement(categorybtn));
        Util.click(driver.findElement(categorybtn1));
        Util.click(driver.findElement(createreq));

    }
    public static String actualtext(WebDriver driver){

        return driver.findElement(toassert).getText();
    }
    public static String expectedtext(WebDriver driver){
        return  driver.findElement(createreq).getText();

    }
    public static void fillvalue(WebDriver driver, String key, String date, String duration,String starttime, String endtime){
        Util.click(driver.findElement(createrequest));
        Util.click(driver.findElement(selectreact));

        Util.sendKey(driver.findElement(discription),key);

        Util.sendKey(driver.findElement(selectdate),date);

        Util.sendKey(driver.findElement(start_time),starttime);

        Util.sendKey(driver.findElement(avail_endtime),endtime);


        Util.sendKey(driver.findElement(Durationadd),duration);
        Util.zoomout(driver);

//        Actions act=new Actions(driver);
//        act.moveToElement(driver.findElement(By.xpath("//button[@class='req-button']"))).doubleClick();
        Util.jsClick(driver,createbtn);

    }

}
