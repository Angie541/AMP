package org.epam.rptaf.core.util;

import org.epam.rptaf.business.pageobject.impl.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UiTestUtil {

    private UiTestUtil() {

    }
    public static WebDriver getNewDriver() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        TestContext.putDriver(driver);
        return driver;
    }

    public static void login(WebDriver driver, String login, String password) {
        new LoginPage(driver).openPage()
                .loginFormIsVisible()
                .enterLogin(login)
                .enterPassword(password)
                .clickLoginButton();
    }

     public static WebElement findElement(WebDriver driver, String filterXpath) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(filterXpath)));
        return driver.findElement(By.xpath(filterXpath));
    }

}
