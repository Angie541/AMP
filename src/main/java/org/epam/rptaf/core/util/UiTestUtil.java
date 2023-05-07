package org.epam.rptaf.core.util;

import org.epam.rptaf.business.pageobject.impl.LoginPage;
import org.openqa.selenium.WebDriver;

public class UiTestUtil {

    private UiTestUtil() {

    }

    public static void login(WebDriver driver, String login, String password) {
        new LoginPage(driver).openPage()
                .loginFormIsVisible()
                .enterLogin(login)
                .enterPassword(password)
                .clickLoginButton();
    }

}
