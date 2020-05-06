package com.vytrack.tests;

import com.vytrack.utilities.WebDriverFactory;
import oracle.jrockit.jfr.ActiveSettingEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class userStroy7_TestCases {

    // VYTruck Project User Story-7 (EU3-73, EU3-74) Test Cases automation Tests:

    WebDriver driver;

    @BeforeMethod
    public void before(){
        driver= WebDriverFactory.getDriver("chrome");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://qa1.vytrack.com/user/login");
    }

    @AfterMethod
    public void after() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }

    @DataProvider (name = "UserLoginPreConditions")
    public Object[][] loginData(Method testCase){    // username + password sıralamasını kullan!!
        if(testCase.getName().equals("TestCase1_withAuthorizedUser")){
            return new Object[][]{
                    {"storemanager55","UserUser123"},
                    {"storemanager56","UserUser123"},
                    {"salesmanager107","UserUser123"},
                    {"salesmanager108","UserUser123"},
                    {"salesmanager109","UserUser123"} };
        }
        if (testCase.getName().equals("TestCase2_withUnAuthorizedUser")){
            return new Object[][]{
                    {"user7" , "UserUser123"},
                    {"user8" , "UserUser123"},
                    {"user9" , "UserUser123"} };
        }
       return null;
    }


    @Test (dataProvider = "UserLoginPreConditions")
    public void TestCase1_withAuthorizedUser(String username , String password){
        // Login Web PAge
        driver.findElement(By.id("prependedInput")).sendKeys(username);
        driver.findElement(By.id("prependedInput2")).sendKeys(password);
        driver.findElement(By.id("_submit")).click();
        loadingExplicitWait();

        //Move to mouse to Fleet Tab
        WebElement fleetTab = driver.findElement(By.xpath("//span[contains(text(),'Fleet')]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(fleetTab).perform();

        // Verify vehicleContracts is displayed from user
        WebElement vehicleContracts = driver.findElement(By.xpath("//span[contains(text(),'Vehicle Contracts')]"));
        Assert.assertTrue(vehicleContracts.isDisplayed(),"Fleet tab dropdown displayed");

        // Click vehicleContracts and Verify page can accesseable from authorized user
        vehicleContracts.click();
        loadingExplicitWait();
        String expectedTitle = "All - Vehicle Contract - Entities - System - Car - Entities - System";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle,expectedTitle);

    }

    @Test (dataProvider = "UserLoginPreConditions")
    public void TestCase2_withUnAuthorizedUser(String username , String password){
        // Login Web PAge
        driver.findElement(By.id("prependedInput")).sendKeys(username);
        driver.findElement(By.id("prependedInput2")).sendKeys(password);
        driver.findElement(By.id("_submit")).click();
        loadingExplicitWait();

        //Move to mouse to Fleet Tab
        WebElement fleetTab = driver.findElement(By.xpath("//span[contains(text(),'Fleet')]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(fleetTab).perform();

        // Verify vehicleContracts is displayed from user
        WebElement vehicleContracts = driver.findElement(By.xpath("//span[contains(text(),'Vehicle Contracts')]"));
        Assert.assertTrue(vehicleContracts.isDisplayed(),"Fleet tab dropdown displayed");

        // Click vehicleContracts and Verify page can NOT accesseable from UNauthorized user
        vehicleContracts.click();
        loadingExplicitWait();
        String expectedTitle = "Dashboard";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle,expectedTitle);

        // Verify Error message is displayed and equal to expected text
        String expectedMEssage= "You do not have permission to perform this action.";
        WebElement message = driver.findElement(By.xpath("//div[contains(text(),'You do not have permission')]"));
        Assert.assertTrue(message.isDisplayed());
        Assert.assertEquals(message.getText(),expectedMEssage);

    }

    public void loadingExplicitWait(){
        // this method waits after every click for loading bar!!
        WebElement loading = driver.findElement(By.cssSelector(".loader-mask  .loader-frame"));
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.invisibilityOfAllElements(loading));
    }

}
