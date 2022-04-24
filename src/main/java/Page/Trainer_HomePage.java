package Page;

import Base.BaseClass;
import Helper.Util;
import net.jodah.failsafe.internal.util.Assert;
import org.apache.poi.ss.formula.functions.T;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.beans.Transient;
import java.time.Duration;

public class Trainer_HomePage {

    static By loginXpath = By.xpath("//input[@placeholder='Enter your username']");
    static By passwordXpath = By.xpath("//input[@placeholder='Enter your password']");
    static By become_trainer = By.xpath("//button[normalize-space()='BECOME A TRAINER']");
    static By initiation = By.xpath("//div[@class='mat-select-arrow ng-tns-c52-3']");
    static By pagination_range = By.xpath("//div[@class='mat-paginator-range-label']");
    static By next_page_btn = By.xpath("//button[@aria-label='Next page']");
    static By prev_page_btn = By.xpath("//button[@aria-label='Previous page']");
    static By search_box = By.xpath("//input[@id='mat-input-0']");
    static By first_name = By.xpath("//tbody/tr[1]/td[1]");
    static By skill_name = By.xpath("//tbody/tr[1]/td[2]");



    public static void login(WebDriver driver, String username, String password){

        Util.sendKey(driver.findElement(loginXpath), username);
        Util.sendKey(driver.findElement(passwordXpath), password);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
        Util.click(driver.findElement(By.xpath("/html/body/app-root/app-login-page/div/form/div/div[3]")));
        Util.zoomout(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
    }

    public static void goto_trainer(WebDriver driver){
        Util.jsClick(driver,become_trainer);
        driver.navigate().refresh();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
        driver.navigate().refresh();

    }

    public static void initiation(WebDriver driver,By initiation_number,int number) throws InterruptedException {
        Util.zoomout(driver);
        Util.jsClick(driver,initiation);
        Thread.sleep(3000);
        Util.jsClick(driver,initiation_number);
        Thread.sleep(3000);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.body.style.zoom='40%'");

        String range = driver.findElement(pagination_range).getText();

        int total = Integer.parseInt(range.substring(range.length()-2).strip());

        if(total > number){
            int display_num = Integer.parseInt(range.substring(4,6).strip());

            if(display_num == number){
                System.out.println("Pass");
            }
            else {
                System.out.println("Fail");
            }
        }



    }

    public static void search(WebDriver driver,String search_name,String head){
        Util.sendKey(driver.findElement(search_box), search_name);

        if(head.equals("name")) {
            String appearing_name = driver.findElement(first_name).getText();
            System.out.println(appearing_name);
            if (appearing_name.equals(search_name)) {
                System.out.println("Search Successful");
            } else {
                System.out.println("Search Unsuccessful");
            }
        }

        if(head.equals("skill")){
            String appearing_name = driver.findElement(skill_name).getText();
            System.out.println(appearing_name);
            if (appearing_name.equals(search_name)) {
                System.out.println("Search Successful");
            } else {
                System.out.println("Search Unsuccessful");
            }
        }

    }

    public static void navigate(WebDriver driver,String nav) throws InterruptedException {

        String range = driver.findElement(pagination_range).getText();
        int total = Integer.parseInt(range.substring(range.length()-2).strip());

        if (total > 5) {
            if (nav.equals("next")) {
                Util.jsClick(driver, next_page_btn);
                Thread.sleep(3000);
                int num = Integer.parseInt((driver.findElement(pagination_range).getText()).substring(0, 1));
                if (num == 6) {
                    System.out.println("Successful");
                } else {
                    System.out.println("Unsuccessful");
                }
            }

            if (nav.equals("prev")) {
                Util.jsClick(driver, prev_page_btn);
                Thread.sleep(3000);
                int num = Integer.parseInt((driver.findElement(pagination_range).getText()).substring(0, 1));
                if (num == 1) {
                    System.out.println("Successful");
                } else {
                    System.out.println("Unsuccessful");
                }
            }
        }
    }
}
