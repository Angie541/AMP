package org.epam.rptaf.business.pageobject.impl;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractBasePage {
    public WebDriver driver;

    public AbstractBasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }
}
