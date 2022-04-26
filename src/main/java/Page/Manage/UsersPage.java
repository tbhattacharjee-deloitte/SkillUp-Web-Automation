package Page.Manage;

import Helper.ExtractData;
import Helper.User;
import Helper.Util;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

public class UsersPage {
    private final By users = By.xpath("//a[text() = 'Users']");
    private final By newUserBtnPath = By.xpath("//button[contains(text(), 'NEW USER')]");
    private final By createUserXpath = By.xpath("//button[contains(text(), 'CREATE')]");
    private final By pageDataxpath = By.xpath("//div[@class='mat-paginator-range-label']");
    private final By nxtPageXpath = By.xpath("(//span[@class='mat-button-wrapper'])[2]");
    private final By delXpath = By.xpath("//mat-icon[contains(text(), 'delete')]");

    private final String ipGenPath = "//*[@placeholder='%s']";
    private final String matOption = "//span[contains(text(), '%s')]";
    private WebDriver driver;
    private Logger logger;

    public UsersPage(WebDriver driver, Logger logger) {
        this.driver = driver;
        this.logger = logger;
    }

    public void checkUserBtnClikability() {
        Util.expectedWait_toClick(driver, 2000, users);
    }

    public void adduser() {
        Util.click(driver.findElement(newUserBtnPath)); // clicking on new user tab
        User user = ExtractData.getSingleUser(); // Data Extraction

        // filling addUser form
        Util.sendKey(driver, getReplacedXpath(ipGenPath,"Enter name here"), user.name);
        Util.sendKey(driver, getReplacedXpath(ipGenPath, "Enter email id"), user.email);
        Util.sendKey(driver, getReplacedXpath(ipGenPath, "Enter username"), user.username);
        Util.sendKey(driver, getReplacedXpath(ipGenPath, "Enter your age"), user.age);
        Util.sendKey(driver, getReplacedXpath(ipGenPath, "Enter your designation"), user.designation);
        Util.sendKey(driver, getReplacedXpath(ipGenPath, "Enter a bio"), user.bio);
        Util.sendKey(driver, getReplacedXpath(ipGenPath, "Enter your working experience"), user.workex);
        Util.sendKey(driver, getReplacedXpath(ipGenPath, "Enter password"), user.password);
        // Role drop down
        Util.click(driver, getReplacedXpath(ipGenPath, "Select Role"));
        Util.click(driver, getReplacedXpath(matOption, user.role));

        // select skills
        Util.threadSleep(3000);
        driver.findElement(By.xpath("//mat-select[@placeholder='Select Skills']")).click();
        for (String skill: user.skills) {
            Util.expectedWait_toClick(driver, 2000, getReplacedXpath(matOption, skill.trim()));
        }
        Actions action = new Actions(driver);
        action.click(driver.findElement(By.xpath("//label[contains(text(), 'Skills')]"))).perform();
        // create user
        Util.click(driver.findElement(createUserXpath));
    }

    public void deleteLastUser(ExtentTest test) {
        test.log(Status.INFO, "deleting last user");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        while (true) {
            String[] s = driver.findElement(pageDataxpath).getText().trim().split(" ");
            logger.debug("Checking str array: "+ Arrays.toString(s));
            if (s[2].equals(s[4])) break;
            Util.jsClick(driver, nxtPageXpath);
        }
        List<WebElement> lst = driver.findElements(delXpath);
        assert lst.size() > 0;
        logger.debug("List size = " + lst.size());
        WebElement lastItem = lst.get(lst.size()-1);
        Util.click(lastItem);
        test.log(Status.INFO, "Last user is deleted");
    }

    private By getReplacedXpath(String original, String toReplace) {
        return By.xpath(original.replace("%s", toReplace));
    }
}
