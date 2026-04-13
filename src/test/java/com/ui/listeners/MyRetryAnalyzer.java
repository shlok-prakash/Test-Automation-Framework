package com.ui.listeners;

import com.constants.Env;
import com.utility.JSONUtility;
//import com.utility.PropertiesUtil;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class MyRetryAnalyzer implements IRetryAnalyzer {

    //private static final int MAX_NUMBER_OF_ATTEMPTS = Integer.parseInt(PropertiesUtil.readProperty(Env.UAT, "MAX_NUMBER_OF_ATTEMPTS"));
    private static final int MAX_NUMBER_OF_ATTEMPTS  = JSONUtility.readJSON(Env.QA).getMAX_NUMBER_OF_ATTEMPTS();
    private static int currentAttempt = 1;


    @Override
    public boolean retry(ITestResult result) {
        if(currentAttempt<=MAX_NUMBER_OF_ATTEMPTS){
            currentAttempt++;
            return true;
        }
        return false;
    }
}
