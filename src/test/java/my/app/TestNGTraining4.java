package my.app;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Parallelization via &#64;Test annotation </br>
 * Not to mess with parallelization via &#60;suite&#62; tag although classes are
 * identical, *suite.xml differs bit.</br>
 * Extension is not an option, methods from superclass annotated with &#64;Test
 * are valid tests in this class too
 * 
 * 
 * 
 * Hmmmm, are test executed by one thread or treads from poolsize?
 *
 */
public class TestNGTraining4 extends TestNGBasic {

    private int testInstanceCount = 0;
    private int test2InstanceCount = 0;
    private int test3InstanceCount = 0;
    private int test4InstanceCount = 0;

    private Logger logger = Logger.getLogger(this.getClass());

    public synchronized int getTestInstanceCount() {
	return ++testInstanceCount;
    }

    public synchronized int getTest2InstanceCount() {
	return ++test2InstanceCount;
    }

    public synchronized int getTest3InstanceCount() {
	return ++test3InstanceCount;
    }

    public synchronized int getTest4InstanceCount() {
	return ++test4InstanceCount;
    }

    @BeforeClass
    public void beforeClass() {
	logger.info("Before Class " + Thread.currentThread());
    }

    @AfterClass
    public void afterClass() {
	logger.info("After Class " + Thread.currentThread());
    }

    @BeforeMethod
    public void BeforeMethod() {
	logger.info("Before Method " + Thread.currentThread());
    }

    @AfterMethod
    public void afterMethod() {
	logger.info("After Method " + Thread.currentThread());
    }

    @Test(threadPoolSize = 3, invocationCount = 10)
    public void f1() {
	int instance = getTestInstanceCount();
	logger.info("test f1, Parallelization");

	beforeSleep(instance);
	try {
	    Thread.sleep(200);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
	aftherSleep(instance);
    }

    @Test(threadPoolSize = 4, invocationCount = 5)
    public void f2() {
	int instance = getTest2InstanceCount();
	logger.info("test f2, Parallelization");

	beforeSleep(instance);
	try {
	    Thread.sleep(200);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
	aftherSleep(instance);

    }

    private void beforeSleep(int instance) {
	logger.info(String.format("Current thread %s, Training4obj %d, instance %d, going to sleep",
		Thread.currentThread().toString(), objectIdentifier, instance));
    }

    private void aftherSleep(int instance) {
	logger.info(String.format("Current thread %s, Training4obj %d, instance %d, awaken and continuing the job",
		Thread.currentThread().toString(), objectIdentifier, instance));
    }

}
