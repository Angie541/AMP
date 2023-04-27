package org.epam.rptaf.business.pageobject.impl;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DashboardPage extends AbstractBasePage {
    private static final String DASHBOARD_PAGE_LAYOUT = "//div[@class='pageLayout__page-content--2R36V']";

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    @Step
    public boolean isVisible() {
        WebElement layout = new WebDriverWait(driver, Duration.ofSeconds(100))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DASHBOARD_PAGE_LAYOUT)));
        return layout.isDisplayed();
    }

}
