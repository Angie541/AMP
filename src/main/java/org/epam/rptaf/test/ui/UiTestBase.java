package org.epam.rptaf.test.ui;

import org.epam.rptaf.core.driver.DriverSingleton;
import org.epam.rptaf.core.util.TestListener;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners({TestListener.class})
public abstract class UiTestBase {
    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        driver = DriverSingleton.getDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        DriverSingleton.closeDriver();
    }
}
