package com.vytrack.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class SingleMethods {

    public static void navigateTo_Module_Page( String moduleName, String pageName){

        WebDriver driver = Driver.get();
        WebElement module = driver.findElement(By.xpath("//span[contains(text(),'"+moduleName+"')]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(module).perform();

      //  WebElement page = driver.findElement(By.xpath("//span[contains(text(),'"+pageName+"')]"));
        WebElement page = driver.findElement(By.xpath("//span[.='"+pageName+"']"));
        page.click();
        ExplicitWaits.loadingWait(driver);
    }

    public static void verify_Title_PageName( String expectedTitle, String expectedPageName ) {

        WebDriver driver = Driver.get();
        // to wait update title we need extra explicit wait!!
        ExplicitWaits.titleWait(expectedTitle);
        // StaticWaits.waitLine(2);
        String actualtitle= driver.getTitle();
        String actualPageName= driver.findElement(By.xpath("(//h1)[2]")).getText();

        Assert.assertEquals(actualtitle,expectedTitle);
        Assert.assertEquals(actualPageName,expectedPageName);

    }


    public static void logout_User(){

        WebDriver driver = Driver.get();
        WebElement userMenu = driver.findElement(By.cssSelector(".dropdown-toggle"));
        userMenu.click();
        //Actions actions = new Actions(driver);
        //actions.moveToElement(.....).perform();

        driver.findElement(By.cssSelector("a[class='no-hash']")).click();
        //ExplicitWaits.loadingWait(driver);  beklemeye gerek yok otomatik çıkıyor reflesh oluyor!!!
    }

    public static WebElement userName(){

        WebDriver driver = Driver.get();
        return driver.findElement(By.cssSelector(".dropdown-toggle"));
    }

}
