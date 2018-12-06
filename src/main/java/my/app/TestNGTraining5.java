package my.app;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Dependences - method version</br>
 * 
 * Dependent method must be &#64;Test annotated method and must be enabled.</br>
 * Also 'dependsOnMethods' in any &#64;Before or &#64;After annotated method
 * doesn't work. It looks like 'dependsOnMethod is reserved for &#64;Test
 * annotated methods.</br>
 * Same thing can be accomplished by using 'dependsOnGroups' On using groups, we
 * are no longer exposed to refactoring problems as when using
 * 'dependsOnMethods'
 *
 */
public class TestNGTraining5 extends TestNGBasic {

    private Logger logger = Logger.getLogger(this.getClass());

    @BeforeClass
    public void beforeClass() {
	logger.info("Before Class ");
    }

    @AfterClass
    public void afterClass() {
	logger.info("After Class ");
    }

    @BeforeMethod
    public void beforeMethod() {
	logger.info("Before Method ");
    }

    // this doesn't work, dependent method must be @Test annotated and must be
    // enable=true
    // @AfterMethod(dependsOnMethods = { "beforeMethod" })
    // this dosen't work either even if "f2" is the only test method
    // @AfterMethod(dependsOnMethods = { "f2" })
    @AfterMethod
    public void afterMethod() {
	logger.info("After Method ");
    }

    public void f0notTestMethod() {
	logger.info("test f1, Method dependency");
    }

    @Test(dependsOnMethods = { "f2" })
    public void f1() {
	logger.info("test f1, Method dependency");
    }

    // this doesn't work, dependent method must be @Test annotated and must be
    // enable=true
    // @Test(dependsOnMethods = { "afterMethod" })
    // @Test(dependsOnMethods = { "f0notTestMethod" })
    @Test
    public void f2() {
	logger.info("test f2, Method dependency");
    }
}
