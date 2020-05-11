package com.vytrack.utilities.DeadUtilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoadingWait {

    public static void ExplicitWait(WebDriver driver){

        // this method waits after every click for loading bar!!
        WebElement loading = driver.findElement(By.cssSelector(".loader-mask  .loader-frame"));
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.invisibilityOfAllElements(loading));

    }
}
