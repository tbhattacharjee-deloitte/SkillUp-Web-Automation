package TestClasses;

import Base.BaseClass;
import Page.Login;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class AdminManagerTest {
    WebDriver driver;
    By profileDivPath = By.xpath("//div[@class='profile']");

    @BeforeTest
    void login() {
        driver = BaseClass.init();
        Login.login(driver,"mentorAdmin", "abc123");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(profileDivPath));
        driver.navigate().refresh();
        BaseClass.zoomout(driver);
    }
    @Test
    void create_users() {

    }
}
