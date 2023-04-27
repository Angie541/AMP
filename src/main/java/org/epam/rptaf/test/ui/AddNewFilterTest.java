package org.epam.rptaf.test.ui;

import org.epam.rptaf.business.pageobject.impl.FiltersPage;
import org.epam.rptaf.business.pageobject.impl.LaunchesPage;
import org.epam.rptaf.core.util.ConfigReader;
import org.epam.rptaf.core.util.UiTestUtil;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Iterator;

public class AddNewFilterTest extends UiTestBase {
    @Test(dataProvider = "addingNewFilters")
    public void addNewFilter(String filterValue, String filterName) {
        String[] credentials = ConfigReader.getUserTestData().get(0);
        UiTestUtil.login(driver, credentials[0], credentials[1]);
        FiltersPage filtersPage = new FiltersPage(driver);
        filtersPage.openPage();
        if (filtersPage.isVisible()) {
            filtersPage.addNewFilter();
            LaunchesPage launchesPage = new LaunchesPage(driver);
            if (launchesPage.isVisible()) {
                launchesPage.enterFilterValue(filterValue)
                        .clickOnSaveButton()
                        .nameNewFilter(filterName)
                        .clickOnAddButton();
                SoftAssert softAssert = new SoftAssert();
                softAssert.assertEquals(launchesPage.getFilterValue(), filterValue);
                softAssert.assertTrue(launchesPage.isFilterDisplayed(filterName));
                filtersPage.clickOnFiltersButtonInLeftSidebar().deleteFilter(filterName);
            } else {
                Assert.fail("Launches page layout is not visible");
            }
        } else {
            Assert.fail("Filters page layout is not visible");
        }
    }

    @DataProvider(name = "addingNewFilters")
    public static Iterator<String[]> newFiltersNames() {
        return ConfigReader.getNewFiltersNamesTestData().iterator();
    }

}
