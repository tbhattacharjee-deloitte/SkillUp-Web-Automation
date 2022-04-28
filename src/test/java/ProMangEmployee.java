import Base.BaseClass;
import Helper.Util;
import Page.Login;
import Page.ManagerEmployee;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

import static Base.BaseClass.prop;

public class ProMangEmployee {
    WebDriver driver;
    By init_5 = By.xpath("//span[@class='mat-option-text'][normalize-space()='5']");
    By init_10 = By.xpath("//span[normalize-space()='10']");
    By profileDivPath = By.xpath("//div[@class='profile']");

    public ProMangEmployee() throws IOException {
    }

    @BeforeTest
    @Parameters({"pmUsername", "pmPassword"})
    void login(String pmUsername, String pmPassword) throws IOException, InterruptedException {
        driver = BaseClass.init();
        Login.login(driver, pmUsername, pmPassword);
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
        String name = prop.getProperty("name");
        ManagerEmployee.searchBy(driver,name,"Name");

        driver.navigate().refresh();

    }

    @Test (priority = 3)
    void searchBy_Designation(){
        String designation = prop.getProperty("designation");
        ManagerEmployee.searchBy(driver,designation,"Designation");

        driver.navigate().refresh();

    }


    @Test (priority = 4)
    void searchBy_exp(){
        String exper= prop.getProperty("experience");
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
        Util.threadSleep(3000);

        ManagerEmployee.checkPageButton(driver,"next");
    }


    @Test (priority = 7)
    void prev_page() throws InterruptedException {
        Util.threadSleep(3000);
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
    @AfterTest
    void closeDriver() {
        driver.close();
    }

}