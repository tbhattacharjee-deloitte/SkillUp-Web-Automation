import Base.BaseClass;
import Helper.Util;
import Page.Login;
import Page.LoginPageXLSInfo;
import Page.LoginScenario;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;


public class TestLogin {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = BaseClass.init();

    }

    //Looping through every scenario for Login
    @Test(dataProvider = "LoginData")
    public void loginTest(String user, String pwd, String exp) throws InterruptedException {

        //Login using credentials from excel
        LoginScenario.login(driver, user, pwd);

        if (exp.equals("Valid")) {
            String exp_title = "Home";
            WebElement home = driver.findElement(By.xpath("//p[@class='bd']"));
            String act_title=home.getText();
            if (exp_title.equals(act_title)) {
                By drop_down_btn=By.xpath("//button[@class='drop-btn']");
                By logout_button=By.xpath("//a[normalize-space()='Logout']");
                Util.jsClick(driver,drop_down_btn);
                Util.jsClick(driver,logout_button);
                driver.navigate().refresh();
                Assert.assertTrue(true);
            } else {
                Assert.assertTrue(false);
            }

        } else if (exp.equals("Invalid")) {
            LoginScenario.accept_alert(driver);
            Assert.assertTrue(true);

        }
    }

    @DataProvider(name = "LoginData")
    public String[][] getData() throws IOException {

        //Getting the data from excel
        LoginPageXLSInfo xlUtil = new LoginPageXLSInfo(System.getProperty("user.dir")+"\\src\\main\\resources\\LoginData.xlsx");

        int totalRows = xlUtil.getRowCount("Sheet1");
        int totalCols = xlUtil.getCellCount("Sheet1", 1);

        String loginData[][] = new String[totalRows][totalCols];

        for (int i = 1; i <= totalRows; i++) //1
        {
            for (int j = 0; j < totalCols; j++) //0
            {
                loginData[i - 1][j] = xlUtil.getCellData("Sheet1", i, j);
            }

        }
        return loginData;
    }

}