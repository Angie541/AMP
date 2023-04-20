package org.epam.rptaf.test.ui;

import org.epam.rptaf.business.pageobject.impl.LoginPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class BasicTest extends UiTestBase {

    private final Logger logger = LoggerFactory.getLogger(BasicTest.class);

    @Test
    public void testLoginPage() {
        logger.info("Testing Login page");
        new LoginPage(driver).openPage();
    }
}
