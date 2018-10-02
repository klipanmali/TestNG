package my.app;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * 
 * Another example of dependsOnMethod dependency</br>
 * Hard vs Soft dependency</br>
 * Hard dependency(alwaysRun = true) - execute this test even if test it depends
 * on were failed</br>
 * Soft dependency - execute this test only if test it depends were executed
 * successful
 */
public class TestNGTraining5_1 {
    private Logger logger = Logger.getLogger(this.getClass());
    private Calculator calc = new Calculator();

    @BeforeMethod
    public void beforeMethod() {
	logger.info("Before Method ");
    }

    @AfterMethod
    public void afterMethod() {
	logger.info("After Method ");
    }

    // Soft dependency
    @Test(dependsOnMethods = { "testAdd", "testDivide" })
    public void testProcessRealNumbers() {
	logger.info("testProcessRealNumbers");

    }

    @Test
    public void testAdd() {
	logger.info("tetsAdd");
	Assert.assertEquals(calc.add(5, 10), 15);
    }

    @Test
    public void testDivide() {
	logger.info("tetsDivide");
	Assert.assertEquals(calc.divide(15, 6), 2);
    }

    // Soft dependency
    @Test(dependsOnMethods = { "testAdd", "testDivideFail" })
    public void testSoftDependency() {
	logger.info("testSoftDependency");

    }

    // Hard dependency
    @Test(dependsOnMethods = { "testAdd", "testDivideFail" }, alwaysRun = true)
    public void testHardDependency() {
	logger.info("testHardDependency");

    }

    @Test
    public void testDivideFail() {
	logger.info("testDivideFail");
	Assert.assertEquals(calc.divide(15, 6), 3);
    }
}
