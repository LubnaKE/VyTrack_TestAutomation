package com.vytrack.tests.tasks;

import com.vytrack.pages.BasePage;
import com.vytrack.pages.CalendarEventsPage;
import com.vytrack.pages.DashboardPage;
import com.vytrack.pages.LoginPage;
import com.vytrack.tests.TestBase;
import com.vytrack.utilities.BrowserUtils;
import com.vytrack.utilities.Driver;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import sun.java2d.pipe.AAShapePipe;

public class VytrackHW extends TestBase {

    // Test DATA:
    // Environment: https://qa1.vytrack.com
    // UserName/Password : storemanager  / UserUser123

    LoginPage loginPage = new LoginPage();
    CalendarEventsPage calendarEventsPage = new CalendarEventsPage();

    public void testCaseSameLines() {

        extentLogger.info("username: " + "storemanager");
        extentLogger.info("password: " + "UserUser123");

        // i added  loader wait ib login method so i dont need wait extra
        extentLogger.info("Login as a Store Manager");
        loginPage.loginAs("storemanager");

        extentLogger.info("Navigate to Activities -> Calendar Events");
        new DashboardPage().navigateToModule("Activities", "Calendar Events");

        calendarEventsPage.waitUntilLoaderScreenDisappear();
    }

    @Test(description = "TestCase #1")
    public void calendarEventsOptionTest() {

        extentLogger = report.createTest("TestCase #1: Calendar Events Option Display");

        testCaseSameLines();

        extentLogger.info("Verify subtitle Option is displayed");
        Assert.assertTrue(calendarEventsPage.options.isDisplayed(), "Option is displayed");


        extentLogger.pass("PASS: TestCase #1 Calendar Events Option Display ");
    }


    @Test(description = "TestCase #2")
    public void calendarEventsPageNumberTest() {

        extentLogger = report.createTest("TestCase #2:Calendar Events Option Page Number Verification");

        testCaseSameLines();

        extentLogger.info("Verify that page number is equals to \"1\"");
        Assert.assertEquals(calendarEventsPage.pageNumber.getAttribute("value"), "1");

        extentLogger.pass("PASS: TestCase #2 Calendar Events Option Page Number Verification");
    }


    @Test(description = "TestCase #3")
    public void calendarEventsViewPerPageTest() {

        extentLogger = report.createTest("TestCase #3: Calendar Events View Per page num Verification");

        testCaseSameLines();

        extentLogger.info("Verify that view per page number is equals to\"25\"");

        // System.out.println("view per page:" + calendarEventsPage.viewPerPage.getText());
        BrowserUtils.waitFor(2);
        Assert.assertEquals(calendarEventsPage.viewPerPage.getText().trim(), "25");

        extentLogger.pass("PASS: TestCase #3: Calendar Events View Per page num Verification");
    }


    @Test(description = "TestCase #4")
    public void calendarEventsNumberOfCalendarEventsTest() {

        extentLogger = report.createTest("TestCase #4: Number of calendar events Verification");

        testCaseSameLines();

        extentLogger.info("Verify that number of calendar events (rows in the table) is equals to number of records");

//        System.out.println("isClickable: " + BasePage.isClickableViaLoader(calendarEventsPage.nextPageLink));
//       // calendarEventsPage.nextPageLink.click();
//        BasePage.waitUntilLoaderScreenDisappear();
//        BrowserUtils.waitFor(1);
//        System.out.println("isClickable: " + BasePage.isClickableViaLoader(calendarEventsPage.nextPageLink));
//      //  while (calendarEventsPage.nextPageLink.isEnabled())

        int totalEvents = 0;
        boolean check = false;
        do {
            BrowserUtils.waitFor(2);  // hemen tablo güncellenmiyor neden???
            //System.out.println("cureent page row Num:" + calendarEventsPage.rowsNumbers());
            totalEvents += calendarEventsPage.getRowsNumbers();
            check = calendarEventsPage.isLastPage();
            calendarEventsPage.nextPageLink.click();
            BasePage.waitUntilLoaderScreenDisappear();

        } while (!check);

        Assert.assertEquals(totalEvents, calendarEventsPage.getTotalRecords(), "Check event numbers");

        extentLogger.pass("PASS: TestCase #4: Number of calendar events Verification");
    }



