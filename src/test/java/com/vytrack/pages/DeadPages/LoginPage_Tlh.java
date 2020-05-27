package com.vytrack.pages.DeadPages;

import com.vytrack.utilities.Driver;
import com.vytrack.utilities.DeadUtilities.ExplicitWaits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage_Tlh {

   public static void userLogin(String username, String password){

       WebDriver driver = Driver.get();
        driver.findElement(By.id("prependedInput")).sendKeys(username);
        driver.findElement(By.id("prependedInput2")).sendKeys(password);
        driver.findElement(By.id("_submit")).click();
       ExplicitWaits.loadingWait();
    }

}
