package pom;

import java.util.regex.Pattern;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class SeleniumTestForm {          //DO NOT change the class name

    //Use this declarations to store respective values
    public static WebDriver driver;
    public static String result;
    public static String msg;
   
   
    public void createDriver() {
        //Implement code to create driver and assign it to 'static' driver variable
        driver= DriverSetup.getWebDriver();
       
    }

    public void testSeleniumTestForm(String weight,String transportMode)  throws Exception{
    //'weight' and 'transportMode' parameter are used to send the values to the form
        //find the elements in the form and set values as per description. Submit the form.
        //Find the element of the message displayed after submit. Store it in 'msg' variable.
        //Store the 'pass' or 'fail' in the 'result' variable
        driver.findElement(By.id("weight")).sendKeys(weight);
        driver.findElement(By.id(transportMode)).click();
         driver.findElement(By.id("calculate")).click();
        msg= driver.findElement(By.id("result")).getText();
        System.out.println(msg);
        if(msg.equals("Dear Customer, your total shipping cost is $200"))
   
        result ="pass";
   
    else
   
        result="fail";
   
        }
             
         
    public void closeBrowser(){
        //close the browser
        driver.close();
    }

    public static void main(String[] args) throws Exception
{

SeleniumTestForm st=new SeleniumTestForm();
//Add code here
st.createDriver();
st.testSeleniumTestForm("200","Air");
st.closeBrowser();


String path = System.getProperty("user.dir")+"/cargo.xlsx";
 System.out.println(result);
 CargoShipping.writeExcelData(path,result);




}
}


/// CargoShipping:
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

public class CargoShipping   //DO NOT change the class name
{
public static XSSFSheet sheet;
public static File file;
public static FileInputStream fis;
public static FileOutputStream fos;

public static XSSFWorkbook wb;
public static Row row;
public static Cell cell;
   
    public static void writeExcelData(String fileName,String result) throws Exception { //Do not change the method signature
        //Write the Test result to the excel sheet
    file= new File(fileName);
   
    fis= new FileInputStream(file);
    wb= new XSSFWorkbook(fis);
    sheet= wb.getSheet("TestCase");
   
    row= sheet.createRow(0);
    cell= row.createCell(0);
    cell.setCellValue(result);
    fos= new FileOutputStream(file);
    wb.write(fos);
    fos.close(); 
    }
   
}
