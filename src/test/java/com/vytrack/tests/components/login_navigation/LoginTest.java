package com.vytrack.tests.components.login_navigation;

import com.vytrack.pages.LoginPage;
import com.vytrack.pages.SetAndClosePage;
import com.vytrack.utilities.ConfigurationReader;
import org.openqa.selenium.By;
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
        WebElement userName= driver.findElement(By.cssSelector(".dropdown-toggle"));
        String storemanagerName= userName.getText();
        //System.out.println("storemanagerName = " + storemanagerName);
        Assert.assertTrue(userName.isDisplayed());


        //3. Verify Dashboad page is open
        verify_Title_PageName(driver, "Dashboard", "Dashboard");


        //4. Log out
        logout_User(driver);





    }

}
