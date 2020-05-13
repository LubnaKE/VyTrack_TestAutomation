package com.vytrack.tests.components.fleet;

import com.vytrack.pages.LoginPage;
import com.vytrack.pages.SetAndClosePage;
import com.vytrack.utilities.ConfigurationReader;
import com.vytrack.utilities.SingleMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.vytrack.utilities.SingleMethods.*;

public class PageAccessTest extends SetAndClosePage {

    @Test (description = "Vehicle contracts test / store manager")
    public void test1(){

        //TEST CASE
        //1. Login to Vytrack as a store manager
        LoginPage.userLogin(driver, ConfigurationReader.get("storemanager_username"),ConfigurationReader.get("storemanager_password"));


        //2. Verify that you can access Vehicle contracts page
        navigateTo_Module_Page(driver,"Fleet", "Vehicle Contracts");
        verify_Title_PageName(driver,"All - Vehicle Contract - Entities - System - Car - Entities - System", "All Vehicle Contract");

    }



    @Test (description = "Vehicle contracts test / sales manager")
    public void test2(){

        //TEST CASE
        //1. Login to Vytrack as a sales manager
        LoginPage.userLogin(driver, ConfigurationReader.get("salesmanager_username"),ConfigurationReader.get("salesmanager_password"));


        //2. Verify that you can access Vehicle contracts page
        navigateTo_Module_Page(driver,"Fleet", "Vehicle Contracts");
        verify_Title_PageName(driver,"All - Vehicle Contract - Entities - System - Car - Entities - System", "All Vehicle Contract");

    }



    @Test (description = "Vehicle contracts test / Driver")
    public void test3(){

        //TEST CASE
        //1. Login to Vytrack as a driver
        LoginPage.userLogin(driver, ConfigurationReader.get("driver_username"),ConfigurationReader.get("driver_password"));


        //2. Verify that you cannot access Vehicle contracts page
        navigateTo_Module_Page(driver,"Fleet", "Vehicle Contracts");
        verify_Title_PageName(driver,"Dashboard", "Quick Launchpad");


        //3. Message You do not have permission to perform this action. should be displayed
        String expectedMessage = "You do not have permission to perform this action.";
        //WebElement actualMessage= driver.findElement(By.xpath("//div[contains(text(),'You do not have permission to perform this action.')]"));
        WebElement actualMessage= driver.findElement(By.xpath("(//*[@class='message'])[2]"));
        Assert.assertEquals(actualMessage.getText(),expectedMessage);
        Assert.assertTrue(actualMessage.isDisplayed(), "Flash error message is displayed");
    }
}
