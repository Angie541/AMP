package org.epam.rptaf.test.ui.junit;

import org.epam.rptaf.business.pageobject.impl.DashboardPage;
import org.epam.rptaf.core.util.ConfigReader;
import org.epam.rptaf.core.util.UiTestUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class LoginTest extends JUnitTestBase {
    private final Logger logger = LoggerFactory.getLogger(LoginTest.class);
    private String login;
    private String password;

    public LoginTest(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Parameterized.Parameters
    public static Iterable<String[]> userCredentials() {
        return ConfigReader.getUserTestData();
    }

    @Test
    public void loginToReportPortal() {
        logger.info("Testing Login page");
        UiTestUtil.login(driver, login, password);
        DashboardPage dashboardPage = new DashboardPage(driver);
        assertTrue(dashboardPage.isVisible());
    }

}
