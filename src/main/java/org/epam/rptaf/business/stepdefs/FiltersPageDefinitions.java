package org.epam.rptaf.business.stepdefs;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.epam.rptaf.business.pageobject.impl.FiltersPage;
import org.epam.rptaf.business.pageobject.impl.LaunchesPage;
import org.epam.rptaf.core.util.TestContext;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

import static org.epam.rptaf.core.enums.TestContextKey.*;

public class FiltersPageDefinitions {
    private final LaunchesPage launchesPage;
    private final FiltersPage filtersPage;
    private String filterValue;
    private String filterName;

    public FiltersPageDefinitions() {
        WebDriver driver = TestContext.getDriver();
        launchesPage = new LaunchesPage(driver);
        filtersPage = new FiltersPage(driver);
    }

    @When("Registered user opens Filters page")
    public void userOpensFiltersPage() {
        filtersPage.openPage();
        Assert.assertTrue(filtersPage.isVisible(), "Filters page layout is not visible");
    }

    @Then("Registered user edits the filter name to a new one:")
    public void userEditsFilterName(final List<String> names) {
        names.forEach(name ->
                filtersPage.clickOnEditSpecificFilterButton()
                        .editFilterName(name)
                        .clickOnUpdateFilterChangesButton().shouldHave(name));
    }

    @When("Registered user clicks on 'Add new filter' button")
    public void userClicksOnAddFilterButton() {
        filtersPage.addNewFilter();
        Assert.assertTrue(launchesPage.isVisible(), "Launches page layout is not visible");
    }

    @And("Registered user adds new filter with:")
    public void userAddsNewFilter(final DataTable dataTable) {
        for (Map<String, String> row : dataTable.asMaps()) {
            filterValue = row.get("filterValue");
            filterName = row.get("filterName");
            launchesPage.enterFilterValue(filterValue)
                    .clickOnSaveButton()
                    .nameNewFilter(filterName)
                    .clickOnAddButton();
        }
    }

    @Then("Registered user sees new filter")
    public void userSeesNewFilter() {
        Assert.assertEquals(launchesPage.getFilterValue(), filterValue);
        Assert.assertTrue(launchesPage.isFilterDisplayed(filterName));
    }

    @And("Registered user deletes filter")
    public void userDeletesFilter() {
        filtersPage.clickOnFiltersButtonInLeftSidebar().deleteFilter(filterName);
    }

}
