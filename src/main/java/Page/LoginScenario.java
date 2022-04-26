package Page;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginScenario {

    //alert function in login scenario
    public static void accept_alert(WebDriver driver)
    {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alt = driver.switchTo().alert();
        alt.accept();
        driver.navigate().refresh();
    }

}
