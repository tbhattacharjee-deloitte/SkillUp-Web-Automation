import Base.BaseClass;
import Page.Login;
import Page.MyTrainingsTrainee;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class MyTrainingsTraineeTest {
    WebDriver driver;
    @BeforeTest
    void login() {
        driver = BaseClass.init();
        Login.login(driver,"vivek", "vivek123");
    }
    @Test
    void MyTrainingsPage() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        MyTrainingsTrainee.MyTrainingPage(driver);
        String ActualURL = driver.getCurrentUrl();
        String ExpectedURL = "https://hu-monitorapp-front-urtjok3rza-wl.a.run.app/my-trainings?trainingAs=trainee";
        assert ActualURL.equals(ExpectedURL);
    }
    @Test(priority = 1)
    void ClickAsATrainee() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        MyTrainingsTrainee.AllTrainings(driver);
    }

    @Test(priority = 2)
    void SearchBySkills() throws Exception{
        driver.navigate().refresh();
        Thread.sleep(4000);
        driver.navigate().refresh();
        String key = "Python";
        MyTrainingsTrainee.SearchBySkills(driver, key);
        String skill = driver.findElement(By.xpath("//tr[1]//td[2]")).getText();
        assert key.equals(skill);
    }
    @Test(priority = 3)
    void CheckStatus() throws Exception{
        driver.navigate().refresh();
        Thread.sleep(3000);
        String status = ((JavascriptExecutor)driver).executeScript("return document.getElementsByTagName('td')[2].innerText").toString();
        System.out.println(status);
    }
    @Test(priority = 3)
    void ClickLearn() throws Exception{
        driver.navigate().refresh();
        Thread.sleep(3000);
        driver.navigate().refresh();
        MyTrainingsTrainee.Learn(driver);
        Thread.sleep(5000);
        String ActualURL = driver.getCurrentUrl();
        String SubString = ActualURL.substring(0,72);
        String ExpectedSubString = "https://hu-monitorapp-front-urtjok3rza-wl.a.run.app/training?trainingId=";
        assert SubString.equals(ExpectedSubString);
    }

    @Test(priority = 4)
    void MessageBox(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        String message = "some message";
        MyTrainingsTrainee.ChatBox(driver, message);
        String val = ((JavascriptExecutor)driver).executeScript("return document.getElementsByTagName('input')[0].value").toString();
        assert val.equals(message);
    }
    @Test(priority = 5)
    void Send() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        MyTrainingsTrainee.Send(driver);
    }
    @Test(priority = 6)
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
    void CloseBrowser(){
        driver.close();
    }
}
