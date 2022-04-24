package Page.Manage;

import Helper.Util;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class CategoriesPage {
    private final By categoriesXpath = By.xpath("(//a[contains(text() , 'Categories')])[2]");
    private final By addNewCat = By.xpath("//button[contains(text(), 'NEW CATEGORY')]");
    private final By mat_sel = By.xpath("//mat-select[@placeholder=\"Select Skills\"]");
    private final By createSkill = By.xpath("//button[contains(text(), 'CREATE')]");
    private final String matOption = "//span[contains(text(), '%s')]";
    private final String inpGenPath = "//input[@placeholder='%s']";

    private WebDriver driver;
    private ExtentTest test;

    public CategoriesPage(WebDriver driver, ExtentTest test) {
        this.driver = driver;
        this.test = test;
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

}
