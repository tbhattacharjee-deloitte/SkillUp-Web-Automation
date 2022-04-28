import Base.BaseClass;
import ListenersPackage.Listener;
import Page.Login;
import Page.Profile;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

@Listeners(Listener.class)
public class Profiletest {
    WebDriver driver;
    @BeforeTest
    @Parameters({"username", "password"})
    void login(String username, String password) throws InterruptedException {
        driver = BaseClass.init();
//        Login.login(driver,BaseClass.prop.getProperty("username"), BaseClass.prop.getProperty("password"));
        Login.login(driver, username, password);
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
