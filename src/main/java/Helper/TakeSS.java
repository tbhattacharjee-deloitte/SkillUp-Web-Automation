package Helper;

import Base.BaseClass;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;

public class TakeSS {
    public static void takeSS(String fileName) {
        File scrFile = ((TakesScreenshot)BaseClass.driver).getScreenshotAs(OutputType.FILE);
        try {
            FileHandler.copy(scrFile, new File(System.getProperty("user.dir") + "\\src\\main\\ScreenShot\\" + fileName));
            BaseClass.logger.info("Screen shot successfully taken");
        } catch (IOException e) {
            BaseClass.logger.fatal(e.getMessage());
        }
    }
}
