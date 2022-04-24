package Page;

import Helper.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class As_A_Trainer_Page {

    static By my_training = By.xpath("//a[normalize-space()='My Trainings']");
    static By as_a_trainer = By.xpath("//button[normalize-space()='As a Trainer']");
    static By search_box = By.xpath("//input[@id='mat-input-0']");
    static By first_name = By.xpath("//tbody/tr[1]/td[1]");
    static By skill_name = By.xpath("//tbody/tr[1]/td[2]");

    public static void goto_as_a_trainer(WebDriver driver){
        Util.jsClick(driver,my_training);
        driver.navigate().refresh();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
        driver.navigate().refresh();
        Util.jsClick(driver,as_a_trainer);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));

    }

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
}
