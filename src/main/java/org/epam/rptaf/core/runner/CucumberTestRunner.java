package org.epam.rptaf.core.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(tags = "",
        features = {"src/main/resources/features"},
        glue = {"org/epam/rptaf/business/stepdefs"},
        plugin = {"pretty", "json:target/cucumber-json/cucumber.json", "html:target/cucumber-reports"},
        monochrome = true)

public class CucumberTestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

}
