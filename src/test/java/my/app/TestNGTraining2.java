package my.app;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Factories, two instances of this class are created by the factory.<br/>
 * Tests from both instances are executed
 * 
 */
// when class is annotated with @Test, it is the same as all public
// methods are annotated with @Test
@Test
public class TestNGTraining2 extends TestNGBasic {

    // not multithreadding so OK
    private static int instanceCount = 0;

    private int parameter1FromFactory;
    private int parameter2FromFactory;
    private String instanceName;
    private Logger logger = Logger.getLogger(TestNGTraining2.class);

    @BeforeClass
    public void beforeClass() {
	logger.info("Before Class");
    }

    @AfterClass
    public void afterClass() {
	logger.info("After Class");
    }

    @BeforeMethod
    public void BeforeMethod() {
	logger.info("Before Method");
    }

    @AfterMethod
    public void afterMethod() {
	logger.info("After Method");
    }

    public TestNGTraining2(int parameter1, int parameter2) {
	instanceCount++;
	this.parameter1FromFactory = parameter1;
	this.parameter2FromFactory = parameter2;
	instanceName = String.format("%s %d", new Exception("it doesn't matter").getStackTrace()[0].getClassName(),
		instanceCount);
    }

    public void testf() {
	logger.info("test f, TestNG Factory");
	StackTraceElement[] st = new Exception("it doesn't matter").getStackTrace();
	// List<StackTraceElement> stLIst = Arrays.asList(st);
	// System.out.println(stLIst);
	// String methodName = st[1].getMethodName(); // is it 0 or 1, depends
	// on OS
	String methodName = st[0].getMethodName();
	logger.info(String.format("Instance: %s, method: %s, parameter1: %d", instanceName, methodName,
		parameter1FromFactory));
    }

    public void testf2() {
	logger.info("test f2, TestNG Factory");
	class Local {
	}
	String methodName = Local.class.getEnclosingMethod().getName();
	// same thing with anonymous class, all classes extends Object class
	// new Object() {
	// }.getClass().getEnclosingMethod().getName();
	logger.info(String.format("Instance: %s, method: %s, parameter2: %d", instanceName, methodName,
		parameter2FromFactory));
    }

}
