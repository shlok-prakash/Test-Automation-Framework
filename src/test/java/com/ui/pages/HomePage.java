package com.ui.pages;

import com.constants.Browser;
import static com.constants.Env.*;
import com.utility.BrowserUtility;
import com.utility.JSONUtility;
import com.utility.LoggerUtility;
import com.utility.PropertiesUtil;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public final class HomePage extends BrowserUtility {
    Logger logger = LoggerUtility.getLogger(this.getClass());

    private static final By SING_IN_LINK_LOCATOR = By.xpath("//a[contains(text(),'Sign in')]");
    //class variable, Instance variable, if variable is final its also static, constant value always in upper case
    //Variable inside class and is going to be static then it is also private
    //Design pattern -> the way we create a class.

    public HomePage(Browser browserName, boolean isHeadless) {
        super(browserName,isHeadless); //to call the Parent class constructor from the child class constructor
        goToWebsite(JSONUtility.readJSON(QA).getUrl());
        maximizeWindows();
    }

    public HomePage(WebDriver driver) {
        super(driver); //to call the Parent class constructor from the child class constructor
        goToWebsite(JSONUtility.readJSON(QA).getUrl());
    }


    public LoginPage goToLoginPage(){//page functions --> Cannot use void return type/Needs to return something
        logger.info("Trying to perform Click to go to the Sign In Page");
        clickOn(SING_IN_LINK_LOCATOR);
        LoginPage loginPage = new LoginPage(getDriver());
        return loginPage;
    }



}
