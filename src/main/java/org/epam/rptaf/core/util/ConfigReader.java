package org.epam.rptaf.core.util;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ConfigReader {

    private static final String CREDENTIALS_KEY = "credentials";
    private static final String FILTER_NAMES_FOR_EDITING_KEY = "filterNamesForEditing";
    private static final String NEW_FILTERS_NAMES_KEY = "newFiltersNames";
    private static final String ARRAY_SEPARATOR = "; ";
    private static final String OBJECT_SEPARATOR = ", ";
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("config");

    private ConfigReader() {

    }

    public static String getTestData(String key) {
        return RESOURCE_BUNDLE.getString(key);
    }

    public static List<String[]> getUserTestData() {
        return getObjectsTestData(CREDENTIALS_KEY);
    }

    public static List<String[]> getFilterNamesForEditingTestData() {
        return getObjectsTestData(FILTER_NAMES_FOR_EDITING_KEY);
    }

    public static List<String[]> getNewFiltersNamesTestData() {
        return getObjectsTestData(NEW_FILTERS_NAMES_KEY);
    }

    private static List<String[]> getObjectsTestData(String key) {
        String[] testDataArrays = RESOURCE_BUNDLE.getString(key).split(ARRAY_SEPARATOR);
        List<String[]> result = new ArrayList<>();
        for (String testDataArray : testDataArrays) {
            result.add(testDataArray.split(OBJECT_SEPARATOR));
        }
        return result;
    }

}
