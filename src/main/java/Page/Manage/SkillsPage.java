package Page.Manage;

import Helper.Util;
import com.aventstack.extentreports.ExtentTest;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;

public class SkillsPage {
    private final By skillXpath = By.xpath("//a[contains(text(), 'Skills')]");
    private final By addNewSkill = By.xpath("//button[contains(text(), 'NEW SKILL')]");
    private final By inpXpath = By.xpath("//input[@placeholder = 'Enter skill name here']");
    private final By createXpath = By.xpath("//button[contains(text(), 'CREATE')]");
    private final By pageDataxpath = By.xpath("//div[@class='mat-paginator-range-label']");
    private final By nxtPageXpath = By.xpath("(//span[@class='mat-button-wrapper'])[2]");
    private final By delBtn = By.xpath("//mat-icon[contains(text(), 'delete')]");

    private WebDriver driver;
    private Logger logger;

    public SkillsPage(WebDriver driver,  Logger logger) {
        this.driver = driver;
        this.logger = logger;
    }

    public void checkClikability() {
        Util.expectedWait_toClick(driver, 2000, skillXpath);
    }
    public void addNewSkill() {
        Util.expectedWait_toClick(driver, 2000, addNewSkill);
        Util.sendKey(driver, inpXpath, "New Skills");
        Util.click(driver, createXpath);
    }
    public void delLastSkill() {
        Util.threadSleep(3000);
        while (true) {
            String[] s = driver.findElement(pageDataxpath).getText().trim().split(" ");
            logger.debug("Checking str array: "+ Arrays.toString(s));
            if (s[2].equals(s[4])) break;
            Util.jsClick(driver, nxtPageXpath);
        }
        Util.threadSleep(5000);
        List<WebElement> list = driver.findElements(delBtn);
        WebElement last = list.get(list.size() - 1);
        last.click();
    }
}
