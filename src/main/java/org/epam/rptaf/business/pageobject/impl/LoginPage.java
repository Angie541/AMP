package org.epam.rptaf.business.pageobject.impl;

import org.epam.rptaf.core.util.ConfigReader;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class LoginPage extends AbstractBasePage {

    private static final String PAGE_URL = ConfigReader.getTestData("login_page_url");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage openPage() {
        driver.get(PAGE_URL);
        return this;
    }
}
