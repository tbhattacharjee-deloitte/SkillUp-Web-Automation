import Base.BaseClass;
import Helper.Util;
import Page.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class HomePage_Test{
    WebDriver driver;

    By init_5 = By.xpath("//span[@class='mat-option-text'][normalize-space()='5']");
    By init_10 = By.xpath("//span[normalize-space()='10']");
    By init_15 = By.xpath("//span[@class='mat-option-text'][normalize-space()='15']");
    String username = "vivek";
    String password = "vivek123";
    String name = "vivek";


    @BeforeTest
    void login() throws InterruptedException {
        driver = BaseClass.init();
        HomePage.login(driver,username,password);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));

//        HomePage.page_wait();
    }

    @Test (priority = 1)
    void become_trainer() throws InterruptedException {
        HomePage.goto_trainer(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
        driver.navigate().refresh();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));


        driver.navigate().refresh();
    }


    @Test (priority = 2)
    void page_initiation_5() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
        HomePage.initiation(driver,init_5,5);
    }

    @Test (priority = 3)
    void page_initiation_10() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
        HomePage.initiation(driver,init_10,10);
    }

    @Test (priority = 4)
    void page_initiation_15() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
        HomePage.initiation(driver,init_15,15);
    }

    @Test (priority = 5)
    void search_filter_name(){
        String skill_name = "Vivek";
        HomePage.search(driver,skill_name,"name");

        driver.navigate().refresh();
    }

    @Test (priority = 6)
    void search_filter_skill() throws InterruptedException {

        Util.zoomout(driver);
        String trainee_name = "HTML";
        HomePage.page_wait();
        HomePage.search(driver,trainee_name,"skill");

        driver.navigate().refresh();
        Util.zoomout(driver);
    }

    @Test (priority = 7)
    void next_page() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
        HomePage.page_wait();
        HomePage.navigate(driver,"next");
    }


    @Test (priority = 8)
    void prev_page() throws InterruptedException {
        HomePage.page_wait();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
        HomePage.navigate(driver,"prev");
    }

    @Test (priority = 9)
    void accept_skill() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
        HomePage.accept_skill(driver,name);
    }


    @Test (priority = 10)
    void delete_skill() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
        HomePage.delete_skill_req(driver,username);

        HomePage.page_wait();
        HomePage.goto_home(driver);
    }

    
    @Test (priority = 11)
    void BecomeTraineeTest() throws Exception{
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
        HomePage.page_wait();
        HomePage.goto_home(driver);
        HomePage.page_wait();
        driver.navigate().refresh();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        HomePage.BecomeTraineeButton(driver);
        String ActualURL = driver.getCurrentUrl();
        String ExpectedURL = "https://hu-monitorapp-front-urtjok3rza-wl.a.run.app/create-request?reqId=0&reqName=Select";
        assert ActualURL.equals(ExpectedURL);
    }
    // positive test cases
    @Test(priority = 12)
    void SelectCourseTest(){
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
        HomePage.SelectCourse(driver);
    }

    @Test(priority = 13)
    void AddDescriptionTest(){
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
        String bio = "Some random words";
        HomePage.EnterBio(driver, bio);
        String val = driver.findElement(By.xpath("//textarea")).getAttribute("value");
        assert bio.equals(val);
    }
    @Test(priority = 14)
    void StartDateTest() throws Exception{
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
        HomePage.EnterStartDate(driver, "2022-05-15");
        HomePage.page_wait();
    }

    @Test(priority = 15)
    void StartTimeTest(){
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
        HomePage.EnterStartTime(driver, "15:00:00");
    }

    @Test(priority = 16)
    void EndTimeTest(){
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
        HomePage.EnterEndTime(driver, "17:00:00");
    }

    @Test(priority = 17)
    void DurationTest(){
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
        HomePage.EnterDuration(driver, "2");
    }

    @Test(priority = 18)
    void CreateRequestTest() throws Exception{
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
        HomePage.ClickCreateButton(driver);
        HomePage.page_wait();
        String ActualURL = driver.getCurrentUrl();
        String ExpectedURL = "https://hu-monitorapp-front-urtjok3rza-wl.a.run.app/request-page";
        assert ActualURL.equals(ExpectedURL);
//        validation url https://hu-monitorapp-front-urtjok3rza-wl.a.run.app/request-page
    }

//    @Test(priority = 19)
//    void SearchBySkillTest(){
//        HomePage.SearchUser(driver, "Python");
//        driver.findElement(By.xpath("//tr[2]//td[2]")).getText();
//    }
//    @AfterTest
//    void CloseBrowser(){
//        driver.close();
//    }
    
    

}
