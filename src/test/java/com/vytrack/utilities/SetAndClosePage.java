package com.vytrack.utilities;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class SetAndClosePage {

  protected WebDriver driver;

    @BeforeMethod
    public void beforeMethod(){
        driver=Driver.get();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(ConfigurationReader.get("url"));
    }

    @AfterMethod
    public void afterMethod() throws InterruptedException {
        WaitStatic.waitLine(3000);
        // driver.quit don't use this. after quit u need assing null to next testNg method!!!
        Driver.closeDriver();
    }
}
