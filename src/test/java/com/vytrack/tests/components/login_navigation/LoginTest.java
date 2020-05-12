package com.vytrack.tests.components.login_navigation;

import com.vytrack.pages.LoginPage;
import com.vytrack.pages.SetAndClosePage;
import com.vytrack.utilities.ConfigurationReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.vytrack.utilities.SingleMethods.*;


public class LoginTest extends SetAndClosePage {

    @Test (description = "Login test (positive)")
    public void test1_LoginPositive(){

        //****TEST CASES****
        // 1. Login to Vytrack as a store manager
        LoginPage.userLogin(driver, ConfigurationReader.get("storemanager_username"), ConfigurationReader.get("storemanager_password"));


        //2. Verify name of the store manager is displayed on top right
        //WebElement userName= driver.findElement(By.cssSelector(".dropdown-toggle"));  bunu utilities e taşıdık!! log out olunca bu elemente ulaşım tekrar olmuyor hata veriyor !!!
        String storemanagerName= userName(driver).getText();
        //System.out.println("storemanagerName = " + storemanagerName);
        Assert.assertTrue( userName(driver).isDisplayed());


        //3. Verify Dashboad page is open
        verify_Title_PageName(driver, "Dashboard", "Dashboard");


        //4. Log out
        logout_User(driver);


        //5. Login to Vytrack as a sales manager
        LoginPage.userLogin(driver, ConfigurationReader.get("salesmanager_username"), ConfigurationReader.get("salesmanager_password"));


       //6. Verify Dashboad page is open
        verify_Title_PageName(driver, "Dashboard", "Dashboard");


        //7. A different name should be displayed on top right
        String salesmanagerName = userName(driver).getText();
        Assert.assertNotEquals(storemanagerName,salesmanagerName);


        //8. Log out
        logout_User(driver);


        //9. Login to Vytrack as a driver
        LoginPage.userLogin(driver, ConfigurationReader.get("driver_username"), ConfigurationReader.get("driver_password"));


        //10. Verify Dashboad page is open
        verify_Title_PageName(driver, "Dashboard", "Quick Launchpad");


        //11. A different name should be displayed on top right
        String driverName = userName(driver).getText();
        Assert.assertNotEquals(driverName,salesmanagerName);

    }

    @Test (description = "Login test (negative)")
    public void test2_LoginNegative() {

        //****TEST CASES****
        // 1.Open Vytrack login page - 2.Enter valid username and invalid password information  - 3. Click login
        LoginPage.userLogin(driver, ConfigurationReader.get("storemanager_username"),"UserUser1234");

        //4. Message Invalid user name or password. should be displayed
        String expectedMessage = "Invalid user name or password.";
        WebElement invalidUserMessage = driver.findElement(By.xpath("//fieldset/div"));

        Assert.assertEquals(invalidUserMessage.getText(),expectedMessage);
    }



}
