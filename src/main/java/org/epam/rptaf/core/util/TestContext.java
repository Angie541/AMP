package org.epam.rptaf.core.util;

import org.epam.rptaf.core.enums.TestContextKey;
import org.epam.rptaf.test.ui.BasicTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public final class TestContext {
    private static final Map<TestContextKey, String> STORAGE = new ConcurrentHashMap<>();
    private static final Queue<WebDriver> DRIVERS = new ConcurrentLinkedQueue<>();
    private static final Logger logger = LoggerFactory.getLogger(TestContext.class);

    private TestContext() {
    }

    public static void put(final TestContextKey key, final String value) {
        STORAGE.put(key, value);
    }

    public static String get(final TestContextKey key) {
        return STORAGE.get(key);
    }

    public static void putDriver(WebDriver driver) {
        logger.info("putDriver");
        DRIVERS.add(driver);
    }

    public static WebDriver getDriver() {
        logger.info("GET DRIVER");
        return DRIVERS.poll();
    }

    public static Queue<WebDriver> getAllDrivers() {
        return DRIVERS;
    }

}
