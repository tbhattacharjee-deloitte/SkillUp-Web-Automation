import Base.BaseClass;
import Helper.Util;
import Page.My_Trainings_Page;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class My_Trainings_Test {
    WebDriver driver;
    String username = BaseClass.prop.getProperty("user_username");
    String password = BaseClass.prop.getProperty("user_password");
    String usr_name = BaseClass.prop.getProperty("user_name");


    @BeforeTest
    void login() throws InterruptedException {
        driver = BaseClass.init();
        My_Trainings_Page.login(driver,username,password);
        Thread.sleep(3000);
    }

    @Test (priority = 1)
    void as_a_trainer() throws InterruptedException {
        My_Trainings_Page.goto_as_a_trainer(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
        driver.navigate().refresh();
        Thread.sleep(3000);
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
        Thread.sleep(3000);
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
    }

}
