import Base.BaseClass;
import Page.HomePageTrainee;
import Page.Login;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class HomePageTraineeTest {
    WebDriver driver;
    @BeforeTest
    void login() {
        driver = BaseClass.init();
        Login.login(driver,"vivek", "vivek123");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
    }
    @Test
    void BecomeTraineeTest() throws Exception{
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
        Thread.sleep(3000);
        driver.navigate().refresh();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        HomePageTrainee.BecomeTraineeButton(driver);
        String ActualURL = driver.getCurrentUrl();
        Thread.sleep(6000);
        String ExpectedURL = "https://hu-monitorapp-front-urtjok3rza-wl.a.run.app/create-request?reqId=0&reqName=Select";
        assert ActualURL.equals(ExpectedURL);
    }
    // positive test cases
    @Test(priority = 1)
    void SelectCourseTest() throws Exception{
        Thread.sleep(4000);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
        HomePageTrainee.SelectCourse(driver);
        Thread.sleep(4000);
    }

    @Test(priority = 2)
    void AddDescriptionTest(){
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
        String bio = "Some random words";
        String val = HomePageTrainee.EnterBio(driver, bio);
        if(val.equals(bio)){
            System.out.println("Pass");
        }
        else{
            System.out.println("Fail");
        }
    }

    @Test(priority = 3)
    void StartDateTest(){
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
        HomePageTrainee.EnterStartDate(driver);
    }

    @Test(priority = 4)
    void StartTimeTest(){
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
        HomePageTrainee.EnterStartTime(driver, "0300PM");
    }

    @Test(priority = 5)
    void EndTimeTest(){
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
        HomePageTrainee.EnterEndTime(driver, "0500PM");
    }

    @Test(priority = 6)
    void DurationTest(){
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
        HomePageTrainee.EnterDuration(driver, "2");
    }

    @Test(priority = 7)
    void CreateRequestTest(){
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
        HomePageTrainee.ClickCreateButton(driver);
    }
}
