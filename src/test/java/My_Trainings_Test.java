import Base.BaseClass;
import Helper.Util;
import Page.My_Trainings_Page;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class My_Trainings_Test {
    WebDriver driver;
//    String username = BaseClass.prop.getProperty("user_username");
//    String password = BaseClass.prop.getProperty("user_password");
    String usr_name = BaseClass.prop.getProperty("user_name");


    @BeforeTest
    @Parameters({"username", "password"})
    void login(String username, String password) throws InterruptedException {
        driver = BaseClass.init();
        My_Trainings_Page.login(driver,username,password);
        Util.threadSleep(3000);
    }

    @Test (priority = 1)
    void as_a_trainer() throws InterruptedException {
        My_Trainings_Page.goto_as_a_trainer(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
        driver.navigate().refresh();
        Util.threadSleep(3000);
        driver.navigate().refresh();
    }

    @Test (priority = 2)
    void search_filter_name() throws InterruptedException {
        String skill_name = My_Trainings_Page.get_data("name");

        My_Trainings_Page.search(driver,skill_name,"name");

        driver.navigate().refresh();
    }

    @Test (priority = 3)
    void search_filter_skill() throws InterruptedException {
        Util.zoomout(driver);
        String trainee_name = My_Trainings_Page.get_data("skill");
        Util.threadSleep(3000);
        My_Trainings_Page.search(driver,trainee_name,"skill");

        driver.navigate().refresh();
        Util.zoomout(driver);
    }

    @Test (priority = 4)
    void teach_btn() throws InterruptedException {
        My_Trainings_Page.teach(driver);
    }

    @Test (priority = 5)
    void check_cred(){
        My_Trainings_Page.check_trainer_name(driver,usr_name);
    }

    @Test (priority = 6)
    void status_updating() throws InterruptedException {
        My_Trainings_Page.status_update(driver);
    }

    @Test (priority = 7)
    void adding_reference() throws InterruptedException {

        System.out.println(My_Trainings_Page.get_data("context")+" "+My_Trainings_Page.get_data("reference"));
        My_Trainings_Page.add_reference(driver,My_Trainings_Page.get_data("context"),My_Trainings_Page.get_data("reference"));
    }

    @Test (priority = 8)
    void send_message_trainer() throws InterruptedException {
        My_Trainings_Page.send_message(driver);
        Util.threadSleep(3000);
        My_Trainings_Page.goto_as_a_trainer(driver);
        Util.threadSleep(3000);
    }

    @Test (priority = 9)
    void MyTrainingsPage() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        My_Trainings_Page.MyTrainingPage(driver);
        String ActualURL = driver.getCurrentUrl();
        String ExpectedURL = "https://hu-monitorapp-front-urtjok3rza-wl.a.run.app/my-trainings?trainingAs=trainee";
        assert ActualURL.equals(ExpectedURL);
    }

    @Test(priority = 10)
    void ClickAsATrainee() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        My_Trainings_Page.AllTrainings(driver);
    }

    @Test(priority = 11)
    void SearchBySkills() throws Exception{
        driver.navigate().refresh();
        Util.threadSleep(3000);
        driver.navigate().refresh();
        String key = "Python";
        My_Trainings_Page.SearchBySkills(driver, key);
        String skill = driver.findElement(By.xpath("//tr[1]//td[2]")).getText();
        assert key.equals(skill);
    }


    @Test(priority = 12)
    void CheckStatus() throws Exception{
        driver.navigate().refresh();
        Util.threadSleep(3000);
        String status = ((JavascriptExecutor)driver).executeScript("return document.getElementsByTagName('td')[2].innerText").toString();
        System.out.println(status);
    }


    @Test(priority = 13)
    void ClickLearn() throws Exception{
        driver.navigate().refresh();
        Util.threadSleep(3000);
        driver.navigate().refresh();
        My_Trainings_Page.Learn(driver);
        Util.threadSleep(3000);
        String ActualURL = driver.getCurrentUrl();
        String SubString = ActualURL.substring(0,72);
        String ExpectedSubString = "https://hu-monitorapp-front-urtjok3rza-wl.a.run.app/training?trainingId=";
        assert SubString.equals(ExpectedSubString);
    }


    @Test(priority = 14)
    void MessageBox(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        String message = "some message";
        My_Trainings_Page.ChatBox(driver, message);
        String val = ((JavascriptExecutor)driver).executeScript("return document.getElementsByTagName('input')[0].value").toString();
        assert val.equals(message);
    }


    @Test(priority = 15)
    void Send() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        My_Trainings_Page.Send(driver);
    }


    @Test(priority = 16)
    void CheckMessage(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        List<WebElement> Received = driver.findElements(By.xpath("//li[@class='msg him ng-star-inserted']"));
        List<WebElement> Sent = driver.findElements(By.xpath("//li[@class='msg me ng-star-inserted']"));
        for(int i = 0; i < Received.size(); i++){
            System.out.println(Received.get(i).getText());
        }
        for(int j = 0; j < Sent.size(); j++){
            System.out.println(Sent.get(j).getText());
        }
    }

    @AfterTest
    void closeDriver() {
        driver.close();
    }

}
