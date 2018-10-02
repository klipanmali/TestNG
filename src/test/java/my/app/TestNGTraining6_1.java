package my.app;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Different cases of using dependsOnGroup</br>
 * <code>
 * https://stackoverflow.com/questions/31476604/testng-all-subsequent-test-classes-are-skipped-when-beforeclass-method-fails</code>
 * </br>
 * Try to add &#64;AfterClass(alwaysrun = true) or/and
 * &#64;AfterMethod(alwaysrun=true) as by default they are skipped if either
 * BeforeClass or BeforeMethod are not completed.</br>
 * <code>
 * https://groups.google.com/forum/#!topic/testng-users/qAsdC7BtbUM</code></br>
 * A failed &#64;BeforeClass should indeed cause all the tests in that class to
 * be skipped. This appears to work:</br>
 * <code>
 * https://developers.perfectomobile.com/display/TT/testNG%2BConfiguration%2BFailures,%2BPolicy,%2Band%2BalwaysRun</code>
 * </br>
 * When configuration failures occur testNG's default behavior is to then skip
 * every after listener down the line. For instance if your exception occurred
 * in beforeClass or beforeMethod then afterMethod and afterClass will be
 * skipped. You can solve the issue of subsequent after listeners not being
 * executed by adding the attribute alwaysRun=true to the annotation of the
 * listeners. This could be useful if you are collecting reporting information
 * during your execution and your rely on these listeners to set some data for
 * your reporting.</br>
 * Remember to create flags to verify if any additional cleanup should be
 * performed in the event that the before listeners DID fail. For instance if
 * the driver was never created calling driver quit could throw another
 * exception in your afterMethod execution.</br>
 * Should you add alwaysRun to your before listeners?</br>
 * If you are adding group names to your methods you probably should but that is
 * entirely up to you. When adding alwaysRun to before listeners it tells the
 * listener that it is ok to run even if it doesn't contain the group name for
 * the test specified in your testng.xml suite.</br>
 * If you wish to have a different set of before listeners which perform
 * different behaviors based on the group name then you shouldn't use
 * alwaysRun.</br>
 */

public class TestNGTraining6_1 extends TestNGBasic {

    private Logger logger = Logger.getLogger(this.getClass());

    @BeforeClass(groups = "beforeClassA")
    public void beforeClassA() {
	logger.info("Before Class A");
    }

    // if your exception occurred in beforeClass or beforeMethod then
    // afterMethod and afterClass will be skipped
    // For after methods (afterSuite, afterClass, ...):
    // If set to true, this configuration method will be run even if one or more
    // methods invoked previously failed
    // or was skipped
    @AfterClass(groups = "afterClassA", dependsOnGroups = "beforeClassA")
    public void afterClassA() {
	logger.info("After Class A");
    }

    @BeforeClass(groups = "beforeClassB")
    public void beforeClassB() {
	logger.info("Before Class B");
	Assert.assertTrue(false);
    }

    @AfterClass(groups = "afterClassB", dependsOnGroups = "beforeClassB")
    public void afterClassB() {
	logger.info("After Class B");
    }

    @BeforeMethod(groups = "beforeMethod1", dependsOnGroups = "beforeClassA")
    public void beforeMethod1() {
	logger.info("Before Method 1");
    }

    @AfterMethod(groups = "aftehMethod1", dependsOnGroups = "beforeMethod1")
    public void afterMethod1() {
	logger.info("After Method 1");
    }

    @BeforeMethod(groups = "beforeMethod2", dependsOnGroups = { "beforeClassA", "beforeClassB" })
    public void beforeMethod2() {
	logger.info("Before Method 2");
    }

    @AfterMethod(groups = "aftehMethod2", dependsOnGroups = "beforeMethod2")
    public void afterMethod2() {
	logger.info("After Method 2");
    }

    @BeforeMethod(groups = "beforeMethod3", dependsOnGroups = { "beforeClassA", "beforeClassB" }, alwaysRun = true)
    public void beforeMethod3() {
	logger.info("Before Method 3");
    }

    @AfterMethod(groups = "aftehMethod3", dependsOnGroups = "beforeMethod3")
    public void afterMethod3() {
	logger.info("After Method 3");
    }

    @BeforeMethod(groups = "beforeMethod4", dependsOnGroups = { "beforeClassA", "beforeClassB" }, alwaysRun = true)
    public void beforeMethod4() {
	logger.info("Before Method 4");
	// throw new RuntimeException();
	Assert.assertTrue(false);
    }

    @AfterMethod(groups = "aftehMethod4", dependsOnGroups = "beforeMethod4")
    public void afterMethod4() {
	logger.info("After Method 4");
    }

    // @Test(dependsOnGroups = { "beforeMethod1", "beforeMethod2" })
    @Test
    public void f1() {
	logger.info("Testting Method f1()");
    }

    // @Test(dependsOnGroups = { "beforeMethod1", "beforeMethod3" })
    @Test
    public void f2() {
	logger.info("Testting Method f2()");
    }

    // @Test(dependsOnGroups = { "beforeMethod1", "beforeMethod4" })
    @Test
    public void f3() {
	logger.info("Testting Method f3()");
    }

}
