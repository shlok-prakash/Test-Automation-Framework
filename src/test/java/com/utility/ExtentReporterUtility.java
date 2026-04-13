package com.utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class  ExtentReporterUtility {

    private static ExtentReports extentReports;//we just want one report once we use static, so that only one variable is created in the Heap memory
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

    public static void setupSparkReporter(String reportName){
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "//" +reportName); //this line is saying that the report needs to be created at the specific location
        extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);
    }

    public static void createExtentTest(String testName){
        ExtentTest test  = extentReports.createTest(testName);
        extentTest.set(test);
    }

    public static ExtentTest getTest(){
        return extentTest.get();
    }

    public static void flushReport(){
        extentReports.flush();
    }
}
