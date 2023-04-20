package org.epam.rptaf.core.runner;

import org.epam.rptaf.test.ui.BasicTest;
import org.testng.TestNG;
import org.testng.xml.XmlSuite;

public class CliRunner {
    public static void main(String[] args) {
        TestNG testngRunner = new TestNG();
        //To run tests in Parallel mode
        testngRunner.setParallel(XmlSuite.ParallelMode.METHODS);
        //Provide the list of test classes
        testngRunner.setTestClasses(new Class[]{
                BasicTest.class
        });
        //Run tests
        testngRunner.run();
    }
}