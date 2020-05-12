package com.vytrack.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class SingleMethods {

    public static void navigateTo_Module_Page(WebDriver driver, String moduleName, String pageName){

        WebElement module = driver.findElement(By.xpath("//span[contains(text(),'"+moduleName+"')]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(module).perform();

      //  WebElement page = driver.findElement(By.xpath("//span[contains(text(),'"+pageName+"')]"));
        WebElement page = driver.findElement(By.xpath("//span[.='"+pageName+"']"));
        page.click();
        ExplicitWaits.loadingWait(driver);
    }

    public static void verify_Title_PageName(WebDriver driver, String expectedTitle, String expectedPageName ) {

        // to wait update title we need extra explicit wait!!
        ExplicitWaits.titleWait(driver,expectedTitle);
        // StaticWaits.waitLine(2);
        String actualtitle= driver.getTitle();
        String actualPageName= driver.findElement(By.xpath("(//h1)[2]")).getText();

        Assert.assertEquals(actualtitle,expectedTitle);
        Assert.assertEquals(actualPageName,expectedPageName);

    }


}
