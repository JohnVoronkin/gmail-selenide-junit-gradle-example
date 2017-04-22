package com.store.demoqa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {

    public static String loadProperty(String name) {
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream("src" + File.separator + "test"
                    + File.separator + "resources" + File.separator + "properties"
                    + File.separator + "resources.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String value = "";
        if (name != null) {
            value = prop.getProperty(name);
        }
        return value;
    }

}
