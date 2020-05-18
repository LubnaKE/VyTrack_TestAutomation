package com.vytrack.tests.smoke_tests;

import com.vytrack.pages.LoginPage_Tlh;
import com.vytrack.pages.SetAndClosePage;
import com.vytrack.utilities.ConfigurationReader;
import org.testng.annotations.Test;

import static com.vytrack.utilities.SingleMethods.*;


public class MenuOptionsTest extends SetAndClosePage {

    @Test (description = "Menu options, Truck driver")
    public void test1_TruckDriver(){

        //TEST CASE:
        //1. Login to Vytrack as a driver
        LoginPage_Tlh.userLogin(ConfigurationReader.get("driver_username"),ConfigurationReader.get("driver_password"));


        //2. Navigate to Fleet->Vehicles
        navigateTo_Module_Page(driver,"Fleet", "Vehicles");
            //verify page title & page name
        verify_Title_PageName(driver,"Car - Entities - System - Car - Entities - System", "Cars");


        //3. Navigate to Customers->Accounts
        navigateTo_Module_Page(driver,"Customers", "Accounts");
            //verify page title & page name
        verify_Title_PageName(driver,"Accounts - Customers", "Accounts");


        //4. Navigate to Customers->Contacts
        navigateTo_Module_Page(driver,"Customers", "Contacts");
            //verify page title & page name
        verify_Title_PageName(driver,"Contacts - Customers", "Contacts");


        //5. Navigate to Activities->Calendar Events
        navigateTo_Module_Page(driver,"Activities", "Calendar Events");
            //verify page title & page name
        verify_Title_PageName(driver,"Calendar Events - Activities", "Calendar Events");

    }

    @Test (description = "Menu options, Store Manager")
    public void test2_StoreManager(){

        //TEST CASE:
        //1. Login to Vytrack as a Store Manager
        LoginPage_Tlh.userLogin(ConfigurationReader.get("storemanager_username"),ConfigurationReader.get("storemanager_password"));


        //2. Navigate to Dashboards and verify page title & page name
        navigateTo_Module_Page(driver,"Dashboards", "Dashboard");
        verify_Title_PageName(driver,"Dashboard - Dashboards", "Dashboard" );


        //3. Navigate to Fleet->Vehicles
        navigateTo_Module_Page(driver,"Fleet", "Vehicles");
        //verify page title & page name
        verify_Title_PageName(driver,"All - Car - Entities - System - Car - Entities - System", "All Cars");


        //4. Navigate to Customers->Accounts
        navigateTo_Module_Page(driver,"Customers", "Accounts");
        //verify page title & page name
        verify_Title_PageName(driver,"Accounts - Customers", "Accounts");


        //5. Navigate to Customers->Contacts
        navigateTo_Module_Page(driver,"Customers", "Contacts");
        //verify page title & page name
        verify_Title_PageName(driver,"All - Contacts - Customers", "All Contacts");


        //6. Navigate to Sales->Opportunities,
        navigateTo_Module_Page(driver,"Sales", "Opportunities");
        //verify page title & page name
        verify_Title_PageName(driver,"Open Opportunities - Opportunities - Sales", "Open Opportunities");


        //7. Navigate to Activities->Calls,
        navigateTo_Module_Page(driver,"Activities", "Calls");
        //verify page title & page name
        verify_Title_PageName(driver,"All - Calls - Activities", "All Calls");


        //8. Navigate to Activities->Calendar Events,
        navigateTo_Module_Page(driver,"Activities", "Calendar Events");
        //verify page title & page name
        verify_Title_PageName(driver,"All - Calendar Events - Activities", "All Calendar Events");
    }
}
