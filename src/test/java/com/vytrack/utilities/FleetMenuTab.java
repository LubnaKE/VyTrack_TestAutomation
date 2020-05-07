package com.vytrack.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class FleetMenuTab {

    public static void clickModule(WebDriver driver, String moduleName){

        WebElement fleetTab = driver.findElement(By.xpath("//span[contains(text(),'Fleet')]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(fleetTab).perform();

        WebElement vehiclemodule = driver.findElement(By.xpath("//span[contains(text(),'"+moduleName+"')]"));
       // Assert.assertTrue(vehicleContracts.isDisplayed(),"Fleet tab dropdown displayed");
        vehiclemodule.click();
    }
}
