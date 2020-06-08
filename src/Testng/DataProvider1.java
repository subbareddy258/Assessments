package Testng;

import static org.junit.Assert.assertEquals;
import java.io.File;
import java.io.FileInputStream;
import java.util.*;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.TestNG;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.collections.Lists;
import junit.framework.Assert;

@Listeners(FeatureTest.class)       //DO NOT remove or alter this line. REQUIRED FOR TESTING
public class Ex3TestNG               //DO NOT Change the class Name
{
public static WebDriver driver;
static XSSFWorkbook wb;
static XSSFSheet sheet;
static XSSFRow row;
static XSSFCell cell;
static Object[][] str;
@BeforeSuite
public void createDriver() {           //DO NOT change the method signature
//Create driver and assign it to 'static' driver variable
DriverSetup d=new DriverSetup();
driver=d.getWebDriver();
    }
   
@DataProvider(name="shipmentData")
public Object[][] shipmentData() throws Exception        //DO NOT change the method signature
{
   //Parse data from Shipmentdetails.xlsx and return the 2-dimensional array
   String path = System.getProperty("user.dir")+"/ShipmentDetails.xlsx";
File filePath = new File(path);
FileInputStream ExcelFile = new FileInputStream(filePath);
wb = new XSSFWorkbook(ExcelFile);
sheet = wb.getSheet("Data");
int numRows = sheet.getLastRowNum() + 1;
int numCols = sheet.getRow(0).getLastCellNum();
str = new Object[numRows][numCols];
for (int i = 1; i < numRows; i++) {
row = sheet.getRow(i);
for (int j = 0; j < numCols; j++) {
cell = row.getCell(j);
DataFormatter fmt = new DataFormatter();
String value = fmt.formatCellValue(cell);
str[i-1][j] = value;
System.out.println(str[i][j]);
}
}
return str;
}
   


//DO NOT change the method signature
@Test(dataProvider ="shipmentData")
void testShipment (String orginPort,String destinationPort,String railModeCharge,String roadModeCharge,String airModeCharge) throws InterruptedException
{
//Select the souce/destination as specified in description.
// Test the tabled data against the excel data as specified in description.
Select s=new Select(driver.findElement(By.xpath("//select[@name='origin_id']")));
s.selectByVisibleText("Mumbai");
Select e=new Select(driver.findElement(By.xpath("//select[@name='destination_id']")));
e.selectByVisibleText("New York");
System.out.println(orginPort);
driver.findElement(By.xpath("//input[@type='submit']")).click();
String rail=driver.findElement(By.xpath("(//table//tbody/tr/td[2])[1]")).getText();
String road=driver.findElement(By.xpath("(//table//tbody/tr/td[2])[2]")).getText();
String air=driver.findElement(By.xpath("(//table//tbody/tr/td[2])[3]")).getText();

try {
    Assert.assertEquals(railModeCharge, rail);
} catch (AssertionError a) {
    System.out.println("The Rail mode Charge doesnt match");
    throw a;
}
try {
    Assert.assertEquals(roadModeCharge, road);
} catch (AssertionError a) {
    System.out.println("The Road mode Charge doesnt match");
    throw a;
}
try {
    Assert.assertEquals(airModeCharge, air);
} catch (AssertionError a) {
    System.out.println("The Air mode Charge doesnt match");
    throw a;
}
}

public  void shipment() {
   //Invoke the test using TestNG ONLY HERE.
   TestNG t=new TestNG();
   t.setTestClasses(new Class[] {Ex3TestNG.class});
   try{
   t.run();
   }
   catch(Exception e){
       
   }

}

public static void main(String[] args) {
Ex3TestNG ex3=new Ex3TestNG();
   //Implement any required code.
   ex3.shipment();
}
}




