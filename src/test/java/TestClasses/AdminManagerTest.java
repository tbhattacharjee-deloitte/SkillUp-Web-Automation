package TestClasses;

import Base.BaseClass;
import ListenersPackage.AdminManagerListener;
import Page.Login;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Listeners(AdminManagerListener.class)
public class AdminManagerTest {
    WebDriver driver;
    By profileDivPath = By.xpath("//div[@class='profile']");
    By manageBtnPath = By.xpath("//a[text() = 'Manage']");
    By dropdownListPath = By.xpath("//div[@class='next-to-training']/ul/li");
    By users = By.xpath("//a[text() = 'Users']");

    public ExtentReports extent;
    public static ExtentTest test;
    public Logger logger;

    public AdminManagerTest() {
        extent = new ExtentReports();
        logger = LogManager.getLogger(AdminManagerTest.class.getName());
        extent.attachReporter(new ExtentHtmlReporter("extent.html"));
    }

    @BeforeTest
    void login() {
        // initialize driver
        driver = BaseClass.init();
        // login
        Login.login(driver,"mentorAdmin", "abc123");
        // refresh
        BaseClass.explicitWait_visibility(driver, 5000, profileDivPath);
        driver.navigate().refresh();
        //BaseClass.zoomout(driver);
    }
    @Test(priority = 1)
    void validate_manageTab() {
        // validate if manage tab is present
        BaseClass.expectedWait_toClick(driver, 2000, manageBtnPath);

        // validate if the dropdown List under manage have all the required tabs
        List<WebElement> dropDownList = driver.findElements(dropdownListPath);
        String[] check = {"Users", "Categories", "Skills"};
        test.log(Status.INFO, "Validating the buttons under dropdown of Manage Tab");
        for (int i = 0; i < dropDownList.size(); i++) {
            assert check[i].equalsIgnoreCase(dropDownList.get(i).getText());
        }
    }

    @Test (priority = 2)
    void userClickability() {
        BaseClass.expectedWait_toClick(driver, 2000, users);
    }
}
