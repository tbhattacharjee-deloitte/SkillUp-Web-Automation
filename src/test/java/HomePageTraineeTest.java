import Base.BaseClass;
import Page.HomePageTrainee;
import Page.Login;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class HomePageTraineeTest {
    WebDriver driver;
    @BeforeTest
    void login() {
        driver = BaseClass.init();
        Login.login(driver,"vivek", "vivek123");
    }
    @Test
    void BecomeTraineeTest() throws Exception{
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
        Thread.sleep(3000);
        driver.navigate().refresh();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        HomePageTrainee.BecomeTraineeButton(driver);
        String ActualURL = driver.getCurrentUrl();
        String ExpectedURL = "https://hu-monitorapp-front-urtjok3rza-wl.a.run.app/create-request?reqId=0&reqName=Select";
        assert ActualURL.equals(ExpectedURL);
    }
    // positive test cases
    @Test(priority = 1)
    void SelectCourseTest(){
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
        HomePageTrainee.SelectCourse(driver);
    }

    @Test(priority = 2)
    void AddDescriptionTest(){
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
        String bio = "Some random words";
        HomePageTrainee.EnterBio(driver, bio);
        String val = driver.findElement(By.xpath("//textarea")).getAttribute("value");
        assert bio.equals(val);
    }
    @Test(priority = 3)
    void StartDateTest() throws Exception{
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
        HomePageTrainee.EnterStartDate(driver, "2022-05-15");
        Thread.sleep(3000);
    }

    @Test(priority = 4)
    void StartTimeTest(){
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
        HomePageTrainee.EnterStartTime(driver, "15:00:00");
    }

    @Test(priority = 5)
    void EndTimeTest(){
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
        HomePageTrainee.EnterEndTime(driver, "17:00:00");
    }

    @Test(priority = 6)
    void DurationTest(){
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
        HomePageTrainee.EnterDuration(driver, "2");
    }

    @Test(priority = 7)
    void CreateRequestTest() throws Exception{
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
        HomePageTrainee.ClickCreateButton(driver);
        Thread.sleep(3000);
        String ActualURL = driver.getCurrentUrl();
        String ExpectedURL = "https://hu-monitorapp-front-urtjok3rza-wl.a.run.app/request-page";
        assert ActualURL.equals(ExpectedURL);
//        validation url https://hu-monitorapp-front-urtjok3rza-wl.a.run.app/request-page
    }

    @Test(priority = 8)
    void SearchBySkillTest(){
        HomePageTrainee.SearchUser(driver, "Python");
        driver.findElement(By.xpath("//tr[2]//td[2]")).getText();
    }
//    @AfterTest
//    void CloseBrowser(){
//        driver.close();
//    }
}
