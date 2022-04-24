import Base.BaseClass;
import Page.Login;
import Page.Profile;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Profiletest {
    WebDriver driver;
    @BeforeTest
    void login() throws InterruptedException {
        driver = BaseClass.init();
        Login.login(driver,BaseClass.prop.getProperty("username"), BaseClass.prop.getProperty("password"));
    }
    @Test
    void viewprofile() {
        Profile.profileview(driver);
        Assert.assertEquals(Profile.actualname,Profile.expectedname);
    }

    @AfterTest
    void teardown(){
        driver.quit();
    }
}
