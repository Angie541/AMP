package org.epam.rptaf.business.pageobject.impl;

import io.qameta.allure.Step;
import org.epam.rptaf.core.util.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FiltersPage extends AbstractBasePage {
    private static final String PAGE_URL = ConfigReader.getTestData("filters_page_url");
    private static final String FILTERS_PAGE_LAYOUT = "//div[@class='pageLayout__page-content--2R36V']";
    private static final String FILTERS_LEFT_SIDEBAR_BUTTON = "//a[@href='#filters_project/filters']";
    private static final String SPECIFIC_FILTER_NAME = "//*[@href='#filters_project/launches/34']";
    private static final String PENCIL_FOR_SPECIFIC_FILTER = SPECIFIC_FILTER_NAME + "/following-sibling::span";
    private static final String EDIT_FILTER_NAME_FIELD = "//input[@placeholder='Enter filter name']";
    private static final String UPDATE_FILTER_BUTTON = "//button[text()='Update']";
    private static final String ADD_FILTER_BUTTON = "//*[text()='Add Filter']";
    private static final String BIN_ICON_FOR_FILTER = "//*[text()='%s']/../../../..//div[@class='deleteFilterButton__bin-icon--3aWi9']";
    private static final String DELETE_FILTER_BUTTON = "//button[text()='Delete']";

    public FiltersPage(WebDriver driver) {
        super(driver);
    }

    @Step
    public FiltersPage openPage() {
        driver.get(PAGE_URL);
        return this;
    }

    @Step
    public boolean isVisible() {
        WebElement layout = new WebDriverWait(driver, Duration.ofSeconds(100))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(FILTERS_PAGE_LAYOUT)));
        return layout.isDisplayed();
    }

    @Step
    public FiltersPage clickOnFiltersButtonInLeftSidebar() {
        driver.findElement(By.xpath(FILTERS_LEFT_SIDEBAR_BUTTON)).click();
        return this;
    }

    @Step
    public FiltersPage clickOnEditSpecificFilterButton() {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath(SPECIFIC_FILTER_NAME))).perform();
        driver.findElement(By.xpath(PENCIL_FOR_SPECIFIC_FILTER)).click();
        new WebDriverWait(driver, Duration.ofSeconds(1000))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(EDIT_FILTER_NAME_FIELD)));
        return this;
    }

    @Step
    public FiltersPage editFilterName(String name) {
        WebElement editFilterNameField = driver.findElement(By.xpath(EDIT_FILTER_NAME_FIELD));
        editFilterNameField.click();
        editFilterNameField.clear();
        editFilterNameField.sendKeys(name);
        return this;
    }

    @Step
    public FiltersPage clickOnUpdateFilterChangesButton() {
        driver.findElement(By.xpath(UPDATE_FILTER_BUTTON)).click();
        return this;
    }

    @Step
    public FiltersPage getFilterName() {
        new WebDriverWait(driver, Duration.ofSeconds(1000))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SPECIFIC_FILTER_NAME)));
        driver.findElement(By.xpath(SPECIFIC_FILTER_NAME)).getText();
        return this;
    }

    @Step
    public void addNewFilter() {
        driver.findElement(By.xpath(ADD_FILTER_BUTTON)).click();
    }

    @Step
    public void deleteFilter(String filterName) {
        String filterXpath = String.format(BIN_ICON_FOR_FILTER, filterName);
        driver.findElement(By.xpath(filterXpath)).click();
        driver.findElement(By.xpath(DELETE_FILTER_BUTTON)).click();
    }

}
