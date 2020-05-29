package com.vytrack.pages.activities.calendarEvents;

import com.vytrack.pages.BasePage;
import com.vytrack.utilities.BrowserUtils;
import com.vytrack.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Arrays;

public class CalendarEventsPage extends BasePage {

    public CalendarEventsPage() {
        PageFactory.initElements(Driver.get(), this);
    }

    @FindBy(css = "[title='Create Calendar event']")
    public WebElement createCalendarEvent;

    @FindBy (xpath = "//div[@class='btn btn-link dropdown-toggle']")
    public WebElement options;

    @FindBy (css = ".input-widget")
    public WebElement pageNumber;

    @FindBy(xpath ="//*[contains(@class,'btn dropdown-toggle')]")
    public WebElement viewPerPage;

    @FindBy (css = "table tr.grid-row")
    public WebElement allRows;

    @FindBy (css = ".fa-chevron-right.hide-text")
    public WebElement nextPageLink;

    @FindBy (xpath = "//*[@class='dib'][2]")
    public WebElement totalPage;

    @FindBy (xpath = "//*[@class='dib'][3]")
    public WebElement totalRecords;

    @FindBy (xpath = "//button//input")
    public WebElement allCheckbox;

    @FindBy (css = ".grid-body-cell-title")
    public WebElement titleCells;




    public String getTotalPageString(){

       return totalPage.getText().split(" ")[1];
    }


    public int getTotalPageInt(){
        BrowserUtils.waitFor(2);
//        String[] totalPagee = totalPage.getText().split(" ");
//        System.out.println(Arrays.toString(totalPagee));
     //  System.out.println(Integer.valueOf(totalPage.getText().split(" ")[1]));
        return Integer.valueOf(totalPage.getText().split(" ")[1]);
    }


    public boolean isLastPage(){
       // System.out.println(pageNumber.getAttribute("value"));
       // System.out.println(totalPage());
        if (pageNumber.getAttribute("value").equals(getTotalPageString())) {
            return true;
        }
        return false;
    }


    public int getRowsNumbers(){
        BrowserUtils.waitFor(1);
        return Driver.get().findElements(By.cssSelector("table>tbody tr.grid-row")).size();
    }

    public int getTotalRecords(){

        return Integer.valueOf(totalRecords.getText().split(" ")[2]);
    }

    public WebElement getControlElement(String data){

        return Driver.get().findElement(By.xpath("//div[@class='control-label'][contains(text(),'" + data + "')]"));
    }

}