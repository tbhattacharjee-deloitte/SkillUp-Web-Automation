package Page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MyTrainingsTrainee {
    private static By MyTrainings = By.xpath("//a[normalize-space()='My Trainings']");
    private static By TraineeButton = By.xpath("//*[text()='As a Trainee']");
    private static By SearchBox = By.xpath("//input[@id='mat-input-0']");
    private static By LearnButton = By.xpath("(//button[text()='Learn'])[1]");
    private static By MessageBox = By.xpath("//input[@placeholder='Type message']");
    private static By SendButton = By.xpath("//mat-icon[text()='send']");
    private static By HomePage = By.xpath("//*[text()='Home']");

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
        ((JavascriptExecutor)driver).executeScript("arguments[0].value='"+ Message +"';", driver.findElement(MessageBox));;
    }
    public static void Send(WebDriver driver){
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(SendButton));
    }

    public static void Home(WebDriver driver){
        driver.findElement(HomePage).click();
    }
}
