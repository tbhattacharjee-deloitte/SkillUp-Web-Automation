import Base.BaseClass;
import Page.Login;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestClass {
    WebDriver driver;
    @BeforeTest
    void login() throws InterruptedException {
        driver = BaseClass.init();
        Login.login(driver,"mentorAdmin", "abc123");
    }
    @Test
    void test() {

    }
}
