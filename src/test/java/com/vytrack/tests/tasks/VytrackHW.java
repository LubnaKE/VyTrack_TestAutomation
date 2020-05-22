package com.vytrack.tests.tasks;

import com.vytrack.pages.CalendarEventsPage;
import com.vytrack.pages.DashboardPage;
import com.vytrack.pages.LoginPage;
import com.vytrack.tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VytrackHW extends TestBase {

    // Test DATA:
    // Environment: https://qa1.vytrack.com
    // UserName/Password : storemanager  / UserUser123

    LoginPage loginPage = new LoginPage();
    CalendarEventsPage calendarEventsPage = new CalendarEventsPage();

    public void testCaseSameLines(){

        extentLogger.info("username: "+"storemanager");
        extentLogger.info("password: "+"UserUser123");

        // i added  loader wait ib login method so i dont need wait extra
        extentLogger.info("Login as a Store Manager");
        loginPage.loginAs("storemanager");

        extentLogger.info("Navigate to Activities -> Calendar Events");
        new DashboardPage().navigateToModule("Activities", "Calendar Events");

        calendarEventsPage.waitUntilLoaderScreenDisappear();
    }

    @Test
    public void calendarEventsOptionTest(){

        extentLogger = report.createTest("TestCase #1: Calendar Events Option Display");

        testCaseSameLines();

        extentLogger.info("Verify subtitle Option is displayed");
        Assert.assertTrue(calendarEventsPage.options.isDisplayed(),"Option is displayed");


        extentLogger.pass("PASS: TestCase #1 Calendar Events Option Display ");
    }


    @Test
    public void calendarEventsPageNumberTest(){

        extentLogger = report.createTest("TestCase #2: Calendar Events Option Page Number Verification");

        testCaseSameLines();

        extentLogger.info("Verify that page number is equals to \"1\"");
        Assert.assertEquals(calendarEventsPage.pageNumber.getAttribute("value"),"1");

        extentLogger.pass("PASS: TestCase #2 Calendar Events Option Page Number Verification");
    }




}
