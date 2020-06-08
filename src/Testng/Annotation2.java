package Testng;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.TestNG;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.collections.Lists;
import org.testng.annotations.AfterTest;

@Listeners(FeatureTest.class)         //DO NOT remove or alter this line. REQUIRED FOR TESTING
public class Ex2TestNG {             //DO NOT Change the class Name


public static WebDriver driver;
String name;
String password;

@BeforeSuite
    public void createDriver() {                  //DO NOT change the method signature
//Create driver and assign it to 'static' driver variable
driver= DriverSetup.getWebDriver();
}
@BeforeTest
public void resetName() {                   //DO NOT change the method signature
//NO implementation required. ONLY proper annotation is required.
}
    @AfterTest
public void signin() {                       //DO NOT change the method signature
System.out.println("signin");
}
@Test(priority=1)
    public void initializeName() {                //DO NOT change the method signature
name="Tom";
}
@Test(priority=0)
public void initializePassword() {           //DO NOT change the method signature
password="Tom";
}
@AfterSuite
public void closeBrowser() {                //DO NOT change the method signature
//close the driver
driver.close();
}

public void invokeTest() {                 //DO NOT change the method signature
//Implement code to invoke test using TestNG

TestNG testsuite=new TestNG();
testsuite.setTestClasses(new Class[] {
    Ex2TestNG.class
    });


try {
    testsuite.run();
} catch(Exception e) {
   
}
}

public static void main(String[] args) {
   Ex2TestNG ex2=new Ex2TestNG();
   //Implement any required
   ex2.invokeTest();
}

 

}

Testng.xml
<?xml version="1.0" encoding="UTF-8"?>
<suite name="Suite" >
  <test name="Test">
    <classes>
       <!-- Add required entry -->
       <class name="Ex2TestNG">
           
                   
           
       </class>
     </classes>
  </test>
</suite> 





