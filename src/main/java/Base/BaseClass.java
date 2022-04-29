package Base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.util.Properties;

public class BaseClass {

    public static Properties data;
    public static ExtentReports extent;
    public static ExtentTest test;
    public static Logger logger;
    public static WebDriver driver;
    static {
        try{
            data = new Properties();
            FileInputStream ip=new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\config\\data.properties");
            data.load(ip);
        } catch (Exception e) {
            e.printStackTrace();
        }
        extent = new ExtentReports();
        logger = LogManager.getLogger(BaseClass.class.getName());
        extent.attachReporter(new ExtentHtmlReporter("extent.html"));
    }


    public static Properties prop;
    static {
        try{
            prop = new Properties();
            FileInputStream ip=new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\config\\config.properties");
            prop.load(ip);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void init() {
        String browserName=prop.getProperty("browser");
        if(browserName.equals("chrome")){
            System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + "\\src\\main\\resources\\chromedriver.exe");
        }
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(prop.getProperty("url"));
    }
}
