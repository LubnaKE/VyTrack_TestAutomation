package com.vytrack.pages;

import com.vytrack.utilities.ConfigurationReader;
import com.vytrack.utilities.Driver;
import com.vytrack.utilities.StaticWaits;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class SetAndClosePage {

  protected WebDriver driver;

    @BeforeMethod
    protected void beforeMethod(){
        driver= Driver.get();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(ConfigurationReader.get("url"));
    }

    @AfterMethod
    protected void afterMethod() {
       StaticWaits.waitLine(3);
        // driver.quit don't use this. after quit u need assing null to next testNg method!!!
        Driver.closeDriver();
    }
}
