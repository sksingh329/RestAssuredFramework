package com.qa.gorest.configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationManager {

    public Properties initProp(){
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("./src/test/resources/config/config.properties");){
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
