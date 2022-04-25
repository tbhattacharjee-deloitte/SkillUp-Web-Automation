import Base.BaseClass;
import Page.Login;
import Page.LoginPageXLSInfo;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static Base.BaseClass.prop;
import static org.testng.Reporter.clear;

public class TestLogin {
    WebDriver driver;


    @BeforeClass
    public void setup() {
        driver = BaseClass.init();

    }


    @Test(dataProvider = "LoginData")
    public void loginTest(String user, String pwd, String exp) throws InterruptedException  {

        Login.login(driver, user,pwd);


        String exp_title ="Home";
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
        WebElement home = driver.findElement(By.xpath("/html/body/app-root/app-sidenav/div/div[2]/app-landing-page/p"));
        String act_title=home.getText();

        if (exp.equals("Valid")) {
            if (exp_title.equals(act_title)) {
                System.out.println("actual title is" + act_title);
                driver.findElement(By.xpath("/html/body/app-root/app-sidenav/app-header/div/div/button")).click();
                driver.findElement(By.xpath("/html/body/app-root/app-sidenav/app-header/div/div/div[2]/ul/li[2]/a")).click();
                driver.navigate().refresh();
                Assert.assertTrue(true);
            } else {
                Assert.assertTrue(false);
            }
        } else if (exp.equals("Invalid")) {
            if (exp_title.equals(act_title)) {
                driver.findElement(By.xpath("/html/body/app-root/app-sidenav/app-header/div/div/button")).click();
                driver.findElement(By.xpath("/html/body/app-root/app-sidenav/app-header/div/div/div[2]/ul/li[2]/a")).click();
                driver.navigate().refresh();
                Assert.assertTrue(false);
            } else {
                WebDriverWait wait = new WebDriverWait(driver, 20);
                wait.until(ExpectedConditions.alertIsPresent());
                Alert alt=driver.switchTo().alert();
                Thread.sleep(5000);
                alt.accept();
                Thread.sleep(5000);
                driver.get(prop.getProperty("url"));

                //driver.navigate().to(driver.getCurrentURL());
                Assert.assertTrue(true);
            }
        }
        /*WebElement txtUserName = driver.findElement(By.xpath("//input[@placeholder='Enter your username']"));
        txtUserName.clear();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
        WebElement txtPassword = driver.findElement(By.xpath("//input[@placeholder='Enter your password']"));
        txtPassword.clear();*/


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
/*


        /*String exp_title ="Home";
        String act_title = driver.findElement(By.xpath("/html/body/app-root/app-sidenav/div/div[2]/app-landing-page/p")).getText();
        if (exp_title.equals(act_title)) {
            System.out.println("actual title is");
            driver.findElement(By.xpath("/html/body/app-root/app-sidenav/app-header/div/div/button/img")).click();
            driver.findElement(By.xpath("/html/body/app-root/app-sidenav/app-header/div/div/div[2]/ul/li[2]/a")).click();

        } else {
            System.out.println("ffaill");

        }*/
        /*WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();*/