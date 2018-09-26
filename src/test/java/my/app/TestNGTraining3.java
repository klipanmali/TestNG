package my.app;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Parallelization on &#60;suite&#62; tag
 * 
 * 
 * &#64;BeforeMethod and &#64;AfterMethod annotated methods are executed
 * before/after every test execution, like they are executed in parallel too.
 * &#64;Beforemethod of the subclass doesn't have to follow right after the
 * &#64;BeforeMethod of the superclass.</br>
 * The same is valid for &#64;st AfterMethod methods</br>
 * 
 * 
 * When parallelization is achieved via &#60;suite&#62; tag , every method (all
 * of it's invocations) will be executed in its separate thread
 * 
 */
public class TestNGTraining3 extends TestNGBasic {
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

    @Test(invocationCount = 10)
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

    @Test(invocationCount = 5)
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

    @Test(invocationCount = 10)
    public void f3() {
	int instance = getTest3InstanceCount();
	logger.info("test f3, Parallelization");

	beforeSleep(instance);
	try {
	    Thread.sleep(200);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
	aftherSleep(instance);

    }

    @Test(invocationCount = 5)
    public void f4() {
	int instance = getTest4InstanceCount();
	logger.info("test f4, Parallelization");

	beforeSleep(instance);
	try {
	    Thread.sleep(200);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
	aftherSleep(instance);

    }

    private void beforeSleep(int instance) {
	logger.info(String.format("Current thread %s, Training3obj %d, instance %d, going to sleep",
		Thread.currentThread().toString(), objectIdentifier, instance));
    }

    private void aftherSleep(int instance) {
	logger.info(String.format("Current thread %s, Training3obj %d, instance %d, awaken and continuing the job",
		Thread.currentThread().toString(), objectIdentifier, instance));
    }

}
