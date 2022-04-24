import Base.BaseClass;
import Page.LoginPageXLSInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestLogin {
    WebDriver driver;

    @Test(dataProvider = "LoginData")
    public void loginTest(String user, String pwd, String exp) {
        driver = BaseClass.init();

        WebElement txtUserName = driver.findElement(By.xpath("//input[@placeholder='Enter your username']"));
        txtUserName.clear();
        txtUserName.sendKeys(user);

        WebElement txtPassword = driver.findElement(By.xpath("//input[@placeholder='Enter your password']"));
        txtPassword.clear();
        txtPassword.sendKeys(pwd);




        //Login  button
        driver.findElement(By.xpath("/html/body/app-root/app-login-page/div/form/div/div[3]")).click();

        String exp_title ="Home";
        String act_title = driver.findElement(By.xpath("/html/body/app-root/app-sidenav/div/div[2]/app-landing-page/p")).getText();

        if (exp.equals("Valid")) {
            if (exp_title.equals(act_title)) {
                driver.findElement(By.xpath("/html/body/app-root/app-sidenav/app-header/div/div/button/img")).click();
                driver.findElement(By.xpath("/html/body/app-root/app-sidenav/app-header/div/div/div[2]/ul/li[2]/a")).click();
                Assert.assertTrue(true);
            } else {
                Assert.assertTrue(false);
            }
        } else if (exp.equals("Invalid")) {
            if (exp_title.equals(act_title)) {
                driver.findElement(By.xpath("/html/body/app-root/app-sidenav/app-header/div/div/button/img")).click();
                driver.findElement(By.xpath("/html/body/app-root/app-sidenav/app-header/div/div/div[2]/ul/li[2]/a")).click();
                Assert.assertTrue(false);
            } else {
                Assert.assertTrue(true);
            }
        }

    }

    @DataProvider(name = "LoginData")
    public String[][] getData() throws IOException {

        //get the data from excel
        LoginPageXLSInfo xlUtil = new LoginPageXLSInfo("C:\\Users\\sweetasingh\\SkillUp-Web-Automation\\src\\main\\resources\\LoginData.xlsx");

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
