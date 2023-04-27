package org.epam.rptaf.core.runner;

import org.epam.rptaf.test.ui.junit.AddNewFilterTest;
import org.epam.rptaf.test.ui.junit.EditFilterNameTest;
import org.epam.rptaf.test.ui.junit.LoginTest;
import org.junit.experimental.ParallelComputer;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JUnitRunner {

    private static Logger logger = LoggerFactory.getLogger(JUnitRunner.class);

    public static void main(String[] args) {

        JUnitCore junit = new JUnitCore();
        junit.addListener(new TextListener(System.out));

        Result result = junit.run(new ParallelComputer(true, true),
                LoginTest.class,
                EditFilterNameTest.class,
                AddNewFilterTest.class);

        resultReport(result);
    }

    public static void resultReport(Result result) {
        logger.info("Finished. Result: Failures: {}.\nIgnored: {}.\nTests run: {}.\nTime: {} ms.\nTests are failed: {}",
                result.getFailureCount(), result.getIgnoreCount(), result.getRunCount(), result.getRunTime(),
                result.getFailures());
    }
}
