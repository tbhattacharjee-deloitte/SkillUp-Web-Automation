package Page;

import Helper.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static Page.Login.loginXpath;
import static Page.Login.passwordXpath;

public class ManagerEmployee {
    static By loginXpath = By.xpath("//input[@placeholder='Enter your username']");
    static By passwordXpath = By.xpath("//input[@placeholder='Enter your password']");

    static By employee_btn=By.xpath("//a[@href ='/employees']");
    static By search_icon = By.xpath("//input[@id='mat-input-0']");
    static By action_btn = By.xpath("/html/body/app-root/app-sidenav/div/div[2]/app-employees/div/div/table/tbody/tr[1]/td[4]/a/mat-icon");
    static By close_btn =By.xpath("//*[@id='mat-dialog-1']/app-profile-view/div/div/button");
    static By next_page_btn = By.xpath("//button[@aria-label='Next page']");
    static By prev_page_btn = By.xpath("//button[@aria-label='Previous page']");
    static By items_perPage_btn =By.xpath("//*[@id='mat-select-0']");
    static By searched_name =By.xpath("/html/body/app-root/app-sidenav/div/div[2]/app-employees/div/div/table/tbody/tr/td[1]");
    static By searched_desigantion =By.xpath("/html/body/app-root/app-sidenav/div/div[2]/app-employees/div/div/table/tbody/tr[1]/td[2]");
    static By searched_experience =By.xpath("/html/body/app-root/app-sidenav/div/div[2]/app-employees/div/div/table/tbody/tr[1]/td[3]");


    public static void login(WebDriver driver, String username, String password){

        Util.sendKey(driver.findElement(loginXpath), username);
        Util.sendKey(driver.findElement(passwordXpath), password);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
        Util.click(driver.findElement(By.xpath("/html/body/app-root/app-login-page/div/form/div/div[3]")));
        Util.zoomout(driver);
        driver.navigate().refresh();




    }

    public static void goto_employee(WebDriver driver){
        driver.navigate().refresh();
        Util.jsClick(driver,employee_btn);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));


    }


    public static void searchBy(WebDriver driver,String search_by_value,String search_status ){
        Util.sendKey(driver.findElement(search_icon), search_by_value);

        if(search_status.equals("Name")) {
            String searched_appName = driver.findElement(searched_name).getText();
            System.out.println(searched_appName);
            if (searched_appName.equals(searched_name)) {
                System.out.println("Searched name found");
            } else {
                System.out.println("Search named not found");
            }
        }

        if(search_status.equals("Designation")){
            String searched_appName = driver.findElement(searched_desigantion).getText();
            System.out.println(searched_appName);
            if (searched_appName.equals(searched_desigantion)) {
                System.out.println("Search designation found");
            } else {
                System.out.println("Search designation not found");
            }
        }

        if(search_status.equals("Experience")){
            String searched_appName = driver.findElement(searched_experience).getText();
            System.out.println(searched_appName);
            if (searched_appName.equals(searched_experience)) {
                System.out.println("Search experience found");
            } else {
                System.out.println("Search designation not found");
            }
        }
    }

    public static void acceptButton(WebDriver driver){
        Util.jsClick(driver, action_btn);
        Util.jsClick(driver, close_btn);

    }

    public static void checkPageButton(WebDriver driver)
    {
        Util.jsClick(driver, next_page_btn);
        Boolean accept_true = driver.findElement(next_page_btn).isEnabled();
        System.out.println(accept_true+ "Next page is enabled");
        Util.jsClick(driver, prev_page_btn);
        Boolean accept_true1 = driver.findElement(prev_page_btn).isEnabled();
        System.out.println(accept_true1+ "Previous page is enabled");
        Util.jsClick(driver, items_perPage_btn);
        Boolean accept_true2 = driver.findElement(items_perPage_btn).isEnabled();
        System.out.println(accept_true2+ "Items per page is enabled");


    }
}
