import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProMangEmployee {
    WebDriver driver;

    public void mangerEmployeeTab()
    {
        driver.findElement(By.linkText("Employees")).click();

    }

}
