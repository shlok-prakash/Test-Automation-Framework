package com.ui.tests;

import com.constants.Browser;
import com.ui.pages.HomePage;
import com.utility.BrowserUtility;
import com.utility.LambdaTestUtility;
import com.utility.LoggerUtility;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;


public class TestBase { //parent of all the test classes

    HomePage homePage; //Instance Variable
    Logger logger = LoggerUtility.getLogger(this.getClass());

    private boolean isLamdaTest;

    @Parameters({"browser","isLamdaTest", "isHeadless"})
    @BeforeMethod(description = "Load the HomePage of the Website")
    public void setup(
            @Optional("chrome") String browser,
            @Optional("false") boolean isLamdaTest,
            @Optional("true") boolean isHeadless, ITestResult result){
        WebDriver lambdaDriver;
        if(isLamdaTest){
            lambdaDriver = LambdaTestUtility.initializeLambdaTestSession(browser, result.getMethod().getMethodName());
            homePage = new HomePage(lambdaDriver);
        }else {
            logger.info("Load the Homepage of the website");
            homePage = new HomePage(Browser.valueOf(browser.toUpperCase()), isHeadless);
        }
    }

    public BrowserUtility getInstance(){
        return homePage;
    }

    @AfterMethod(description = "Tear Down the browser")
    public void tearDown(){
        if (isLamdaTest) {
            LambdaTestUtility.quitSession();
        }else {
            homePage.quit();   // call quit method from BrowserUtility
        }
    }
}
