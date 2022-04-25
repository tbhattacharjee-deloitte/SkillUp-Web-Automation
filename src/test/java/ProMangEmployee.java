import Base.BaseClass;
import Helper.Util;
import Page.Login;
import Page.LoginScenario;
import Page.ManagerEmployee;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class ProMangEmployee {
    WebDriver driver;
    String username = "sangeeta";
    String password = "sangeeta123";
    By init_5 = By.xpath("//span[@class='mat-option-text'][normalize-space()='5']");
    By init_10 = By.xpath("//span[normalize-space()='10']");
    By profileDivPath = By.xpath("//div[@class='profile']");

    @BeforeTest
    void login() {
        driver = BaseClass.init();
        LoginScenario.login(driver, username,password);
        Util.explicitWait_visibility(driver, 5000, profileDivPath);
        driver.navigate().refresh();
        Util.zoomout(driver);

    }



    @Test(priority=1)
    void goto_employee(){
        ManagerEmployee.goto_employee(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));


    }


   @Test (priority = 2)
    void searchBy_name(){
        String name = "Vivek";
        ManagerEmployee.searchBy(driver,name,"Name");

       driver.navigate().refresh();

    }

    @Test (priority = 3)
    void searchBy_Designation(){
        String designation = "sdet";
        ManagerEmployee.searchBy(driver,designation,"Designation");

        driver.navigate().refresh();

    }


    @Test (priority = 4)
    void searchBy_exp(){
        String exper= "3";
        ManagerEmployee.searchBy(driver,exper,"Experience");
        driver.navigate().refresh();

    }
    @Test (priority = 5)
    void actionIcon(){
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
        ManagerEmployee.acceptButton(driver);
    }

    @Test (priority = 6)
    void next_page() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
        Thread.sleep(3000);

        ManagerEmployee.checkPageButton(driver,"next");
    }


    @Test (priority = 7)
    void prev_page() throws InterruptedException {
        Thread.sleep(3000);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
        ManagerEmployee.checkPageButton(driver,"prev");
    }

    @Test (priority = 8)
    void page_initiation_5() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
        ManagerEmployee.page_init(driver,init_5,5);
    }

    @Test (priority = 9)
    void page_initiation_10() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
        ManagerEmployee.page_init(driver,init_10,10);
    }



}
