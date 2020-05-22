package com.vytrack.tests.tasks;

import com.vytrack.pages.LoginPage;
import com.vytrack.tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NegativeLoginTestWithReport extends TestBase {


    @Test
    public void wrongPasswordTest(){

        extentLogger = report.createTest("Wrong Password Test");

        LoginPage loginPage = new LoginPage();

        loginPage.usernameInput.sendKeys("user1");
        extentLogger.info("Enter Username: User1");
        loginPage.passwordInput.sendKeys("somepassword");
        extentLogger.info("Enter Password: somepassword");
        loginPage.loginBtn.click();
        extentLogger.info("click login button");

        extentLogger.info("Verify page url");
        Assert.assertEquals(driver.getCurrentUrl(),"https://qa3.vytrack.com/user/login");
        //we put after assertion so if the assertion fails, it will not send pass to report
        extentLogger.pass("Wrong Passord Test is passed");
    }

    @Test
    public void wrongUsernameTest(){
        //name of the test for report
        extentLogger = report.createTest("Wrong Username Test");
        LoginPage loginPage = new LoginPage();

        loginPage.usernameInput.sendKeys("someusername");
        extentLogger.info("Enter username: someusername");

        loginPage.passwordInput.sendKeys("UserUser123");
        extentLogger.info("Enter password: UserUser123");

        loginPage.loginBtn.click();
        extentLogger.info("Click login button");

        extentLogger.info("verify page url");
        Assert.assertEquals(driver.getCurrentUrl(),"https://qa3.vytrack.com/user/login");
        Assert.assertTrue(1<0);
        extentLogger.pass("PASS: Wrong password Test");
    }
}