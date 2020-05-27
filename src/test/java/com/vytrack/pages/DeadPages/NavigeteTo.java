package com.vytrack.pages.DeadPages;

import com.vytrack.utilities.DeadUtilities.ExplicitWaits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class NavigeteTo {
    public static void module_page(WebDriver driver, String moduleName, String pageName){

        WebElement fleetTab = driver.findElement(By.xpath("//span[contains(text(),'"+moduleName+"')]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(fleetTab).perform();

        WebElement vehiclemodule = driver.findElement(By.xpath("//span[contains(text(),'"+pageName+"')]"));
        //Assert.assertTrue(vehicleContracts.isDisplayed(),"Fleet tab dropdown displayed");
        vehiclemodule.click();
        ExplicitWaits.loadingWait();
    }
}
