package com.qa.gorest.configuration;

import com.qa.gorest.frameworkexceptions.APIFrameworkException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;

public class ConfigurationManager {

    public Properties initProp(){

        Properties properties = new Properties();

        String envName = System.getProperty("env");
        String configFileName;

        if(envName==null){
            envName = "qa";
            System.out.println("No env provided so running test on env: "+envName);
        }
        System.out.println("Running test on env: " + envName);

        switch (envName.toLowerCase(Locale.ROOT).trim()){
            case "qa":
                configFileName = "qa.config.properties";
                break;
            case "dev":
                configFileName = "dev.config.properties";
                break;
            default:
                System.out.println("Please provide valid env name");
                throw new APIFrameworkException("No Valid Env name.");
        }


        try (FileInputStream fis = new FileInputStream("./src/test/resources/config/"+configFileName);){
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
