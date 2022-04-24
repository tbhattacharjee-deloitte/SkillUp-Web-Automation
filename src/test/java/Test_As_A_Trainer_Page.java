import Base.BaseClass;
import Helper.Util;
import Page.As_A_Trainer_Page;
import Page.Trainer_HomePage;
import net.bytebuddy.build.Plugin;
import org.apache.logging.log4j.core.net.Priority;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class Test_As_A_Trainer_Page {
    WebDriver driver;
    String username = "vivek";
    String password = "vivek123";
    String name = "vivek";


    @BeforeTest
    void login() throws InterruptedException {
        driver = BaseClass.init();
        Trainer_HomePage.login(driver,username,password);
        Thread.sleep(3000);
    }

    @Test (priority = 1)
    void as_a_trainer() throws InterruptedException {
        As_A_Trainer_Page.goto_as_a_trainer(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
        driver.navigate().refresh();
        Thread.sleep(3000);
        driver.navigate().refresh();
    }

    @Test (priority = 2)
    void search_filter_name() throws InterruptedException {
        String skill_name = "Somnath";
        As_A_Trainer_Page.search(driver,skill_name,"name");

        driver.navigate().refresh();
    }

    @Test (priority = 3)
    void search_filter_skill() throws InterruptedException {
        Util.zoomout(driver);
        String trainee_name = "Java";
        Thread.sleep(3000);
        As_A_Trainer_Page.search(driver,trainee_name,"skill");

        driver.navigate().refresh();
        Util.zoomout(driver);
    }

    @Test (priority = 4)
    void teach_btn(){
        As_A_Trainer_Page.teach(driver);
    }

    @Test (priority = 5)
    void check_cred(){
        As_A_Trainer_Page.check_trainer_name(driver,name);
    }

    @Test (priority = 6)
    void status_updating(){
        As_A_Trainer_Page.status_update(driver);
    }

    @Test (priority = 7)
    void adding_reference() throws InterruptedException {
        As_A_Trainer_Page.add_reference(driver,"youtube","www.youtube.com");
    }

}
