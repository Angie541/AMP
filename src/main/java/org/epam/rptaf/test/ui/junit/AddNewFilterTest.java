package org.epam.rptaf.test.ui.junit;

import org.epam.rptaf.business.pageobject.impl.FiltersPage;
import org.epam.rptaf.business.pageobject.impl.LaunchesPage;
import org.epam.rptaf.core.util.ConfigReader;
import org.epam.rptaf.core.util.UiTestUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class AddNewFilterTest extends JUnitTestBase{

    private String filterValue;
    private String filterName;

    public AddNewFilterTest(String filterValue, String filterName) {
        this.filterValue = filterValue;
        this.filterName = filterName;
    }

    @Parameterized.Parameters
    public static Iterable<String[]> newFiltersNames() {
        return ConfigReader.getNewFiltersNamesTestData();
    }

    @Test
    public void addNewFilter() {
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
                assertEquals(launchesPage.getFilterValue(), filterValue);
                assertTrue(launchesPage.isFilterDisplayed(filterName));
                filtersPage.clickOnFiltersButtonInLeftSidebar().deleteFilter(filterName);
            } else {
                Assert.fail("Launches page layout is not visible");
            }
        } else {
            Assert.fail("Filters page layout is not visible");
        }
    }

}