    @Test(description = "TestCase #5")
    public void calendarEventsSelectAllEventsTest() {

        extentLogger = report.createTest("TestCase #5: All calendar events is selected Verification");

        testCaseSameLines();

        extentLogger.info("Verify that all calendar events were selected");

        calendarEventsPage.allCheckbox.click();

        for (int i=1; i<=calendarEventsPage.getRowsNumbers(); i++){
            Assert.assertTrue(Driver.get().findElement(By.xpath("//tr["+i+"]//td[1]//input[1]")).isSelected());
        }

        extentLogger.pass("PASS: TestCase #5: All calendar events is selected Verification");

    }




    @Test(description = "TestCase #6")
    public void calendarEventsMeetingVerifyTest() {

        extentLogger = report.createTest("TestCase #6: Testers Meeting data Verification");

        testCaseSameLines();

        extentLogger.info("Verify that given data is displayed");


        String expectedTitle = "Testers meeting";
        String expectedStartDate= "Nov 27, 2019, 9:30 AM";
        String expectedDescripton= "This is a a weekly testers meeting";
        String expectedEndDate= "Nov 27, 2019, 10:30 AM";
        String expectedAllDayEvent= "No";
        String expectedOrganizer= "Stephan Haley";
        String expectedGuest= "Tom Smith";
        String expectedRecurrence= "Weekly every 1 week on Wednesday";
        String expectedCallViaHangout= "  No";

// 1.WAY (check all page 1 by 1)
        extentLogger.info("Search for expected title and click");
        line:for (int i = 1; i<=calendarEventsPage.getTotalPageInt(); i++){
              for (int j=1; j<= calendarEventsPage.getRowsNumbers(); j++) {
                if (Driver.get().findElement(By.xpath("//tr[" + j + "]/td[@data-column-label='Title']")).getText().equals(expectedTitle)) {
                   if(Driver.get().findElement(By.xpath("//tr[" + j + "]/td[@data-column-label='Start']")).getText().equals(expectedStartDate)){
                        Driver.get().findElement(By.xpath("//tr[" + j + "]/td[@data-column-label='Title']")).click();
                        BasePage.waitUntilLoaderScreenDisappear();
                        break line;
                   }
                }
                System.out.println("i:"+i+" j:"+j);
            }
        }
// 2.WAY (use SearchBox)
        //
        //
        //

        Assert.assertTrue(calendarEventsPage.getControlElement(expectedTitle).isDisplayed());
        Assert.assertTrue(calendarEventsPage.getControlElement(expectedStartDate).isDisplayed());
        Assert.assertTrue(calendarEventsPage.getControlElement(expectedStartDate).isDisplayed());
        Assert.assertTrue(calendarEventsPage.getControlElement(expectedRecurrence).isDisplayed());
        Assert.assertTrue(calendarEventsPage.getControlElement(expectedCallViaHangout).isDisplayed());

        Assert.assertTrue(Driver.get().findElement(By.xpath("//p[contains(text(),'"+expectedDescripton+"')]")).isDisplayed());
        Assert.assertTrue(Driver.get().findElement(By.xpath("//a[contains(text(),'"+expectedGuest+"')]")).isDisplayed());
        Assert.assertTrue(Driver.get().findElement(By.xpath("//a[contains(@class,'')][contains(text(),'"+expectedOrganizer+"')]")).isDisplayed()); ////div[@class='calendar-event-organizer']
        Assert.assertTrue(Driver.get().findElement(By.xpath("//*[.='"+expectedAllDayEvent+"']")).isDisplayed());

         extentLogger.pass("PASS: TestCase #6: Testers Meeting data Verification");
    }

}
