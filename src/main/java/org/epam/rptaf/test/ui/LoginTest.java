package org.epam.rptaf.test.ui;

import org.epam.rptaf.business.pageobject.impl.DashboardPage;
import org.epam.rptaf.core.util.ConfigReader;
import org.epam.rptaf.core.util.UiTestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Iterator;

public class LoginTest extends UiTestBase {
    private final Logger logger = LoggerFactory.getLogger(LoginTest.class);

    @DataProvider(name = "users")
    public static Iterator<String[]> userCredentials() {
        return ConfigReader.getUserTestData().iterator();
    }

    @Test (dataProvider = "users")
    public void loginToReportPortal(String login, String password) {
        logger.info("Testing Login page");
        UiTestUtil.login(driver, login, password);
        DashboardPage dashboardPage = new DashboardPage(driver);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(dashboardPage.isVisible(), "Dashboard page isn't displayed!");
    }

}
