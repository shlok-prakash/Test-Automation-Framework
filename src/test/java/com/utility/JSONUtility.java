package com.utility;

import com.constants.Env;
import com.google.gson.Gson;
import com.ui.pojo.Config;
import com.ui.pojo.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class JSONUtility {

    public static Environment readJSON(Env env)  {

        Gson gson = new Gson(); //First create a Json Object
        File jsonFile = new File(System.getProperty("user.dir")+"/config/config.json"); //Pointer telling me where the file is
        FileReader fileReader = null; //Responsible for reading the file
        try {
            fileReader = new FileReader(jsonFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Config config = gson.fromJson(fileReader, Config.class);
        Environment environment = config.getEnvironments().get("QA");
        return environment;

    }
}
