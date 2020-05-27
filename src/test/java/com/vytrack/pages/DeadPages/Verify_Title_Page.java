package com.vytrack.pages.DeadPages;

import com.vytrack.utilities.DeadUtilities.ExplicitWaits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Verify_Title_Page {

    public static void verify(WebDriver driver, String expectedTitle, String expectedPageName ) {

        // to wait update title we need extra explicit wait!!
        ExplicitWaits.titleWait(expectedTitle);
       // StaticWaits.waitLine(2);
        String actualtitle= driver.getTitle();
        String actualPageName= driver.findElement(By.xpath("(//h1)[2]")).getText();

        Assert.assertEquals(actualtitle,expectedTitle);
        Assert.assertEquals(actualPageName,expectedPageName);

    }
}
