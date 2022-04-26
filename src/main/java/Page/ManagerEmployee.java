package Page;

import Helper.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.time.Duration;


public class ManagerEmployee {

    static By employee_btn=By.xpath("//a[@href ='/employees']");
    static By search_icon = By.xpath("//input[@id='mat-input-0']");
    static By action_btn = By.xpath("/html/body/app-root/app-sidenav/div/div[2]/app-employees/div/div/table/tbody/tr[1]/td[4]/a/mat-icon");
    static By close_btn =By.xpath("/html/body/div[2]/div[2]/div/mat-dialog-container/app-profile-view/div/div/button");
    static By next_page_btn = By.xpath("/html/body/app-root/app-sidenav/div/div[2]/app-employees/div/div/mat-paginator/div/div/div[2]/button[2]");
    static By pagination_range = By.xpath("//div[@class='mat-paginator-range-label']");
    static By prev_page_btn = By.xpath("/html/body/app-root/app-sidenav/div/div[2]/app-employees/div/div/mat-paginator/div/div/div[2]/button[1]");
    static By items_perPage_btn =By.xpath("//*[@id='mat-select-0']");
    static By searched_name =By.xpath("/html/body/app-root/app-sidenav/div/div[2]/app-employees/div/div/table/tbody/tr/td[1]");
    static By searched_desigantion =By.xpath("/html/body/app-root/app-sidenav/div/div[2]/app-employees/div/div/table/tbody/tr[1]/td[2]");
    static By searched_experience =By.xpath("/html/body/app-root/app-sidenav/div/div[2]/app-employees/div/div/table/tbody/tr[1]/td[3]");


    //Taking user credential of Project Manager from excel sheet
    public static void data_manager(WebDriver driver) throws IOException {
        LoginPageXLSInfo xlUtil = new LoginPageXLSInfo("C:\\Users\\sweetasingh\\SkillUp-Web-Automation\\src\\main\\resources\\LoginData.xlsx");
        String manage_username=xlUtil.getCellData("Sheet1", 5, 0);
        String manage_password=xlUtil.getCellData("Sheet1", 5, 1);
        Login.login(driver, manage_username,manage_password);

    }

    //checking employee button functionality
    public static void goto_employee(WebDriver driver){
        Util.jsClick(driver,employee_btn);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));

    }

    //checking search field functionality
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

    //checking action button functionality
    public static void acceptButton(WebDriver driver){
        Util.jsClick(driver, action_btn);
        Util.jsClick(driver, close_btn);

    }

    //checking pagination functionality for next and previous button
    public static void checkPageButton(WebDriver driver,String nav) throws InterruptedException
    {
        //zoom-out to fit the data in the screen
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.body.style.zoom='50%'");

        String range = driver.findElement(pagination_range).getText();
        int total = Integer.parseInt(range.substring(range.length()-2).trim());

        if (total > 5) {
            //Next Page button
            if (nav.equals("next")) {
                Util.jsClick(driver, next_page_btn);
                driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));

                Thread.sleep(3000);
                int num = Integer.parseInt((driver.findElement(pagination_range).getText()).substring(0, 1));
                if (num == 6) {
                    System.out.println("Successful");
                } else {
                    System.out.println("Unsuccessful");
                }
            }


            // Previous Page button
            if (nav.equals("prev")) {
                Util.jsClick(driver, prev_page_btn);
                driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));

                Thread.sleep(3000);
                int num = Integer.parseInt((driver.findElement(pagination_range).getText()).substring(0, 1));
                if (num == 1) {
                    System.out.println("Successful");
                } else {
                    System.out.println("Unsuccessful");
                }
            }
        }
    }

    //checking pagination for items per page
    public static void page_init(WebDriver driver,By drop_down_number,int number) throws InterruptedException {
        //zoom-out to fit the data in the screen
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.body.style.zoom='40%'");

        // dropdown button
        Util.jsClick(driver,items_perPage_btn);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));


        //selecting the number from dropdown
        Util.jsClick(driver,drop_down_number);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));

        Thread.sleep(3000);


        //validating the number of data being displaying
        String range = driver.findElement(pagination_range).getText();
        int total = Integer.parseInt(range.substring(range.length()-2).trim());

        if(total > number){
            int display_num = Integer.parseInt(range.substring(4,6).trim());

            if(display_num == number){
                System.out.println("Pass");
            }
            else {
                System.out.println("Fail");
            }
        }
    }


}
