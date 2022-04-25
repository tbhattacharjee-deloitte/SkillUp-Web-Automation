package Page;

import Base.BaseClass;
import Helper.Util;
import net.jodah.failsafe.internal.util.Assert;
import org.apache.poi.ss.formula.functions.T;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class My_Trainings_Page {

    static By loginXpath = By.xpath("//input[@placeholder='Enter your username']");
    static By passwordXpath = By.xpath("//input[@placeholder='Enter your password']");
    static By my_training = By.xpath("//a[normalize-space()='My Trainings']");
    static By as_a_trainer = By.xpath("//button[normalize-space()='As a Trainer']");
    static By search_box = By.xpath("//input[@id='mat-input-0']");
    static By first_name = By.xpath("//tbody/tr[1]/td[1]");
    static By skill_name = By.xpath("//tbody/tr[1]/td[2]");
    static By teach_btn = By.xpath("//button[@class='tbtn']");
    static By trainer_name  = By.xpath("//body/app-root/app-sidenav/div/div/app-training-detail/div/div/div/h2[1]");
    static By status_state = By.xpath("//h2[@class='status-state']");
    static By update_status = By.xpath("//button[@class='update-status']");
    static By update_conformation = By.xpath("//button[@class='del-dialog-yes']");
    static By add_ref = By.xpath("//button[@class='plus']//mat-icon[@role='img']");
    static By context = By.xpath("//input[@placeholder='Enter context here']");
    static By reference = By.xpath("//input[@placeholder='Enter reference here']");
    static By add_ref_btn = By.xpath("//button[@class='req-button']");
    static By message_box = By.xpath("//input[@placeholder='Type message']");
    static By send_msg = By.xpath("//button[@class='send-btn']//mat-icon[@role='img']");
    static By msg_sent = By.xpath("//li[@class='msg me ng-star-inserted']");
    static By MyTrainings = By.xpath("//a[normalize-space()='My Trainings']");
    static By TraineeButton = By.xpath("//*[text()='As a Trainee']");
    static By SearchBox = By.xpath("//input[@id='mat-input-0']");
    static By LearnButton = By.xpath("(//button[text()='Learn'])[1]");
    static By MessageBox = By.xpath("//input[@placeholder='Type message']");
    static By SendButton = By.xpath("//mat-icon[text()='send']");
    static By HomePage = By.xpath("//*[text()='Home']");



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


    //Navigate to My training - As a trainer page
    public static void goto_as_a_trainer(WebDriver driver){
        Util.jsClick(driver,my_training);
        driver.navigate().refresh();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
        driver.navigate().refresh();
        Util.jsClick(driver,as_a_trainer);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));

    }

    public static String get_data(String key){
        return(BaseClass.data.getProperty(key));
    }


    // Search box functionality
    public static void search(WebDriver driver,String search_name,String head) throws InterruptedException {
        Util.sendKey(driver.findElement(search_box), search_name);

        page_wait();

        if(head.equals("name")) {
            String appearing_name = driver.findElement(first_name).getText();
            System.out.println(appearing_name);
            assert appearing_name.equals(search_name);

        }

        if(head.equals("skill")){

            // Try block to ignore test if no skill is being trained
            try {
                String appearing_name = driver.findElement(skill_name).getText();
                assert appearing_name.equals(search_name);
            }

            catch (Exception e){
                System.out.println("Search Unsuccessful");
            }

            if(head.equals("status")) {
                page_wait();
            }
        }
    }


    public static void page_wait()throws InterruptedException {
        Thread.sleep(3000);
    }

    // clicking the teach button
    public static void teach(WebDriver driver) throws InterruptedException {
        search(driver,"In","status");

        Util.jsClick(driver,teach_btn);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
    }


    // verifying if the trainer name is correct
    public static void check_trainer_name(WebDriver driver, String name){
        String displayed_trainer_name = driver.findElement(trainer_name).getText();

        assert displayed_trainer_name.equals(name);

    }


    // Checking if the status is being updated properly
    public static void status_update(WebDriver driver) throws InterruptedException {

        page_wait();
        String cur_status = driver.findElement(status_state).getText();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));

        Util.jsClick(driver,update_status);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
        Util.jsClick(driver,update_conformation);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));

        String next_status = driver.findElement(status_state).getText();


        if(cur_status.equals(get_data("status1"))){
            assert next_status.equals(get_data("status2"));
        }

        else if (cur_status.equals(get_data("status2"))){
            assert next_status.equals(get_data("status3"));
        }

        else if (cur_status.equals(get_data("status3"))){
            assert next_status.equals(get_data("status4"));
        }

        else{
            assert cur_status.equals(get_data("status4"));
        }
    }


    public static void add_reference(WebDriver driver, String context_str,String reference_str) throws InterruptedException {
        Util.jsClick(driver,add_ref);

        Util.sendKey(driver.findElement(context),context_str);
        Util.sendKey(driver.findElement(reference), reference_str);

        Util.jsClick(driver,add_ref_btn);
        page_wait();

        String appearing_context = driver.findElement(By.xpath("//div[@class='bar']//h2")).getText();
        String appearing_reference = driver.findElement(By.xpath("//div[@class='left-container']//div[1]//div[1]//a[1]")).getText();

        System.out.println(appearing_reference);

        assert appearing_context.equals(context_str);

    }


    public static void send_message(WebDriver driver) throws InterruptedException {
        Util.sendKey(driver.findElement(message_box),BaseClass.data.getProperty("message"));
        page_wait();
        Util.jsClick(driver,send_msg);
        page_wait();
    }


    public static void MyTrainingPage(WebDriver driver){
        driver.findElement(MyTrainings).click();
    }


    public static void AllTrainings(WebDriver driver){
        driver.findElement(TraineeButton).click();
    }


    public static void SearchBySkills(WebDriver driver, String SearchData){
        driver.findElement(SearchBox).sendKeys(SearchData);
    }


    public static void Learn(WebDriver driver){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(LearnButton));
        driver.findElement(LearnButton).click();
    }


    public static void ChatBox(WebDriver driver, String Message){
//        ((JavascriptExecutor)driver).executeScript("arguments[0].value='"+ Message +"';", driver.findElement(MessageBox));
        ((JavascriptExecutor)driver).executeScript("document.getElementsByTagName('input')[0].value='"+Message+"';");
    }


    public static void Send(WebDriver driver){
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(SendButton));
    }


    public static void Home(WebDriver driver){
        driver.findElement(HomePage).click();
    }
}




