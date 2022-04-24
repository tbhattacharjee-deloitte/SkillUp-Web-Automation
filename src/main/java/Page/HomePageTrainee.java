package Page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class HomePageTrainee {
    private static By TraineeButton = By.xpath("//*[text()='BECOME A TRAINEE']");
    private static By SelectSkill = By.xpath("//mat-option[@id='mat-option-1']");
    private static By Bio = By.xpath("//textarea[@placeholder='Enter request details']");
    private static By StartDate = By.xpath("//input[@class='request-form pp ng-invalid ng-touched ng-dirty']");
    private static By StartTime = By.xpath("(//*[@class='request-form1 ng-pristine ng-invalid ng-touched'])[1]");
    private static By EndTime = By.xpath("(//*[@class='request-form1 ng-pristine ng-invalid ng-touched'])[2]");
    private static By Duration = By.xpath("//input[@class='request-form ng-pristine ng-invalid ng-touched']");
    private static By CreateButton = By.xpath("//*[text()='CREATE']");
    private static By SearchBox = By.xpath("//input[@id='mat-input-0']");
    private static By Info = By.xpath("//*[text()='info']");
    private static By CloseButton = By.xpath("//button[text()='close']");


    // clicking trainee button on the homepage
    public static void BecomeTraineeButton(WebDriver driver){
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(TraineeButton));
    }

    // providing details for creating request
    public static void SelectCourse(WebDriver driver){
        driver.findElement(By.xpath("//div[@id='mat-select-value-1']")).click();
        driver.findElement(SelectSkill).click();
    }


    public static String EnterBio(WebDriver driver, String bio){
        ((JavascriptExecutor)driver).executeScript("arguments[0].value='"+bio+"';",driver.findElement(Bio));
        return bio;
    }

    public static void EnterStartDate(WebDriver driver){
        driver.findElement(StartDate).sendKeys("05122022");
    }

    public static void EnterStartTime(WebDriver driver, String sTime){
        driver.findElement(StartTime).sendKeys(sTime);
    }

    public static void EnterEndTime(WebDriver driver, String eTime){
        driver.findElement(EndTime).sendKeys(eTime);
    }

    public static void EnterDuration(WebDriver driver, String duration){
        driver.findElement(Duration).sendKeys(duration);
    }

    public static void ClickCreateButton(WebDriver driver){
        driver.findElement(CreateButton).click();
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
