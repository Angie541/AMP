package org.epam.rptaf.test.ui.junit;

import org.epam.rptaf.business.pageobject.impl.FiltersPage;
import org.epam.rptaf.core.util.ConfigReader;
import org.epam.rptaf.core.util.UiTestUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class EditFilterNameTest extends JUnitTestBase {

    private String name;

    public EditFilterNameTest(String name) {
        this.name = name;
    }

    @Parameterized.Parameters
    public static Iterable<String[]> filterNamesForEditing() {
        return ConfigReader.getFilterNamesForEditingTestData();
    }

    @Test
    public void editFilterName() {
        String[] credentials = ConfigReader.getUserTestData().get(0);
        UiTestUtil.login(driver, credentials[0], credentials[1]);
        FiltersPage filtersPage = new FiltersPage(driver);
        filtersPage.openPage();
        if (filtersPage.isVisible()) {
            filtersPage.clickOnEditSpecificFilterButton()
                    .editFilterName(name)
                    .clickOnUpdateFilterChangesButton();
            assertEquals(filtersPage.getFilterName(), name);
        } else {
            Assert.fail("Filters page layout is not visible");
        }
    }

}
