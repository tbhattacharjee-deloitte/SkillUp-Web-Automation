package Page.Manage;

import Helper.Util;
import com.aventstack.extentreports.ExtentTest;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.Arrays;
import java.util.List;

public class CategoriesPage {
    private final By categoriesXpath = By.xpath("(//a[contains(text() , 'Categories')])[2]");
    private final By addNewCat = By.xpath("//button[contains(text(), 'NEW CATEGORY')]");
    private final By mat_sel = By.xpath("//mat-select[@placeholder=\"Select Skills\"]");
    private final By createSkill = By.xpath("//button[contains(text(), 'CREATE')]");
    private final By pageDataxpath = By.xpath("//div[@class='mat-paginator-range-label']");
    private final By nxtPageXpath = By.xpath("(//span[@class='mat-button-wrapper'])[2]");
    private final By editXpath = By.xpath("//mat-icon[contains(text(), 'edit')]");
    private final By updateBtn = By.xpath("//button[contains(text(), 'UPDATE')]");
    private final By delBtn = By.xpath("//mat-icon[contains(text(), 'delete')]");

    private final String matOption = "//span[contains(text(), '%s')]";
    private final String inpGenPath = "//input[@placeholder='%s']";

    private WebDriver driver;
    private ExtentTest test;
    private Logger logger;

    public CategoriesPage(WebDriver driver, ExtentTest test, Logger logger) {
        this.driver = driver;
        this.test = test;
        this.logger = logger;
    }

    public void checkUserBtnClikability() {
        Util.jsClick(driver, categoriesXpath);
    }
    public void addNewCat() {
        // clicking on new category btn
        Util.explicitWait_visibility(driver, 5000, addNewCat);
        Util.jsClick(driver, addNewCat);

        // Entering new Category details
        Util.sendKey(driver, Util.getReplacedXpath(inpGenPath, "Enter name here"), "New Cat");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Util.click(driver, mat_sel);
        Util.click(driver, Util.getReplacedXpath(matOption, "Java"));
        Actions action = new Actions(driver);
        action.click(driver.findElement(By.xpath("//label[contains(text(), 'Skills')]"))).perform();
        Util.click(driver, createSkill);
    }

    public void editLastCat() {
        Util.threadSleep(2000);
        while (true) {
            String[] s = driver.findElement(pageDataxpath).getText().trim().split(" ");
            logger.debug("Checking str array: "+ Arrays.toString(s));
            if (s[2].equals(s[4])) break;
            Util.jsClick(driver, nxtPageXpath);
        }
        List<WebElement> list = driver.findElements(editXpath);
        WebElement last = list.get(list.size() - 1);
        last.click();
        Util.threadSleep(2000);
        Util.click(driver, mat_sel);
        driver.findElements(By.xpath("//mat-option/mat-pseudo-checkbox")).get(0).click();
        Actions action = new Actions(driver);
        action.click(driver.findElement(By.xpath("//label[contains(text(), 'Skills')]"))).perform();
        Util.click(driver, updateBtn);
    }
    public void delLastCat() {
        List<WebElement> list = driver.findElements(delBtn);
        WebElement last = list.get(list.size() - 1);
        last.click();
    }
}
