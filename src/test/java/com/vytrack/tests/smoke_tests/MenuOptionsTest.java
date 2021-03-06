package com.vytrack.tests.smoke_tests;

import com.vytrack.pages.DeadPages.LoginPage_Tlh;
import com.vytrack.pages.DeadPages.SetAndClosePage;
import com.vytrack.utilities.ConfigurationReader;
import org.testng.annotations.Test;

import static com.vytrack.utilities.DeadUtilities.SingleMethods.*;


public class MenuOptionsTest extends SetAndClosePage {

    @Test (description = "Menu options, Truck driver")
    public void test1_TruckDriver(){

        //TEST CASE:
        //1. Login to Vytrack as a driver
        LoginPage_Tlh.userLogin(ConfigurationReader.get("driver_username"),ConfigurationReader.get("driver_password"));


        //2. Navigate to Fleet->Vehicles
        navigateTo_Module_Page("Fleet", "Vehicles");
            //verify page title & page name
        verify_Title_PageName("Car - Entities - System - Car - Entities - System", "Cars");


        //3. Navigate to Customers->Accounts
        navigateTo_Module_Page("Customers", "Accounts");
            //verify page title & page name
        verify_Title_PageName("Accounts - Customers", "Accounts");


        //4. Navigate to Customers->Contacts
        navigateTo_Module_Page("Customers", "Contacts");
            //verify page title & page name
        verify_Title_PageName("Contacts - Customers", "Contacts");


        //5. Navigate to Activities->Calendar Events
        navigateTo_Module_Page("Activities", "Calendar Events");
            //verify page title & page name
        verify_Title_PageName("Calendar Events - Activities", "Calendar Events");

    }

    @Test (description = "Menu options, Store Manager")
    public void test2_StoreManager(){

        //TEST CASE:
        //1. Login to Vytrack as a Store Manager
        LoginPage_Tlh.userLogin(ConfigurationReader.get("storemanager_username"),ConfigurationReader.get("storemanager_password"));


        //2. Navigate to Dashboards and verify page title & page name
        navigateTo_Module_Page("Dashboards", "Dashboard");
        verify_Title_PageName("Dashboard - Dashboards", "Dashboard" );


        //3. Navigate to Fleet->Vehicles
        navigateTo_Module_Page("Fleet", "Vehicles");
        //verify page title & page name
        verify_Title_PageName("All - Car - Entities - System - Car - Entities - System", "All Cars");


        //4. Navigate to Customers->Accounts
        navigateTo_Module_Page("Customers", "Accounts");
        //verify page title & page name
        verify_Title_PageName("Accounts - Customers", "Accounts");


        //5. Navigate to Customers->Contacts
        navigateTo_Module_Page("Customers", "Contacts");
        //verify page title & page name
        verify_Title_PageName("All - Contacts - Customers", "All Contacts");


        //6. Navigate to Sales->Opportunities,
        navigateTo_Module_Page("Sales", "Opportunities");
        //verify page title & page name
        verify_Title_PageName("Open Opportunities - Opportunities - Sales", "Open Opportunities");


        //7. Navigate to Activities->Calls,
        navigateTo_Module_Page("Activities", "Calls");
        //verify page title & page name
        verify_Title_PageName("All - Calls - Activities", "All Calls");


        //8. Navigate to Activities->Calendar Events,
        navigateTo_Module_Page("Activities", "Calendar Events");
        //verify page title & page name
        verify_Title_PageName("All - Calendar Events - Activities", "All Calendar Events");
    }
}
