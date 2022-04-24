import Base.BaseClass;
import Page.Login;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestClass {
    WebDriver driver;
    @BeforeTest
    void login() throws InterruptedException {
        driver = BaseClass.init();
        Login.login(driver,"vivek", "vivek123");

    }
    @Test
    void test() throws InterruptedException {
        Assert.assertEquals(Login.actualtext(driver),BaseClass.prop.getProperty("username"));
        System.out.println("Assertion Passed");

    }

    @AfterTest
    void teardown(){
        driver.quit();

    }
}
