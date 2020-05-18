package com.vytrack.pages;

import com.vytrack.utilities.Driver;
import com.vytrack.utilities.ExplicitWaits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class LoginPage_Tlh {

   public static void userLogin(String username, String password){

       WebDriver driver = Driver.get();
        driver.findElement(By.id("prependedInput")).sendKeys(username);
        driver.findElement(By.id("prependedInput2")).sendKeys(password);
        driver.findElement(By.id("_submit")).click();
       ExplicitWaits.loadingWait(driver);
    }

}
