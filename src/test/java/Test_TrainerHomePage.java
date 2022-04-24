import Base.BaseClass;
import Helper.Util;
import Page.Trainer_HomePage;
import net.bytebuddy.build.Plugin;
import org.apache.logging.log4j.core.net.Priority;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class Test_TrainerHomePage {
    WebDriver driver;

    By init_5 = By.xpath("//span[@class='mat-option-text'][normalize-space()='5']");
    By init_10 = By.xpath("//span[normalize-space()='10']");
    By init_15 = By.xpath("//span[@class='mat-option-text'][normalize-space()='15']");
    String username = "vivek";
    String password = "vivek123";


    @BeforeTest
    void login() throws InterruptedException {
        driver = BaseClass.init();
        Trainer_HomePage.login(driver,username,password);
        Thread.sleep(3000);
    }

    @Test (priority = 1)
    void become_trainer() throws InterruptedException {
        Trainer_HomePage.goto_trainer(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
        driver.navigate().refresh();
        Thread.sleep(3000);
        driver.navigate().refresh();
    }


    @Test (priority = 2)
    void page_initiation_5() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
        Trainer_HomePage.initiation(driver,init_5,5);
    }

    @Test (priority = 3)
    void page_initiation_10() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
        Trainer_HomePage.initiation(driver,init_10,10);
    }

    @Test (priority = 4)
    void page_initiation_15() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
        Trainer_HomePage.initiation(driver,init_15,15);
    }

    @Test (priority = 5)
    void search_filter_name(){
        String skill_name = "Vivek";
        Trainer_HomePage.search(driver,skill_name,"name");

        driver.navigate().refresh();
    }

    @Test (priority = 6)
    void search_filter_skill() throws InterruptedException {

        Util.zoomout(driver);
        String trainee_name = "HTML";
        Thread.sleep(3000);
        Trainer_HomePage.search(driver,trainee_name,"skill");

        driver.navigate().refresh();
        Util.zoomout(driver);
    }


    @Test (priority = 7)
    void next_page() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
        Thread.sleep(3000);
        Trainer_HomePage.navigate(driver,"next");
    }


    @Test (priority = 8)
    void prev_page() throws InterruptedException {
        Thread.sleep(3000);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
        Trainer_HomePage.navigate(driver,"prev");
    }

    @Test (priority = 9)
    void accept_skill() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
        Trainer_HomePage.accept_skill(driver);
    }


    @Test (priority = 10)
    void delete_skill() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
        Trainer_HomePage.delete_skill_req(driver,username);
    }

}
