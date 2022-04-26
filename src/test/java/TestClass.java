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
        Login.login(driver,BaseClass.prop.getProperty("username"), BaseClass.prop.getProperty("password"));

    }
    @Test
    void test() throws InterruptedException {
        Assert.assertEquals(Login.actualtext(driver),BaseClass.prop.getProperty("name"));
        System.out.println("Assertion Passed");

    }

    @AfterTest
    void teardown(){
        driver.quit();

    }
}