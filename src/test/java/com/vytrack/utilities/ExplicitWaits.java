package com.vytrack.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExplicitWaits {

    public static void titleWait(WebDriver driver, String expectedTitle){

        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.titleIs(expectedTitle));


    }

    public static void loadingWait(WebDriver driver){

        // this method waits after every click for loading bar!!
        // put try catch for if u dont need wait for login method // for invalid test case!!
        try {
            WebElement loading = driver.findElement(By.cssSelector(".loader-mask  .loader-frame"));
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.invisibilityOfAllElements(loading));
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public static void flashMessageWait(WebDriver driver,WebElement flashMessage){

        // this method waits after every click for loading bar!!
        WebElement loading = driver.findElement(By.cssSelector(".loader-mask  .loader-frame"));
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOf(flashMessage));
    }





}
