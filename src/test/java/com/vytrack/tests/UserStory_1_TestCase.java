package com.vytrack.tests;

import com.vytrack.pages.LoginPage;
import com.vytrack.pages.FleetMenuTab;
import com.vytrack.utilities.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.vytrack.utilities.SetAndClosePage;

public class UserStory_1_TestCase extends SetAndClosePage{

   // WebDriver driver;

  /*
    @BeforeMethod
    public void before(){
       // driver= WebDriverFactory.getDriver("chrome");
      //  driver= WebDriverFactory.getDriver(ConfigurationReader.get("browser"));
        driver= Driver.get();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://qa1.vytrack.com/user/login");
    }

    @AfterMethod
    public void after() throws InterruptedException {
        Thread.sleep(2000);
        //driver.quit();
        Driver.closeDriver();
    }

    @DataProvider(name = "UserLoginPreConditions")
    public Object[][] loginData(Method testCase){    // username + password sıralamasını kullan!!


        return new Object[][]{
                    {"user7" , "UserUser123"},
                    {"user8" , "UserUser123"},
                    {"user9" , "UserUser123"}};
    }
    */


    @Test //(dataProvider = "UserLoginPreConditions" ) (String username, String password)
    public void UserStory_1_Test() throws InterruptedException {
    // User Story 1: As a truck driver I should be able to access Vehicle under Fleet module.
    // Acceptance Criteria
//        1.Verify that truck driver should be able to see all Vehicle information once navigate to Vehicle page.
//        2.Verify that when user  click on any car on the grid , system should display general information about the car
//        3.Verify that truck driver can add Event and it should display under Activity tab and General information page as well .
//        4.Verify that Truck driver can reset the Grid by click on Grid setting

        // Driver login home page
        LoginPage.driverLogin(driver,ConfigurationReader.get("driver_username"),ConfigurationReader.get("driver_password"));
        String dashboardTitle= driver.getTitle();

        // waite for loading  circle
        LoadingWait.ExplicitWait(driver);

        // click module and wait for loading  circle and verify title change
        FleetMenuTab.clickModule(driver,"Vehicle");
        LoadingWait.ExplicitWait(driver);
        String moduleTitle = driver.getTitle();

        Assert.assertNotEquals(dashboardTitle,moduleTitle);


        //*****************  Verify AC-1  *****************
        String expectedTitle = "Car - Entities - System - Car - Entities - System";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle,expectedTitle);

     //   String expectedInfo = "LICENSE PLATE\n" + "TAGS\n" + "DRIVER\n" + "LOCATION\n" + "CHASSIS NUMBER\n" + "MODEL YEAR\n" + "LAST ODOMETER\n" + "IMMATRICULATION DATE\n" + "FIRST CONTRACT DATE\n" + "CVVI\n" + "SEATS NUMBER\n" + "DOORS NUMBER\n" + "COLOR\n" + "TRANSMISSION\n" + "FUEL TYPE\n" + "CO2 EMISSIONS\n" + "HORSEPOWER\n" + "HORSEPOWER TAXATION\n" + "POWER (KW)";
     //  String actualInfo =driver.findElement(By.xpath("//thead[@class='grid-header']")).getText();

     //    Assert.assertEquals(actualInfo,expectedInfo);


        //*****************  Verify AC-2  *****************
        /* If u want to usespecific car use like tihs!! But it is not static and strong way!
        driver.findElement(By.xpath("//td[contains(text(),'14EF6562')]")).click();
        LoadingWait.ExplicitWait(driver);

        // different way
        String plate = "14EF6562";
        driver.findElement(By.xpath("//td[contains(text(),'"+plate+"')]")).click();
        LoadingWait.ExplicitWait(driver);
        String actualPlate = driver.findElement(By.xpath("//div[contains(text(),'"+plate+"')]")).getText();
        Assert.assertEquals(actualPlate.substring(0,8),plate);

        expectedTitle = "14EF6562 Tera Hermiston I Nijat did this :)New Aimee 2009 silver compact,sedan - Car - Entities - System - Car - Entities - System";
        actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle,expectedTitle);
         */
        // select and check/test the first car on table via its plate
        WebElement firstRow_Column = driver.findElement(By.xpath("(//tbody//td)[1]"));
        String firstRowPlate = firstRow_Column.getText();
        firstRow_Column.click();
        LoadingWait.ExplicitWait(driver);

        String actualPlate = driver.findElement(By.xpath("(//h5/../div/div)[1]")).getText();  //chil -- parent -- child
        Assert.assertEquals(actualPlate,firstRowPlate);

        // and also Verify General info page
        String pageHead= driver.findElement(By.tagName("h5")).getText();
        Assert.assertEquals(pageHead,"General Information");


        //*****************  Verify AC-3  *****************

        // Click on add event button
        //Thread.sleep(2000);
        //driver.findElement(By.xpath("(//a[@href=\"javascript: void(0);\"])[2]")).click();
        driver.findElement(By.partialLinkText("Add Event")).click(); //ATTENTION, direk link ismi kullanma, boşluk vb var!!
        LoadingWait.ExplicitWait(driver);

        // add event
        String EventTitle = "Automation Event By Tlh";
        driver.findElement(By.name("oro_calendar_event_form[title]")).sendKeys(EventTitle);
        // switch iframe
        driver.switchTo().frame(0);
        driver.findElement(By.id("tinymce")).sendKeys("FleetModule Vehicle Event/UserStory-1");

        // switch back and click submit
        driver.switchTo().defaultContent();
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        // add wait for visibilty of flash message
        WebElement flashMessage= driver.findElement(By.xpath("//div[@class='flash-messages-holder']"));
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOf(flashMessage));
       // System.out.println("flash Message text: " +flashMessage.getText());
        Assert.assertTrue(flashMessage.isDisplayed(), "Add event message is displayed");

        //verify that event can see in activty tab
        driver.navigate().refresh();  // after refrsh page we can see new event!!!!!!
        LoadingWait.ExplicitWait(driver);
        String actualEventTitle= driver.findElement(By.tagName("strong")).getText();
        Assert.assertEquals(actualEventTitle,EventTitle);



        //*****************  Verify AC-4  *****************

        driver.navigate().back();
        LoadingWait.ExplicitWait(driver);
        driver.findElement(By.xpath("(//*[@class='fa-refresh'])[2]")).click();
        LoadingWait.ExplicitWait(driver);
        // bunun testi nasıl olur bi açıklama yok ! istersen settings den id ekle sonra restele eski haline döner


    }


}
