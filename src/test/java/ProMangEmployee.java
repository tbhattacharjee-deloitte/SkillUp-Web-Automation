import Base.BaseClass;
import Helper.Util;
import Page.Login;
import Page.ManagerEmployee;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class ProMangEmployee {
    WebDriver driver;
    String username = "sangeeta";
    String password = "sangeeta123";
    By profileDivPath = By.xpath("//div[@class='profile']");

    @BeforeTest
    void login() {
        driver = BaseClass.init();
        Login.login(driver, username,password);
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
    void checkPagination(){
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
        ManagerEmployee.checkPageButton(driver);
    }




}
