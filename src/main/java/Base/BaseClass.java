package Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.util.Properties;

public class BaseClass {

    public static Properties data;
    static {
        try{
            data = new Properties();
            FileInputStream ip=new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\config\\data.properties");
            data.load(ip);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    public static WebDriver init() {
        String browserName=prop.getProperty("browser");
        if(browserName.equals("chrome")){
            System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + "\\src\\main\\resources\\chromedriver.exe");
        }
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(prop.getProperty("url"));
        return driver;
    }
}
