package pom;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import java.io.*;

public class CusRegExcel {     //DO NOT change the class name
public static XSSFWorkbook workbook;
public static File file;
public static FileInputStream fis;
public static FileOutputStream fos;
public static Workbook wb;
public static Sheet sh;
public static Cell cell;
public static Row row;

    //Wrire the below values into excel sheet. DO NOT change the values
    static String[] fields1 = new String[] { "Tester", "32" , "XYZ", "2323245235", "test1@gmail.com"};
static String[] fields2 = new String[] { "Tester1", "33" , "ABC", "4323245125", "test2@gmail.com"};
static String[] fields3 = new String[] { "Tester2", "34" , "KLM", "1323245235", "test3@gmail.com"};
         
    public static String getExcelPath(String sheetName) {          //DO NOT change the method signature
   //Implement code to locate excelsheet.
   //Return the filepath
   String path=System.getProperty("user.dir")+"/CustReg.xlsx";
   try
   {
   file= new File(path);
fis = new FileInputStream(file);
wb = WorkbookFactory.create(fis);
sh= wb.getSheet(sheetName);
   }
   catch(Exception e)
   {
      System.out.println("file not found");
   }
   finally
   {
return path;
   }
}

    public static void writeExcelData(String sheetName) throws Exception {         //DO NOT change the method signature
   //Implement code to write the given fields1,fields2,fields3 data to excel sheet.
   //Write from row zero(without any headers)
int rowCount= sh.getLastRowNum();
    row =sh.createRow(++rowCount);
    for(int i=0;i<fields1.length;i++)
    {
       cell= row.createCell(i);
       cell.setCellValue(fields1[i]);
    }
    row =sh.createRow(++rowCount);
    for(int i=0;i<fields2.length;i++)
    {
       cell= row.createCell(i);
       cell.setCellValue(fields2[i]);
    }
    row =sh.createRow(++rowCount);
    for(int i=0;i<fields3.length;i++)
    {
       cell= row.createCell(i);
       cell.setCellValue(fields3[i]);
    }
   fos= new FileOutputStream(file);
   wb.write(fos);
   fos.close();
   
            }
   
   
    public static void main(String[] args) throws Exception
{
   CusRegExcel customer = new CusRegExcel();
   //Add required code
   customer.getExcelPath("customervalid");
   customer.writeExcelData("customervalid");
}
}

  
