package pom;;

//POM 1:
////CUSREGExcel:
	
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import java.io.IOException;
import java.util.Iterator;
import java.util.Arrays;
public class CusRegExcel {         //Do not change the class name
public static XSSFWorkbook workbook;
public static XSSFSheet sheet;
public static FileInputStream fis;
//Use this declaration to store values parsed from excel
    public static String[] customerData=new String[5];
   
    public static String[] readExcelData(String fileName) throws IOException{   //Do not change the method signature
   
        //Implement code to read data from excel file. Store the values in 'customerData' array. Return the array. */
       fis = new FileInputStream(fileName);
workbook = new XSSFWorkbook(fis);
sheet= workbook.getSheet("customervalid");

XSSFRow rows= sheet.getRow(0);
int columnsCount=sheet.getRow(0).getPhysicalNumberOfCells();
   
   
   
        for (int j=0; j<columnsCount; j++) {
            XSSFCell cell = rows.getCell(j);
            DataFormatter formatter = new DataFormatter();
          customerData[j] = formatter.formatCellValue(cell);
           
        }  
        return customerData;


}

}


//Selenium TestForm

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import java.io.IOException;



public class SeleniumTestForm {      //Do not change the class name

    public static WebDriver driver;
   
    public void createDriver() {                //Do not change the method signature
        //Implement code to create driver and assign it to 'static' driver variable
       driver= DriverSetup.getWebDriver();
    }

    public void testSeleniumTestForm() throws Exception {    //Do not change the method signature
    //Read the data from excel sheet. Sheet name is 'customervalid'
       //find the elements in the form and set values parsed from excel sheet. Submit the form for registration
           String path = System.getProperty("user.dir")+"/CustReg.xlsx";
        String[] customer = CusRegExcel.readExcelData(path);
       driver.findElement(By.xpath("//input[@name='cname']")).sendKeys(customer[0]);
        driver.findElement(By.xpath("//input[@name='age']")).sendKeys(customer[1]);
         driver.findElement(By.xpath("//input[@name='address']")).sendKeys(customer[2]);
          driver.findElement(By.xpath("//input[@name='phonenumber']")).sendKeys(customer[3]);
           driver.findElement(By.xpath("//input[@name='email']")).sendKeys(customer[4]);
            driver.findElement(By.id("submit")).click();
       
    }
    public void closeBrowser(){
        //close the browser
        driver.close();
    }

    public static void main(String[] args) throws Exception
{
   CusRegExcel customer = new CusRegExcel();
   //Add required code
   SeleniumTestForm stf= new SeleniumTestForm();
   stf.createDriver();
   stf.testSeleniumTestForm();
   stf.closeBrowser();
}
}

