package com.vytrack.pages;

import com.vytrack.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    public LoginPage(){
        PageFactory.initElements(Driver.get(),this);
    }

    // same with driver.findElement(By.id("prependedInput"))
    @FindAll //if any of the m true turn , or || var yani
    ({@FindBy(id = "prependedInput"),
      @FindBy(name = "_username"),
    })
    public WebElement usernameInput;


    @FindBys//if all of the m true turn , and && var yani
            ({@FindBy(id = "prependedInput2"),
              @FindBy(name = "_passwords"),
            })
    public WebElement passwordInput;

    @FindBy(id = "_submit")
    public WebElement loginBtn;

    public void login(String usernameStr, String passwordStr){

        usernameInput.sendKeys(usernameStr);
        passwordInput.sendKeys(passwordStr);
        loginBtn.click();
    }
}
