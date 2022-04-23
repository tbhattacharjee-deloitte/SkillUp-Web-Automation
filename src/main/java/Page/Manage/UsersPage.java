package Page.Manage;

import Helper.ExtractData;
import Helper.User;
import Helper.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import javax.swing.*;

public class UsersPage {
    private final By users = By.xpath("//a[text() = 'Users']");
    private final By newUserBtnPath = By.xpath("//button[contains(text(), 'NEW USER')]");
    private final By createUserXpath = By.xpath("//button[contains(text(), 'CREATE')]");
    private final String ipGenPath = "//*[@placeholder='%s']";
    private final String matOption = "//span[contains(text(), '%s')]";
    private WebDriver driver;

    public UsersPage(WebDriver driver) {
        this.driver = driver;
    }

    public void checkUserBtnClikability() {
        Util.expectedWait_toClick(driver, 2000, users);
    }

    public void adduser() {
        Util.click(driver.findElement(newUserBtnPath)); // clicking on new user tab
        User user = ExtractData.getSingleUser(); // Data Extraction

        // filling addUser form
        Util.sendKey(driver.findElement(getReplacedXpath(ipGenPath,"Enter name here")), user.name);
        Util.sendKey(driver.findElement(getReplacedXpath(ipGenPath, "Enter email id")), user.email);
        Util.sendKey(driver.findElement(getReplacedXpath(ipGenPath, "Enter username")), user.username);
        Util.sendKey(driver.findElement(getReplacedXpath(ipGenPath, "Enter your age")), user.age);
        Util.sendKey(driver.findElement(getReplacedXpath(ipGenPath, "Enter your designation")), user.designation);
        Util.sendKey(driver.findElement(getReplacedXpath(ipGenPath, "Enter a bio")), user.bio);
        Util.sendKey(driver.findElement(getReplacedXpath(ipGenPath, "Enter your working experience")), user.workex);
        Util.sendKey(driver.findElement(getReplacedXpath(ipGenPath, "Enter password")), user.password);
        // Role drop down
        Util.click(driver.findElement(getReplacedXpath(ipGenPath, "Select Role")));
        Util.click(driver.findElement(getReplacedXpath(matOption, user.role)));

        // select skills
        driver.findElement(By.xpath("//mat-select[@placeholder='Select Skills']")).click();
        for (String skill: user.skills) {
            Util.expectedWait_toClick(driver, 2000, getReplacedXpath(matOption, skill.trim()));
        }
        Actions action = new Actions(driver);
        action.click(driver.findElement(By.xpath("//label[contains(text(), 'Skills')]"))).perform();
        // create user
        Util.click(driver.findElement(createUserXpath));
    }

    private By getReplacedXpath(String original, String toReplace) {
        return By.xpath(original.replace("%s", toReplace));
    }
}
