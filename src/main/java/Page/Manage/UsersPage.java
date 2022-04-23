package Page.Manage;

import Helper.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UsersPage {
    private final By users = By.xpath("//a[text() = 'Users']");
    private final By newUserBtnPath = By.xpath("//button[contains(text(), 'NEW USER')]");
    private WebDriver driver;

    public UsersPage(WebDriver driver) {
        this.driver = driver;
    }

    public void checkUserBtnClikability() {
        Util.expectedWait_toClick(driver, 2000, users);
    }

    public void adduser() {
        Util.click(driver.findElement(newUserBtnPath));
    }
}
