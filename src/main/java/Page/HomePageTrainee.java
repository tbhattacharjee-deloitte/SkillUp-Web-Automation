package Page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class HomePageTrainee {
    private static By TraineeButton = By.xpath("//*[text()='BECOME A TRAINEE']");
    private static By SelectSkill = By.xpath("//mat-option[@id='mat-option-1']");
    private static By Bio = By.xpath("//textarea");
    private static By StartDate = By.xpath("(//input)[1]");
    private static By StartTime = By.xpath("(//input)[2]");
    private static By EndTime = By.xpath("(//input)[3]");
    private static By Duration = By.xpath("(//input)[4]");
    private static By CreateButton = By.xpath("//*[text()='CREATE']");
    private static By SearchBox = By.xpath("(//input)[1]");
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


    public static void EnterBio(WebDriver driver, String bio){
        ((JavascriptExecutor)driver).executeScript("arguments[0].value='"+bio+"';",driver.findElement(Bio));
    }

    public static void EnterStartDate(WebDriver driver, String date){
        driver.findElement(StartDate).sendKeys(date);
//        ((JavascriptExecutor)driver).executeScript("arguments[0].value = '05/15/2022';", driver.findElement(StartDate));
    }

    public static void EnterStartTime(WebDriver driver, String sTime){
        driver.findElement(StartTime).sendKeys(sTime);
//        ((JavascriptExecutor)driver).executeScript("arguments[0].value = '"+sTime+"';", driver.findElement(StartTime));
    }

    public static void EnterEndTime(WebDriver driver, String eTime){
        driver.findElement(EndTime).sendKeys(eTime);
//        ((JavascriptExecutor)driver).executeScript("arguments[0].value = '"+eTime+"';", driver.findElement(EndTime));
    }

    public static void EnterDuration(WebDriver driver, String duration){
        ((JavascriptExecutor)driver).executeScript("arguments[0].value = '"+duration+"';", driver.findElement(Duration));
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
