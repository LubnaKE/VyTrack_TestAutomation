package com.vytrack.tests.components.fleet;

import com.vytrack.pages.LoginPage;
import com.vytrack.pages.DeadPages.SetAndClosePage;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class ByTest extends SetAndClosePage {

    @Test
    public void test() {

        LoginPage loginP = new LoginPage();

        loginP.login("storemanager55", "UserUser123");


        // By locatorS= By.className("fa-question-circle");
      //  waitToClick(By.xpath("//a[contains(@class,'btn btn-mini btn-primary no-hash')]"),5 );

        waitForStaleElement(driver.findElement(By.xpath("//a[contains(@class,'btn btn-mini btn-primary no-hash')]")));



    }

    public void clicklink(By locator){
        driver.findElement(locator).click();
    }

    public void waitToClick(By locator, int attemp){
            int x=0;
            while (x<attemp) {
                try {
                    clicklink(locator);
                    return;
                } catch (WebDriverException e) {
                    x++;
                   // e.printStackTrace();
                    System.out.println(e.getMessage());
                    System.out.println("x = " + x);
                }
            }
        }



    public static void waitForStaleElement(WebElement element) {
        int y = 0;
        while (y <= 15) {
            if (y == 1)
                try {
                    element.isDisplayed();
                    break;
                } catch (StaleElementReferenceException st) {
                    y++;
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } catch (WebDriverException we) {
                    y++;
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        }
    }

}
