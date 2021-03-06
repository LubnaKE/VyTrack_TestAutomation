package com.vytrack.tests.tasks;

import com.vytrack.pages.DeadPages.LoginPage_Tlh;
import com.vytrack.pages.DeadPages.SetAndClosePage;
import com.vytrack.utilities.*;
import com.vytrack.utilities.DeadUtilities.ExplicitWaits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserStroy_7_TestCase extends SetAndClosePage {

    // VYTruck Project User Story-7 (EU3-73, EU3-74) Test Cases automation Tests:
/*
    WebDriver driver;

    @BeforeMethod
    public void before(){
        // driver= WebDriverFactory.getDriver("chrome");
        //driver= WebDriverFactory.getDriver(ConfigurationReader.get("browser"));
        driver=null;
        driver= Driver.get();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://qa1.vytrack.com/user/login");
    }

    @AfterMethod
    public void after() throws InterruptedException {
       // Thread.sleep(2000);
      //  driver.quit();  // manul quit yaparsak 2. methoda gecmez Dİkkat!!!
        Driver.closeDriver();

    }
*/
//
//    @DataProvider (name = "UserLoginPreConditions")
//    public Object[][] loginData(Method testCase){    // username + password sıralamasını kullan!!
//        if(testCase.getName().equals("TestCase1_withAuthorizedUser")){
//            /*return new Object[][]{
//                    {"storemanager55","UserUser123"},
//                    {"storemanager56","UserUser123"},
//                    {"salesmanager107","UserUser123"},
//                    {"salesmanager108","UserUser123"},
//                    {"salesmanager109","UserUser123"} };*/
//            return new Object[][]{
//                    {ConfigurationReader.get("storemanager_username")},  // salesmanager icin ayrıca tanımla!!
//                    {ConfigurationReader.get("storemanager_password")}
//            };
//        }
//        if (testCase.getName().equals("TestCase2_withUnAuthorizedUser")){
//           /* return new Object[][]{
//                    {"user7" , "UserUser123"},
//                    {"user8" , "UserUser123"},
//                    {"user9" , "UserUser123"} };*/
//            return new Object[][]{
//                    {ConfigurationReader.get("driver_username")},
//                    {ConfigurationReader.get("driver_password")}
//            };
//        }
//       return null;
//    }


    @Test // (dataProvider = "UserLoginPreConditions") (String username , String password)
    public void TestCase1_withAuthorizedUser() throws InterruptedException {
        // Login Web PAge
       /* driver.findElement(By.id("prependedInput")).sendKeys(ConfigurationReader.get("storemanager_username"));
        driver.findElement(By.id("prependedInput2")).sendKeys(ConfigurationReader.get("storemanager_password"));
        driver.findElement(By.id("_submit")).click();*/
        LoginPage_Tlh.userLogin(ConfigurationReader.get("storemanager_username"),ConfigurationReader.get("storemanager_password"));
        //LoadingWait.ExplicitWait(driver);
        ExplicitWaits.loadingWait();

        //Move to mouse to Fleet Tab
        WebElement fleetTab = driver.findElement(By.xpath("//span[contains(text(),'Fleet')]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(fleetTab).perform();

        // Verify vehicleContracts is displayed from user
        WebElement vehicleContracts = driver.findElement(By.xpath("//span[contains(text(),'Vehicle Contracts')]"));
        //Assert.assertTrue(vehicleContracts.isDisplayed(),"Fleet tab dropdown displayed");

        // Click vehicleContracts
        vehicleContracts.click();
        //LoadingWait.ExplicitWait(driver);
        ExplicitWaits.loadingWait();
        String expectedTitle = "All - Vehicle Contract - Entities - System - Car - Entities - System";

        // wait for title update!!
       /* WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.titleIs("All - Vehicle Contract - Entities - System - Car - Entities - System"));*/
       //TitleWait.ExplicitWait(driver,"All - Vehicle Contract - Entities - System - Car - Entities - System");
        ExplicitWaits.titleWait("All - Vehicle Contract - Entities - System - Car - Entities - System");
        //Verify page can accesseable from authorized user
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle,expectedTitle);

    }

    @Test //(dataProvider = "UserLoginPreConditions")   (String username , String password)
    public void TestCase2_withUnAuthorizedUser(){
        // Login Web PAge
        /*driver.findElement(By.id("prependedInput")).sendKeys(ConfigurationReader.get("driver_username"));
        driver.findElement(By.id("prependedInput2")).sendKeys(ConfigurationReader.get("driver_password"));
        driver.findElement(By.id("_submit")).click();*/
        LoginPage_Tlh.userLogin(ConfigurationReader.get("driver_username"),ConfigurationReader.get("driver_password"));
        //LoadingWait.ExplicitWait(driver);
        ExplicitWaits.loadingWait();

        //Move to mouse to Fleet Tab
        WebElement fleetTab = driver.findElement(By.xpath("//span[contains(text(),'Fleet')]"));
        //Actions actions = new Actions(driver);
        actions.moveToElement(fleetTab).perform();

        // Verify vehicleContracts is displayed from user
        WebElement vehicleContracts = driver.findElement(By.xpath("//span[contains(text(),'Vehicle Contracts')]"));
        Assert.assertTrue(vehicleContracts.isDisplayed(),"Fleet tab dropdown displayed");
        String exptitle= driver.getTitle();

        // Click vehicleContracts and Verify page can NOT accesseable from UNauthorized user
        vehicleContracts.click();
        //LoadingWait.ExplicitWait(driver);
        ExplicitWaits.loadingWait();
        String expectedTitle = exptitle;
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle,expectedTitle);

        // Verify Error message is displayed and equal to expected text
        String expectedMEssage= "You do not have permission to perform this action.";
        WebElement message = driver.findElement(By.xpath("//div[contains(text(),'You do not have permission')]"));
        Assert.assertTrue(message.isDisplayed());
        Assert.assertEquals(message.getText(),expectedMEssage);

    }

/*  // artık buna gerek yok,beklemeyi utilities e koyduk!!
    public void loadingExplicitWait(WebDriver driver){
        // this method waits after every click for loading bar!!
        WebElement loading = driver.findElement(By.cssSelector(".loader-mask  .loader-frame"));
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.invisibilityOfAllElements(loading));

    }
*/
}
