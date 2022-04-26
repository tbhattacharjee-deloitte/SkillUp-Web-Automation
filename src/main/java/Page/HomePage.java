package Page;

import Helper.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class HomePage {

    static By loginXpath = By.xpath("//input[@placeholder='Enter your username']");
    static By passwordXpath = By.xpath("//input[@placeholder='Enter your password']");
    static By become_trainer = By.xpath("//button[normalize-space()='BECOME A TRAINER']");
    static By initiation = By.xpath("//div[@class='mat-select-arrow ng-tns-c52-3']");
    static By pagination_range = By.xpath("//div[@class='mat-paginator-range-label']");
    static By next_page_btn = By.xpath("//button[@aria-label='Next page']");
    static By prev_page_btn = By.xpath("//button[@aria-label='Previous page']");
    static By search_box = By.xpath("//input[@id='mat-input-0']");
    static By first_name = By.xpath("//tbody/tr[1]/td[1]");
    static By skill_name = By.xpath("//tbody/tr[1]/td[2]");
    static By del_btn = By.xpath("//tbody/tr[1]/td[4]/a[1]/mat-icon[2]");
    static By del_yes = By.xpath("//button[normalize-space()='Yes']");
    static By info_btn = By.xpath("//tbody/tr[1]/td[4]/a[1]/mat-icon[1]");
    static By accept_btn = By.xpath("//button[normalize-space()='Accept']");
    static By home_btn = By.xpath("//a[@routerlink='landing-page']");

    private static By TraineeButton = By.xpath("//*[text()='BECOME A TRAINEE']");
    private static By SelectSkill = By.xpath("//mat-option[@id='mat-option-1']");
    private static By Bio = By.xpath("//textarea");
    private static By CreateButton = By.xpath("//*[text()='CREATE']");
    private static By SearchBox = By.xpath("(//input)[1]");
    private static By Info = By.xpath("//*[text()='info']");
    private static By CloseButton = By.xpath("//button[text()='close']");


    //Function to log in to user account
    public static void login(WebDriver driver, String username, String password){

        //Entering username and password
        Util.sendKey(driver.findElement(loginXpath), username);
        Util.sendKey(driver.findElement(passwordXpath), password);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));

        //Clicking on submit and then zoomout
        Util.click(driver.findElement(By.xpath("/html/body/app-root/app-login-page/div/form/div/div[3]")));
        Util.zoomout(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
    }
    
    public static void page_wait() throws InterruptedException {
        Thread.sleep(3000);
    }

    public static void goto_home(WebDriver driver) throws InterruptedException {
        Util.jsClick(driver,home_btn);
        page_wait();
    }

    //Navigating to Become a trainer page
    public static void goto_trainer(WebDriver driver){
        Util.jsClick(driver,become_trainer);
        driver.navigate().refresh();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
        driver.navigate().refresh();

    }

    //Selecting the number of data to be displayed on the screen
    public static void initiation(WebDriver driver,By initiation_number,int number) throws InterruptedException {
        Util.zoomout(driver);

        // dropdown button
        Util.jsClick(driver,initiation);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));

//        page_wait();

        //selecting the number from dropdown
        Util.jsClick(driver,initiation_number);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));

        page_wait();


        //zoom-out to fit the data in the screen
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.body.style.zoom='40%'");


        //validating the number of data being displaying
        String range = driver.findElement(pagination_range).getText();
        int total = Integer.parseInt(range.substring(range.length()-2).strip());

        if(total > number){
            int display_num = Integer.parseInt(range.substring(4,6).strip());

            if(display_num == number){
                System.out.println("Pass");
            }
            else {
                System.out.println("Fail");
            }
        }
    }


    //Testing the search box functionality
    public static void search(WebDriver driver,String search_name,String head){
        Util.sendKey(driver.findElement(search_box), search_name);

        // Checking the search name functionality
        if(head.equals("name")) {
            String appearing_name = driver.findElement(first_name).getText();
            System.out.println(appearing_name);
            if (appearing_name.equals(search_name)) {
                System.out.println("Search Successful");
            } else {
                System.out.println("Search Unsuccessful");
            }
        }


        // Checking the search skill functionality
        if(head.equals("skill")){
            String appearing_name = driver.findElement(skill_name).getText();
            System.out.println(appearing_name);
            if (appearing_name.equals(search_name)) {
                System.out.println("Search Successful");
            } else {
                System.out.println("Search Unsuccessful");
            }
        }
    }


    //Checking the next and previous buttons
    public static void navigate(WebDriver driver,String nav) throws InterruptedException {

        String range = driver.findElement(pagination_range).getText();
        int total = Integer.parseInt(range.substring(range.length()-2).strip());


        if (total > 5) {

            //Next Page button
            if (nav.equals("next")) {
                Util.jsClick(driver, next_page_btn);
                driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));

                page_wait();
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

                page_wait();
                int num = Integer.parseInt((driver.findElement(pagination_range).getText()).substring(0, 1));
                if (num == 1) {
                    System.out.println("Successful");
                } else {
                    System.out.println("Unsuccessful");
                }
            }
        }
    }


    // Accept skill to train functionality
    public static void accept_skill(WebDriver driver, String name) throws InterruptedException {

        Util.jsClick(driver, info_btn);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));

