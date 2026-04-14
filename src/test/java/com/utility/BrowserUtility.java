package com.utility;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.constants.Browser.*;

public abstract class BrowserUtility {
    Logger logger = LoggerUtility.getLogger(this.getClass());

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

    public WebDriver getDriver() {
        return driver.get();
    }

    public BrowserUtility(WebDriver driver) {
        super();
        this.driver.set(driver);  //Initialize the instance variable driver!!!!
    }

    public BrowserUtility(com.constants.Browser browserName, boolean isHeadless){
        logger.info("Launching Browser " + browserName);
        if (browserName==CHROME ){
            ChromeOptions options = new ChromeOptions();
            if (isHeadless) {
                options.addArguments("--headless=new"); //headless
                options.addArguments("--window-size = 3000,2000");
                options.addArguments("--disable-gpu");
            }
            driver.set( new ChromeDriver(options));
        }
        else if (browserName==EDGE) {
            EdgeOptions options = new EdgeOptions();
            if(isHeadless){
                options.addArguments("--headless=new"); //headless
                options.addArguments("--window-size = 1920,1080");
                options.addArguments("--disable-gpu");
            }
            driver.set(new EdgeDriver(options));
        }

        else if(browserName==SAFARI){  //safari does not support Headless
            driver.set(new SafariDriver());
        }

        else if(browserName==FIREFOX){
            FirefoxOptions options = new FirefoxOptions();
            if(isHeadless) {
                options.addArguments("--headless"); //headless
                options.addArguments("--window-size = 1920,1080");
                options.addArguments("--disable-gpu");
            }
            driver.set(new FirefoxDriver(options));
        }
        else{
            logger.error("Invalid Browser name, Plesae select Chrome/Edge/Safari/Firefox!!!");
        }
    }

    public void quit() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }

    public void goToWebsite(String url){
        logger.info("Visiting the Website " + url);
        driver.get().get(url);
    }

    public void maximizeWindows(){
        logger.info("Maximizing the Browser Window");
        driver.get().manage().window().maximize();
    }

    public void clickOn(By locator){
        logger.info("Finding Element with the Locator " + locator);
        WebElement element = driver.get().findElement(locator);
        logger.info("Element found and now performing the click");
        element.click();
    }

    public void enterText(By locator, String textToEnter){
        logger.info("Finding Element with the Locator " + locator);
        WebElement element = driver.get().findElement(locator);
        logger.info("Element found and now enter text " + textToEnter);
        element.sendKeys(textToEnter);
    }

    public String getVisibleText(By locator){
        logger.info("Finding Element with the Locator " + locator);
        WebElement element = driver.get().findElement(locator);
        logger.info("Element found now Returning the visible text " + element.getText());
        return element.getText();
    }

    public String takeScreenShot(String name){
        TakesScreenshot screenshot = (TakesScreenshot)driver.get();
        File screenshotData = screenshot.getScreenshotAs(OutputType.FILE);
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("HH-mm-ss");
        String timestamp = format.format(date);
        String path = "./screenshots/" + name+ "-"+timestamp +".png";
        File screenshotFile  = new File(path);
        try {
            FileUtils.copyFile(screenshotData,screenshotFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return path;
    }

}




