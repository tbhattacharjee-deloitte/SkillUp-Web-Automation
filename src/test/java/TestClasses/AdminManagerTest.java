package TestClasses;

import Base.BaseClass;
import Helper.Util;
import ListenersPackage.Listener;
import Page.Login;
import Page.Manage.CategoriesPage;
import Page.Manage.SkillsPage;
import Page.Manage.UsersPage;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

import java.util.List;

@Listeners(Listener.class)
public class AdminManagerTest {
    WebDriver driver;
    By profileDivPath = By.xpath("//div[@class='profile']");
    By manageBtnPath = By.xpath("//a[text() = 'Manage']");
    By dropdownListPath = By.xpath("//div[@class='next-to-training']/ul/li");
    By users = By.xpath("//a[text() = 'Users']");
    By newUserBtnPath = By.xpath("//button[contains(text(), 'NEW USER')]");


    private UsersPage usersPage;
    private CategoriesPage catPage;
    private SkillsPage skillsPage;


    @BeforeTest
    @Parameters({"adminUsername", "adminPassword"})
    public void login(String adminUsername, String adminPassword) throws InterruptedException {
        // initialize driver
        driver = BaseClass.init();
        // login
//        ArrayList<String> admin = ExtractData.getAdminDetails();
        Login.login(driver,adminUsername, adminPassword);
        // refresh
        Util.explicitWait_visibility(driver, 5000, profileDivPath);
        driver.navigate().refresh();
        //BaseClass.zoomout(driver);
    }

    @Test(priority = 1)
    public void validate_manageTab() {
        // validate if manage tab is present
        Util.expectedWait_toClick(driver, 2000, manageBtnPath);

        // validate if the dropdown List under manage have all the required tabs
        List<WebElement> dropDownList = driver.findElements(dropdownListPath);
        String[] check = {"Users", "Categories", "Skills"};
        BaseClass.test.log(Status.INFO, "Validating the buttons under dropdown of Manage Tab");
        for (int i = 0; i < dropDownList.size(); i++) {
            assert check[i].equalsIgnoreCase(dropDownList.get(i).getText());
        }
        usersPage = new UsersPage(driver, BaseClass.logger);
    }

    @Test (priority = 2)
    public void userClickability() {
        usersPage.checkUserBtnClikability();
    }

    @Test (priority = 3)
    public void addUser() {
        usersPage.adduser();
        BaseClass.test.log(Status.INFO, "User added successfully");
    }

    @Test (priority = 4)
    public void deleteLastUser() {
        usersPage.deleteLastUser(BaseClass.test);
    }

    @Test (priority = 5)
    public void categoryClikability() {
        catPage = new CategoriesPage(driver, BaseClass.logger);
        catPage.checkUserBtnClikability();
    }
    @Test (priority = 6)
    public void addNewCat() {
        catPage.addNewCat();
    }

    @Test(priority = 7)
    public void editLastCart() {
        catPage.editLastCat();
    }

    @Test (priority = 8)
    public void delLastCategory() {
        catPage.delLastCat();
    }

    @Test (priority = 9)
    public void checkClikabilityOfSkill() {
        skillsPage = new SkillsPage(driver, BaseClass.logger);
        Util.implicitWait(driver,2000);
        skillsPage.checkClikability();
    }

    @Test (priority = 10)
    public void addNewSkill() {
        skillsPage.addNewSkill();
    }

    @Test (priority = 11)
    public void delLastSkill() {
        skillsPage.delLastSkill();
    }

    @AfterTest
    public void closeDriver() {
        Util.implicitWait(driver, 3000);
        driver.close();
    }
}
