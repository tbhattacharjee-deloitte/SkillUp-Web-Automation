package Page;

import Helper.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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
    static By trainer_name  = By.xpath("//div[@class='content-2']//h2[1]");
    static By status_state = By.xpath("//h2[@class='status-state']");
    static By update_status = By.xpath("//button[@class='update-status']");
    static By update_conformation = By.xpath("//button[@class='del-dialog-yes']");
    static By add_ref = By.xpath("//button[@class='plus']//mat-icon[@role='img']");
    static By context = By.xpath("//input[@placeholder='Enter context here']");
    static By reference = By.xpath("//input[@placeholder='Enter reference here']");
    static By add_ref_btn = By.xpath("//button[@class='req-button']");



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


    // Search box functionality
    public static void search(WebDriver driver,String search_name,String head) throws InterruptedException {
        Util.sendKey(driver.findElement(search_box), search_name);

        Thread.sleep(3000);

        if(head.equals("name")) {
            String appearing_name = driver.findElement(first_name).getText();
            System.out.println(appearing_name);
            if (appearing_name.equals(search_name)) {
                System.out.println("Search Successful");
            } else {
                System.out.println("Search Unsuccessful");
            }
        }

        if(head.equals("skill")){

            // Try block to ignore test if no skill is being trained
            try {
                String appearing_name = driver.findElement(skill_name).getText();
                System.out.println(appearing_name);

                if (appearing_name.equals(search_name)) {
                    System.out.println("Search Successful");
                } else {
                    System.out.println("Search Unsuccessful");
                }
            }

            catch (Exception e){
                System.out.println("Search Unsuccessful");
            }
        }
    }



    // clicking the teach button
    public static void teach(WebDriver driver){
        Util.jsClick(driver,teach_btn);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
    }

    // verifying if the trainer name is correct
    public static void check_trainer_name(WebDriver driver, String name){
        String displayed_trainer_name = driver.findElement(trainer_name).getText();

        if(displayed_trainer_name.equals(name)){
            System.out.println("Pass");
        }
    }

    // Checking if the status is being updated properly
    public static void status_update(WebDriver driver){
        String cur_status = driver.findElement(status_state).getText();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));

        Util.jsClick(driver,update_status);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
        Util.jsClick(driver,update_conformation);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));

        String next_status = driver.findElement(status_state).getText();


        if((cur_status == "running") && (next_status == "assignment")){
            System.out.println("Pass");
        }

        else if ((cur_status == "assignment") && (next_status == "evaluating")){
            System.out.println("Pass");
        }

        else if ((cur_status == "evaluating") && (next_status == "completed")){
            System.out.println("Pass");
        }

        else if ((cur_status == "completed")){
            System.out.println("Pass");
        }

        else{
            System.out.println("Fail");
        }
    }


    public static void add_reference(WebDriver driver, String context_str,String reference_str) throws InterruptedException {
        Util.jsClick(driver,add_ref);

        Util.sendKey(driver.findElement(context),context_str);
        Util.sendKey(driver.findElement(reference), reference_str);

        Util.jsClick(driver,add_ref_btn);
        Thread.sleep(3000);

        String appearing_context = driver.findElement(By.xpath("//div[@class='bar']//h2")).getText();
        String appearing_reference = driver.findElement(By.xpath("//a[@class='link']")).getText();



        if((appearing_context.equals(context_str)) && (appearing_reference.equals(reference_str))){
            System.out.println("Pass");
        }
        else{
            System.out.println("Fail");
        }

    }


}
