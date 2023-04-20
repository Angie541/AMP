package org.epam.rptaf.core.util;

import java.util.ResourceBundle;

public class ConfigReader {
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("config");

    public static String getTestData(String key) {
        return resourceBundle.getString(key);
    }
}
