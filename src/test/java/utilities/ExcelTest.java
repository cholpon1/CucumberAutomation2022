package utilities;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelTest {
    public static void main(String[] args) {

        String path= "C:\\Users\\Abdal\\Desktop\\MindtekFiles\\Project Day\\MindtekCucumberAutomation\\src\\test\\resources\\data\\TestData.xlsx";

        try {
            FileInputStream input= new FileInputStream(path);
            Workbook book= new XSSFWorkbook(input); // Workbook is an interface and XSSFWorkbook
            // We cannot create an object out of Interface.
            Sheet page= book.getSheet("Sheet1");
            String name1=page.getRow(1).getCell(0).toString();
            System.out.println(name1);
            String position1=page.getRow(2).getCell(2).toString();
            System.out.println(position1);

            page.createRow(3).createCell(0).setCellValue("Kim");
            page.getRow(3).createCell(1).setCellValue("Yan");

            FileOutputStream output= new FileOutputStream(path);
            book.write(output);

        } catch (IOException e) {
            System.out.println("Test Data excel file is not found or IO Exception is thrown");
        }

        ExcelUtils.readExcel("TestData","Sheet1");
        System.out.println(ExcelUtils.getValue(2,3));

        ExcelUtils.setValue(3,2,"Scrum Master");
        ExcelUtils.setValue(3,3,100000);


    }
}
