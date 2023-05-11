package org.epam.rptaf.business.stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.epam.rptaf.business.pageobject.impl.DashboardPage;
import org.epam.rptaf.business.pageobject.impl.LoginPage;

import org.epam.rptaf.core.util.UiTestUtil;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginPageDefinitions {
    private final LoginPage loginPage;
    private final DashboardPage dashboardPage;

    public LoginPageDefinitions() {
        WebDriver driver = UiTestUtil.getNewDriver();
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
    }

    @Given("Anonymous user opens Login page")
    public void openLoginPage() {
        loginPage.openPage().loginFormIsVisible();
    }

    @When("Anonymous user logins with {string} login and {string} password")
    public void userLogins(String login, String password) {
        loginPage.enterLogin(login)
                .enterPassword(password)
                .clickLoginButton();
    }

    @Then("Registered user is redirected to Dashboard page")
    public void dashboardPageIsVisible() {
        Assert.assertTrue(dashboardPage.isVisible(), "Dashboard page layout is not visible");
    }

}
