package org.epam.rptaf.business.stepdefs;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.epam.rptaf.core.util.TestContext;
import org.openqa.selenium.WebDriver;

public class CucumberHook {

    private CucumberHook() {
    }

    @BeforeAll
    public static void beforeAll() {
        WebDriverManager.chromedriver().setup();
    }

    @AfterAll
    public static void closeBrowser() {
        TestContext.getAllDrivers().forEach(WebDriver::quit);
    }

}
