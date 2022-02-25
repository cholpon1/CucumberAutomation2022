package utilities;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtils {

    static Sheet sheet;
    static String path;
    static Workbook book;
    static FileOutputStream output;

    /*
    .readExcel(name, sheet);
    .getValue(row,cell);
    .setValue(row,cell);
     */

    public static void  readExcel(String name, String sheetName){
         path="C:\\Users\\Abdal\\Desktop\\MindtekFiles\\Project Day\\MindtekCucumberAutomation\\src\\test\\resources\\data\\"+name+".xlsx";
        try {
            FileInputStream input=new FileInputStream(path);
             book=new XSSFWorkbook(input);
            sheet= book.getSheet(sheetName);
        } catch (IOException e) {
            System.out.println("Excel file is not found or IOException is thrown");
        }
    }

    public static Object getValue(int row, int cell){
        return sheet.getRow(row).getCell(cell);
    }


    public static void setValue(int row, int cell, String value){
        int numberOfRows= sheet.getPhysicalNumberOfRows();

        if(numberOfRows>row){
            sheet.getRow(row).createCell(cell).setCellValue(value);
        }else{
            sheet.createRow(row).createCell(cell).setCellValue(value);
        }

        try {
             output= new FileOutputStream(path);
            book.write(output);
        } catch (IOException e) {
            System.out.println("Excel file is not found or path to file is wrong (IO Exception is thrown)");
        }finally {
            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }




    public static void setValue(int row, int cell, double value){
        int numberOfRows= sheet.getPhysicalNumberOfRows();

        if(numberOfRows>row){
            sheet.getRow(row).createCell(cell).setCellValue(value);
        }else{
            sheet.createRow(row).createCell(cell).setCellValue(value);
        }


        try {
            output= new FileOutputStream(path);
            book.write(output);
        } catch (IOException e) {
            System.out.println("Excel file is not found or path to file is wrong (IO Exception is thrown)");
        }finally {
            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


    public static List<Map<String, Object>> getExcelData(){
        List<Map<String, Object>> data= new ArrayList<>();
        for(int row=1; row<sheet.getPhysicalNumberOfRows(); row++){
            Map<String, Object> map= new HashMap<>();
            for(int cell=1; cell<sheet.getRow(row).getPhysicalNumberOfCells(); cell++){
                map.put(sheet.getRow(0).getCell(cell).toString(), sheet.getRow(row).getCell(cell));

            }

            data.add(map);

        }
        return data;
    }


}
