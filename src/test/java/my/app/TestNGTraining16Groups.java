package my.app;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestNGTraining16Groups {

    @BeforeSuite
    public void thidngsToDoBeforeSuite1() {
	System.out.println("Before Suite - General");
    }

    @BeforeSuite(groups = { "g1" })
    public void thidngsToDoBeforeSuite2() {
	System.out.println("Before Suite - G1");
    }

    @AfterSuite
    public void thidngsToDoAfterSuite1() {
	System.out.println("After Suite - General");
    }

    @AfterSuite(groups = { "g1" })
    public void thidngsToDoAfterSuite2() {
	System.out.println("After Suite - G1");
    }

    @BeforeTest
    public void thidngsToDoBeforeTest1() {
	System.out.println("Before Test - General");
    }

    @BeforeTest(groups = { "g2" })
    public void thidngsToDoBeforeTest2() {
	System.out.println("Before Test - G2");
    }

    @AfterTest
    public void thidngsToDoAfterTest1() {
	System.out.println("After Test - General");
    }

    @AfterTest(groups = { "g2" })
    public void thidngsToDoAfterTest2() {
	System.out.println("After Test - G2");
    }

    @BeforeClass
    public void thidngsToDoBeforeClass1() {
	System.out.println("Before Class - General");
    }

    @BeforeClass(groups = { "g1" })
    public void thidngsToDoBeforeClass2() {
	System.out.println("Before Class - G1");
    }

    @AfterClass
    public void thidngsToDoAfterClass1() {
	System.out.println("After Class - General");
    }

    @AfterClass(groups = { "g1" })
    public void thidngsToDoAfterClass2() {
	System.out.println("After Class - G1");
    }

    @BeforeMethod
    public void thidngsToDoBeforeMethod1() {
	System.out.println("Before Method - General");
    }

    @BeforeMethod(groups = { "g2" })
    public void thidngsToDoBeforeMethod2() {
	System.out.println("Before Method - G2");
    }

    @AfterMethod
    public void thidngsToDoAfterMethod1() {
	System.out.println("After Method - General");
    }

    @AfterMethod(groups = { "g2" })
    public void thidngsToDoAfterMethod2() {
	System.out.println("After Method - G2");
    }

    // @Test(dependsOnGroups = { "g1", "g2" }) // No @Test with these groups
    // ?????
    @Test(groups = { "g1", "g2" })
    public void test1() {
	System.out.println("Test1 execution");
    }

    // @Test(dependsOnGroups = { "g1", "g2" }) // No @Test with these groups
    // ?????
    @Test
    public void test2() {
	System.out.println("Test2 execution");
    }

    // @Test(dependsOnGroups = { "g1", "g2" }) // No @Test with these groups
    // ?????
    @Test
    public void test3() {
	System.out.println("Test3 execution");
    }

    // @Test(dependsOnGroups = { "g1", "g2" }) // No @Test with these groups
    // ?????
    @Test
    public void test4() {
	System.out.println("Test4 execution");
    }

}
