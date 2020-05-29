package com.vytrack.pages.activities.calendarEvents;

import com.vytrack.pages.BasePage;
import com.vytrack.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class EventInfoPage extends BasePage {


    @FindBy (xpath = "(//div[@class='control-group attribute-row']/div/div)")
    WebElement allRow;

    By allrow = By.xpath("(//div[@class='control-group attribute-row']/div/div)");


    public List<WebElement> getAllRowsElemnts(){
        return Driver.get().findElements(allrow);
    }

    public List<String> getAllRowsText() {
        List<String> allRowsText = new ArrayList<>();
        for (WebElement row : getAllRowsElemnts()) {
            allRowsText.add(row.getText());
        }
        return allRowsText;
    }


    public boolean allRowsDisplayCheck(){

        for (WebElement row : getAllRowsElemnts()) {
            if (!row.isDisplayed()){
                return false;
            }
        }
        return true;
    }




}
