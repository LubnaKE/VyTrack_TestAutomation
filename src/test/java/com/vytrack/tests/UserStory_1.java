package com.vytrack.tests;

import com.vytrack.utilities.DriverLogin;
import com.vytrack.utilities.FleetMenuTab;
import com.vytrack.utilities.LoadingWait;
import com.vytrack.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class UserStory_1 {

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

    @DataProvider(name = "UserLoginPreConditions")
    public Object[][] loginData(Method testCase){    // username + password sıralamasını kullan!!


        return new Object[][]{
                    {"user7" , "UserUser123"}/*,
                    {"user8" , "UserUser123"},
                    {"user9" , "UserUser123"}*/ };
    }



    @Test (dataProvider = "UserLoginPreConditions" )
    public void UserStory_1_Test(String username, String password) throws InterruptedException {
    // User Story 1: As a truck driver I should be able to access Vehicle under Fleet module.
    // Acceptance Criteria
//        1.Verify that truck driver should be able to see all Vehicle information once navigate to Vehicle page.
//        2.Verify that when user  click on any car on the grid , system should display general information about the car
//        3.Verify that truck driver can add Event and it should display under Activity tab and General information page as well .
//        4.Verify that Truck driver can reset the Grid by click on Grid setting

        // Driver login home page
        DriverLogin.driverLogin(driver,username,password);
        String dashboardTitle= driver.getTitle();

        // waite for loading  circle
        LoadingWait.ExplicitWait(driver);

        // click module and wait for loading  circle and verify title change
        FleetMenuTab.clickModule(driver,"Vehicle");
        LoadingWait.ExplicitWait(driver);
        String moduleTitle = driver.getTitle();

        Assert.assertNotEquals(dashboardTitle,moduleTitle);

        //Verify AC-1
        String expectedTitle = "Car - Entities - System - Car - Entities - System";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle,expectedTitle);

        String expectedInfo = "LICENSE PLATE\n" + "TAGS\n" + "DRIVER\n" + "LOCATION\n" + "CHASSIS NUMBER\n" + "MODEL YEAR\n" + "LAST ODOMETER\n" + "IMMATRICULATION DATE\n" + "FIRST CONTRACT DATE\n" + "CVVI\n" + "SEATS NUMBER\n" + "DOORS NUMBER\n" + "COLOR\n" + "TRANSMISSION\n" + "FUEL TYPE\n" + "CO2 EMISSIONS\n" + "HORSEPOWER\n" + "HORSEPOWER TAXATION\n" + "POWER (KW)";
        String actualInfo =driver.findElement(By.xpath("//thead[@class='grid-header']")).getText();

        Assert.assertEquals(actualInfo,expectedInfo);

        //Verify AC-2
        /*
        driver.findElement(By.xpath("//td[contains(text(),'14EF6562')]")).click();
        LoadingWait.ExplicitWait(driver);
         */
        // different way
        String plate = "14EF6562";
        driver.findElement(By.xpath("//td[contains(text(),'"+plate+"')]")).click();
        LoadingWait.ExplicitWait(driver);
        String actualPlate = driver.findElement(By.xpath("//div[contains(text(),'"+plate+"')]")).getText();
        Assert.assertEquals(actualPlate.substring(0,8),plate);

        expectedTitle = "14EF6562 Tera Hermiston I Nijat did this :)New Aimee 2009 silver compact,sedan - Car - Entities - System - Car - Entities - System";
        actualTitle = driver.getTitle();
       // Assert.assertEquals(actualTitle,expectedTitle);

        String pageHead= driver.findElement(By.tagName("h5")).getText();

        Assert.assertEquals(pageHead,"General Information");

        driver.findElement(By.cssSelector(".pull-left.pull-left-extra")


        //Verify AC-3
        Thread.sleep(2000);
        driver.findElement(By.xpath("(//a[@href=\"javascript: void(0);\"])[2]")).click();




    }


}
