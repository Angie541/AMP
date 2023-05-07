package org.epam.rptaf.core.runner;

import org.epam.rptaf.test.ui.AddNewFilterTest;
import org.epam.rptaf.test.ui.EditFilterNameTest;
import org.epam.rptaf.test.ui.LoginTest;
import org.testng.TestNG;
import org.testng.xml.XmlSuite;

public class TestNgRunner {
    public static void main(String[] args) {
        TestNG testngRunner = new TestNG();
        //To run tests in Parallel mode in 4 threads
        testngRunner.setParallel(XmlSuite.ParallelMode.CLASSES);
        testngRunner.setThreadCount(4);
        //Provide the list of test classes
        testngRunner.setTestClasses(new Class[]{
                LoginTest.class,
                EditFilterNameTest.class,
                AddNewFilterTest.class
        });
        //Run tests
        testngRunner.run();
    }
}