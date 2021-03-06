import Base.BaseClass;
import ListenersPackage.Listener;
import Page.Categories;
import Page.Login;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Listeners(Listener.class)
public class Categorytest extends BaseClass {
    @BeforeTest
    @Parameters({"username", "password"})
    void login(String username, String password) throws InterruptedException {
        init();
//        Login.login(driver,BaseClass.prop.getProperty("username"), BaseClass.prop.getProperty("password"));
        Login.login(driver, username, password);
    }
    @Test(priority = 1)
    void categoriestest(){
        Categories.category(driver);
        Assert.assertEquals(Categories.actualtext(driver),Categories.expectedtext(driver));
        System.out.println("Assertion Passed");

    }
    @Test(priority = 2)
    void fillvaluetest() throws IOException {
        Categories.category(driver);
        File file = new File(System.getProperty("user.dir")+"/src/test/resources/categoryReqData.xlsx");
        FileInputStream inputStream = new FileInputStream(file);
        XSSFWorkbook wb=new XSSFWorkbook(inputStream);
        XSSFSheet sheet=wb.getSheet("categoryReqData");
        int rows=sheet.getPhysicalNumberOfRows();
        int cols=sheet.getRow(0).getLastCellNum();
        String discription=null;
        String dateValue = null;
        String durationValue=null;
        String starttime=null;
        String endtime=null;
        for(int i=1;i<rows;i++) {
            for (int j = 0; j < cols; j++) {
                if (j == 0) {
                    discription= sheet.getRow(i).getCell(j).getStringCellValue();
                }
                if (j == 1) {
                    dateValue=String.valueOf(sheet.getRow(i).getCell(j).getNumericCellValue());
                }
                if(j==2){
                    durationValue= String.valueOf(sheet.getRow(i).getCell(j).getNumericCellValue());
                }
                if(j==3){
                    starttime=sheet.getRow(i).getCell(j).getStringCellValue();
                }
                if(j==4){
                    endtime=sheet.getRow(i).getCell(j).getStringCellValue();
                }

            }

            Categories.fillvalue(driver,discription,dateValue,durationValue,starttime,endtime);
        }
        wb.close();
        inputStream.close();
    }


    @AfterTest
    void teardown(){
        driver.close();
    }
}
