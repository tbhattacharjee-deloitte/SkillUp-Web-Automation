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

    static By init_5 = By.xpath("//span[@class='mat-option-text'][normalize-space()='5']");
    static By init_10 = By.xpath("//span[normalize-space()='10']");
    static By init_15 = By.xpath("//span[@class='mat-option-text'][normalize-space()='15']");



    @BeforeTest
    void login() throws InterruptedException {
        driver = BaseClass.init();
        Trainer_HomePage.login(driver,"vivek","vivek123");
        Thread.sleep(3000);
    }

    @Test
    void become_trainer() throws InterruptedException {
        Trainer_HomePage.goto_trainer(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
        driver.navigate().refresh();
        Thread.sleep(3000);
        driver.navigate().refresh();
    }


    @Test
    void page_initiation_5() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
        Trainer_HomePage.initiation(driver,init_5,5);
    }

    @Test
    void page_initiation_10() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
        Trainer_HomePage.initiation(driver,init_10,10);
    }

    @Test
    void page_initiation_15() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
        Trainer_HomePage.initiation(driver,init_15,15);
    }

    @Test
    void search_filter_name(){
        String skill_name = "Vivek";
        Trainer_HomePage.search(driver,skill_name,"name");

        driver.navigate().refresh();
    }

    @Test
    void search_filter_skill() throws InterruptedException {

        Util.zoomout(driver);
        String trainee_name = "HTML";
        Thread.sleep(3000);
        Trainer_HomePage.search(driver,trainee_name,"skill");
    }


//
//    //    @Test
//    void next_page(){
//        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
//        Util.click(driver.findElement());
//    }
//
//    //    @Test
//    void prev_page(){
//        Util.click(driver.findElement());
//    }
//




}
