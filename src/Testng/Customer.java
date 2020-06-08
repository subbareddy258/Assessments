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

@Listeners(FeatureTest.class)         //DO NOT remove or alter this line. REQUIRED FOR TESTING
public class Ex4TestNG               //DO NOT Change the class Name
{
	public static WebDriver driver;
    static XSSFWorkbook wb;
    static XSSFSheet sheet;
    static XSSFRow row;
    static XSSFCell cell;
    static Object[][] str;
    static int numRows;
    static int numCols;

    @BeforeSuite
    public void createDriver() {         //DO NOT change the method signature
		//Create driver and assign it to 'static' driver variable
		DriverSetup d=new DriverSetup();
        driver=d.getWebDriver();
    }
	
	@DataProvider(name="commodityData")
	public Object[][] commodityData() throws Exception  //DO NOT change the method signature
	{
	    //Parse data from CommodityDetails.xlsx and return the 2-dimensional array
	    //Parse data from Shipmentdetails.xlsx and return the 2-dimensional array
	    
	    String path = System.getProperty("user.dir")+"/CommodityDetails.xlsx";
        File filePath = new File(path);
        FileInputStream ExcelFile = new FileInputStream(filePath);
        wb = new XSSFWorkbook(ExcelFile);
        sheet = wb.getSheet("Data");
        numRows = sheet.getLastRowNum() + 1;
        numCols = sheet.getRow(0).getLastCellNum();
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

    @Test(dataProvider ="commodityData")
	void testCommodity (String name,String weight,String length,String width,String height) throws InterruptedException
	{
	    //Pass the form data parsed from excel sheet and submit form
	    //Find the elements of the newly displayed row on the page and compare the values against the excel data as mentioned in the description
	    
		for (int k=1; k<=numRows; k++) 
		{
        String name1 = driver.findElement(By.xpath("//*[@id='name']")).getText();
        String weight1=driver.findElement(By.xpath("//*[@id='weight']")).getText();
        String length1=driver.findElement(By.xpath("//*[@id='length']")).getText();
		String width1=driver.findElement(By.xpath("//*[@id='width']")).getText();
        String height1=driver.findElement(By.xpath("//*[@id='height']")).getText();
        //driver.findElement(By.xpath("//input[@type='submit']")).click();
		try {
            Assert.assertEquals(name, name1);
        } catch (AssertionError a) {
            System.out.println("name doesnt match");
            throw a;
        }
		try {
            Assert.assertEquals(weight, weight1);
        } catch (AssertionError a) {
            System.out.println("weight doesnt match");
            throw a;
        }
        try {
            Assert.assertEquals(length, length1);
        } catch (AssertionError a) {
            System.out.println("length doesnt match");
            throw a;
        }
        try {
            Assert.assertEquals(width, width1);
        } catch (AssertionError a) {
            System.out.println("width doesnt match");
            throw a;
        }
        try {
            Assert.assertEquals(height, height1);
            } catch (AssertionError a) {
            System.out.println("height doesnt match");
            throw a;
        }
		}
    }
	public  void commodity() {
	    //Invoke the test using TestNG ONLY HERE.
	    TestNG t=new TestNG();
        t.setTestClasses(new Class[] {Ex4TestNG.class});
        try{
            t.run();
        }
        catch(Exception e){
        }
	}
	public static void main(String[] args) {
		Ex4TestNG ex4=new Ex4TestNG();
	    //Implement any required code.  
	    ex4.commodity();
	}
}