//        page_wait();
        Boolean accept_true = driver.findElement(accept_btn).isDisplayed();
        System.out.println(accept_true);

        if (accept_true == true) {
            Util.jsClick(driver, accept_btn);
        } else {
            System.out.println("Fail");
        }

    }



    // Delete the user's skill request
    public static void delete_skill_req(WebDriver driver,String name) throws InterruptedException {
        Util.sendKey(driver.findElement(search_box), name);

        Boolean del_present = driver.findElement(del_btn).isDisplayed();
        System.out.println("Delete button present : "+del_present);

        if(del_present.equals("true")){
            Util.jsClick(driver,del_btn);
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));

            page_wait();

            Util.jsClick(driver,del_yes);
        }
    }

    // clicking trainee button on the homepage
    public static void BecomeTraineeButton(WebDriver driver){
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(TraineeButton));

//        Util.jsClick(driver,TraineeButton);
    }

    // providing details for creating request
    public static void SelectCourse(WebDriver driver){
        driver.findElement(By.xpath("//div[@id='mat-select-value-1']")).click();
        driver.findElement(SelectSkill).click();
    }


    public static void EnterBio(WebDriver driver, String bio){
        ((JavascriptExecutor)driver).executeScript("arguments[0].value='"+bio+"';",driver.findElement(Bio));
    }

    public static void EnterStartDate(WebDriver driver, String date){
//        driver.findElement(StartDate).sendKeys(date);
        ((JavascriptExecutor)driver).executeScript("document.getElementsByTagName('input')[0].value='"+date+"';");
//        ((JavascriptExecutor)driver).executeScript("arguments[0].value = '05/15/2022';", driver.findElement(StartDate));
    }

    public static void EnterStartTime(WebDriver driver, String sTime){
//        driver.findElement(StartTime).sendKeys(sTime);
        ((JavascriptExecutor)driver).executeScript("document.getElementsByTagName('input')[1].value='"+sTime+"';");
//        ((JavascriptExecutor)driver).executeScript("arguments[0].value = '"+sTime+"';", driver.findElement(StartTime));
    }

    public static void EnterEndTime(WebDriver driver, String eTime){
//        driver.findElement(EndTime).sendKeys(eTime);
        ((JavascriptExecutor)driver).executeScript("document.getElementsByTagName('input')[2].value='"+eTime+"';");
//        ((JavascriptExecutor)driver).executeScript("arguments[0].value = '"+eTime+"';", driver.findElement(EndTime));
    }

    public static void EnterDuration(WebDriver driver, String duration){
//        ((JavascriptExecutor)driver).executeScript("arguments[0].value = '"+duration+"';", driver.findElement(Duration));
        ((JavascriptExecutor)driver).executeScript("document.getElementsByTagName('input')[3].value='"+duration+"';");
    }

    public static void ClickCreateButton(WebDriver driver){
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(CreateButton));
    }

    public static void SearchUser(WebDriver driver, String searchKey){
        driver.findElement(SearchBox).sendKeys(searchKey);
    }

    public static void CheckInfo(WebDriver driver){
        driver.findElement(Info).click();
    }

    public static void CloseInfo(WebDriver driver){
        driver.findElement(CloseButton).click();
    }
}

