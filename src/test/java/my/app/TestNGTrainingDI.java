package my.app;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.NoInjection;
import org.testng.annotations.Test;

/**
 * TestNG lets you declare additional parameters in your methods. TestNG will
 * automatically fill these parameters with the right value.</br>
 * 
 * <pre>
 * Annotattion  | ITestContext | XmlTest | Method | Object[] | ITestResult
 * BeforeSuite  |    Y             N         N         N            N
 * BeforeTest   |    Y             Y         N         N            N
 * BeforeGroups |    Y             Y         N         N            N
 * BeforeClass  |    Y             Y         N         N            N
 * BeforMethod  |    Y             Y         Y         Y            Y
 * Test         |    Y             N        ???        N            N
 * DatProvider  |    Y             N         Y         N            N
 * AfterMethod  |    Y             Y         Y         Y            Y
 * AfterClass   |    Y             Y         N         N            N
 * AfterGroups  |    Y             Y         N         N            N
 * AfterTest    |    Y             Y         N         N            N
 * AfterSuite   |    Y             N         N         N            N
 * 
 * It looks like Test can have Method dependency too
 * </pre>
 * 
 * </br>
 * ITestREsult: reflects the result of the test method that was just run.</br>
 * XmlTest: contains the current &#60;test&#62; tag.</br>
 * Method: receives the test method that will be/was called.</br>
 * Object[]: receives the list of parameters that are about to be fed to the
 * upcoming test method, which could be injected by TestBG, such as
 * java.lang.reflect.Method, or come from a DataProvider</br>
 * </br>
 * You can turn off injection with the NoInjection annotation.</br>
 * </br>
 * 
 */

public class TestNGTrainingDI {

    public void f() {
    }

    @DataProvider
    public Object[][] provide() throws Exception {
	return new Object[][] { { TestNGTrainingDI.class.getMethod("f") } };
    }

    @Test(dataProvider = "provide")
    public void withoutInjection(@NoInjection Method m) { // Method provided by
							  // DataProvider
	Assert.assertEquals(m.getName(), "f");
    }

    @Test(dataProvider = "provide")
    public void withInjection(Method m) { // Method by DI, overrides
					  // DataProvider
	Assert.assertEquals(m.getName(), "withInjection");
    }

    @Test(dataProvider = "provide")
    public void withInjection2(Method m) {// Method by DI, overrides
					  // DataProvider
	Assert.assertEquals(m.getName(), "f");
    }

}
