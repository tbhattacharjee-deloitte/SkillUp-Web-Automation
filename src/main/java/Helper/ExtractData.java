package Helper;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

public class ExtractData {
    public static User getSingleUser() {
        User user = null;
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\resources\\Data.xlsx"));
            XSSFSheet sheet = workbook.getSheetAt(0);
            int rowCnt = sheet.getLastRowNum();
            int colCnt = sheet.getRow(0).getLastCellNum();
            XSSFRow row = sheet.getRow(1);
            user = new User(row.getCell(0).toString(), row.getCell(1).toString(),
                            row.getCell(2).toString(), row.getCell(3).toString(),
                            row.getCell(4).toString(), (int) row.getCell(5).getNumericCellValue(),
                    (int) row.getCell(6).getNumericCellValue(), row.getCell(7).toString(),
                    row.getCell(8).toString(), row.getCell(9).toString().split(","));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

//    public static void main(String[] args) {
//        getSingleUser();
//    }
}
