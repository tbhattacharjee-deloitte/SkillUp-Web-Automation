package TestClasses;

import Base.BaseClass;
import ListenersPackage.AdminManagerListener;
import Page.Login;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.time.Duration;

@Listeners(AdminManagerListener.class)
public class AdminManagerTest {
    WebDriver driver;
    By profileDivPath = By.xpath("//div[@class='profile']");
    public ExtentReports extent;
    public ExtentTest test;
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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(profileDivPath));
        driver.navigate().refresh();
        BaseClass.zoomout(driver);
    }
    @Test
    void create_users() {

    }
}
