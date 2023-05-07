package org.epam.rptaf.test.ui;

import org.epam.rptaf.business.pageobject.impl.FiltersPage;
import org.epam.rptaf.core.util.ConfigReader;
import org.epam.rptaf.core.util.UiTestUtil;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Iterator;

public class EditFilterNameTest extends UiTestBase {

    @Test(dataProvider = "filterNamesForEditing")
    public void editFilterName(String name) {
        String[] credentials = ConfigReader.getUserTestData().get(0);
        UiTestUtil.login(driver, credentials[0], credentials[1]);
        FiltersPage filtersPage = new FiltersPage(driver);
        filtersPage.openPage();
        if (filtersPage.isVisible()) {
            filtersPage.clickOnEditSpecificFilterButton()
                    .editFilterName(name)
                    .clickOnUpdateFilterChangesButton();
            SoftAssert softAssert = new SoftAssert();
            softAssert.assertEquals(filtersPage.getFilterName(), name);
        } else {
            Assert.fail("Filters page layout is not visible");
        }
    }

    @DataProvider(name = "filterNamesForEditing")
    public static Iterator<String[]> filterNamesForEditing() {
        return ConfigReader.getFilterNamesForEditingTestData().iterator();
    }

}
