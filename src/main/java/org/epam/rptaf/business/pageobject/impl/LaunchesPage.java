package org.epam.rptaf.business.pageobject.impl;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LaunchesPage extends AbstractBasePage {
    private static final String LAUNCHES_PAGE_LAYOUT = "//div[@class='pageLayout__page-content--2R36V'][1]";
    private static final String ENTER_NAME_FIELD = "//input[@placeholder='Enter name']";
    private static final String SAVE_BUTTON = "//span[text()='Save']";
    private static final String EDIT_FILTER_NAME_FIELD = "//input[@placeholder='Enter filter name']";
    private static final String ADD_FILTER_BUTTON = "//button[text()='Add']";
    private static final String NEW_FILTER_ICON = "//span[@class='filterItem__name--3ev0G'][text()='%s']";

    public LaunchesPage(WebDriver driver) {
        super(driver);
    }

    @Step
    public boolean isVisible() {
        WebElement layout = new WebDriverWait(driver, Duration.ofSeconds(100))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LAUNCHES_PAGE_LAYOUT)));
        return layout.isDisplayed();
    }

    @Step
    public LaunchesPage enterFilterValue(String filterValue) {
        getEnterNameField().click();
        getEnterNameField().sendKeys(filterValue);
        return this;
    }

    @Step
    public LaunchesPage clickOnSaveButton() {
        driver.findElement(By.xpath(SAVE_BUTTON)).click();
        return this;
    }

    @Step
    public LaunchesPage nameNewFilter(String filterName) {
        WebElement editFilterNameField = driver.findElement(By.xpath(EDIT_FILTER_NAME_FIELD));
        editFilterNameField.click();
        editFilterNameField.clear();
        editFilterNameField.sendKeys(filterName);
        return this;
    }

    @Step
    public LaunchesPage clickOnAddButton() {
        driver.findElement(By.xpath(ADD_FILTER_BUTTON)).click();
        return this;
    }

    @Step
    public String getFilterValue() {
        new WebDriverWait(driver, Duration.ofSeconds(1000))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ENTER_NAME_FIELD)));
        return getEnterNameField().getAttribute("value");
    }

    @Step
    public boolean isFilterDisplayed(String filterName) {
        String filterXpath = String.format(NEW_FILTER_ICON, filterName);
        return driver.findElement(By.xpath(filterXpath)).isDisplayed();
    }

    private WebElement getEnterNameField() {
        return driver.findElement(By.xpath(ENTER_NAME_FIELD));
    }

}
