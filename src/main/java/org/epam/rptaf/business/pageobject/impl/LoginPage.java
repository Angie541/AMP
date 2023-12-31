package org.epam.rptaf.business.pageobject.impl;

import io.qameta.allure.Step;
import org.epam.rptaf.core.util.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.epam.rptaf.core.util.UiTestUtil.findElement;

public class LoginPage extends AbstractBasePage {
    private static final String PAGE_URL = ConfigReader.getTestData("login_page_url");
    private static final String LOGIN_FIELD = "//input[@name='login']";
    private static final String PASSWORD_FIELD = "//input[@name='password']";
    private static final String LOGIN_BUTTON = "//button[@type='submit']";
    private static final String LOGIN_FORM = "//*[@class='loginForm__login-form--3XCs3']";

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step
    public LoginPage openPage() {
        driver.get(PAGE_URL);
        return this;
    }

    @Step
    public LoginPage loginFormIsVisible() {
        findElement(driver, LOGIN_FORM).isDisplayed();
        return this;
    }

    @Step
    public LoginPage enterLogin(String login) {
        WebElement loginField = findElement(driver, LOGIN_FIELD);
        loginField.click();
        loginField.sendKeys(login);
        return this;
    }

    @Step
    public LoginPage enterPassword(String password) {
        WebElement passwordField = findElement(driver, PASSWORD_FIELD);
        passwordField.click();
        passwordField.sendKeys(password);
        return this;
    }

    @Step
    public void clickLoginButton() {
        findElement(driver, LOGIN_BUTTON).click();
    }

}
