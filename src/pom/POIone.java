package pom;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
public class POIone {
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static FileInputStream fis;
	    public static Object[][] readExcelData(String sheetName){ //DO NOT change the method signature
	//Implement code to read data from excel sheet in a 2-D array. Return the array

	int rows= sheet.getPhysicalNumberOfRows();
	int columns=sheet.getRow(0).getPhysicalNumberOfCells();
	String[][] excelData = new String[rows][columns];
	   
	   
	    for (int i=0; i<rows; i++) {
	        XSSFRow row = sheet.getRow(i);
	        for (int j=0; j<columns; j++) {
	            XSSFCell cell = row.getCell(j);
	            DataFormatter formatter = new DataFormatter();
	          excelData[i][j] = formatter.formatCellValue(cell);
	           
	        }  
	     
	    }
	return excelData;

	    }

	 

	    public static String getExcelPath(String sheetName) throws Exception
	    {
	   //Implement code to locate the excel file. Return the filepath
	 String path=System.getProperty("user.dir")+"/CustReg.xlsx";
	// String path="CustReg.xlsx";
	fis = new FileInputStream(path);
	workbook = new XSSFWorkbook(fis);
	sheet= workbook.getSheet(sheetName);
	return path;
	   
	   
	    }

	    public static void main(String[] args) throws Exception
	{
	   CusRegExcel customer = new CusRegExcel();
	   //Add required code
	  customer. getExcelPath("customervalid");
	   
	 Object cust[][]=customer.readExcelData("customervalid");
	}

}
