package my.app;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/**
 * 
 * Hard vs Soft Assert
 * 
 * Hard Assert throws an AssertExceptionimmediately when an assert statement
 * fails and test suite continues with next @Test. Since It marks method as fail
 * if assert condition gets failed and the remaining statements inside the
 * method will be aborted.
 * 
 * Soft Assert collects errors during @Test is running. They don't throw an
 * exception when an assert fails. The execution will continue with the next
 * step after the assert statement. Using assertAll() at the end it will check
 * if any of the asserts during the test failed, and will fail your test with
 * proper summary.
 */
public class TestNGTraining15Asserts {

    @BeforeMethod
    public void before() {
	System.out.println("Something to do before Test");
    }

    @AfterMethod
    public void after() {
	System.out.println("Something to do after Test");
    }

    // This should be Hard Assert
    @Test
    public void f1() {
	System.out.println("Test f1 should pass");
	Assert.assertTrue(true);
	Assert.assertFalse(false);
	System.out.println("End of Test f1");
    }

    // This should be Hard Assert
    @Test
    public void f2() {
	System.out.println("Test f2 should fail");
	Assert.assertTrue(false);
	Assert.assertFalse(true);
	System.out.println("End of Test f2");
    }

    // This should be Soft Assert
    @Test
    public void f3() {
	System.out.println("Test f3 should fail");
	SoftAssert sa = new SoftAssert();
	sa.assertTrue(false);
	sa.assertFalse(true);
	// the asserts used till now will not throw any exception if they fail.
	// The @Test will not fail either.
	System.out.println("End of Test f3");
    }

    // This should be Soft Assert
    @Test
    public void f4() {
	System.out.println("Test f4 should fail");
	SoftAssert sa = new SoftAssert();
	sa.assertTrue(false);
	sa.assertFalse(true);
	// the asserts used till now will not throw any exception if they fail.
	// The @Test will not fail either.
	// If you need the test method to fail at the end, showing all
	// exceptions, you need to use assertAll()
	sa.assertAll();
	System.out.println("End of Test f4");
    }

}
