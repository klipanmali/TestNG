package my.app;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

/**
 * 
 * There are several "TestNG Listeners" that allow you to modify TestNG's
 * behavior:</br>
 * IAnnotationTransformer</br>
 * IAnnotationTransformer2</br>
 * IAnnotationTransformer3</br>
 * IHookable</br>
 * IInvokedMethodListener</br>
 * IMethodInterceptor</br>
 * IReporter</br>
 * ISuiteListener</br>
 * ITestListener</br>
 * </br>
 * ISuite.getAttributeNames didn't return suite's attributes
 */
public class MyMultiListener implements ISuiteListener, ITestListener, IInvokedMethodListener {

    Logger logger = Logger.getLogger(this.getClass());

    @Override
    public void afterInvocation(IInvokedMethod invokedMethod, ITestResult testResult) {
	logger.info("Method " + invokedMethod.getTestMethod().getMethodName() + " result is " + testResult.getStatus());
    }

    @Override
    public void beforeInvocation(IInvokedMethod invokedMethod, ITestResult testResult) {
	logger.info("Method " + invokedMethod.getTestMethod().getMethodName() + " invoked");
    }

    @Override
    public void onFinish(ITestContext arg0) {
	// TODO Auto-generated method stub

    }

    @Override
    public void onStart(ITestContext testContext) {
	logger.info("Yeaah, class is instantiated, test is about to start");

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult testResult) {

    }

    @Override
    public void onTestFailure(ITestResult testResult) {
	logger.info("Test " + testResult.getMethod().getMethodName() + " failed ");

    }

    @Override
    public void onTestSkipped(ITestResult testResult) {
	logger.info("Test " + testResult.getMethod().getMethodName() + " skipped ");
    }

    @Override
    public void onTestStart(ITestResult testResult) {
	logger.info("Test " + testResult.getMethod().getMethodName() + " is about to start");
    }

    @Override
    public void onTestSuccess(ITestResult testResult) {
	logger.info("Test " + testResult.getMethod().getMethodName() + " Success ");
    }

    @Override
    public void onFinish(ISuite suite) {
	logger.info("Suite test ended");
	System.out.println("Invoked methods:");
	List<IInvokedMethod> invokedMethods = suite.getAllInvokedMethods();
	for (IInvokedMethod invokedMethod : invokedMethods) {
	    System.out.println(invokedMethod.getTestMethod().getMethodName());
	}
	System.out.println("Excluded methods:");
	Collection<ITestNGMethod> excludedMethods = suite.getExcludedMethods();
	for (ITestNGMethod excludedMethod : excludedMethods) {
	    System.out.println(excludedMethod.getMethodName());
	}

    }

    @Override
    public void onStart(ISuite suite) {
	Set<String> attributes = suite.getAttributeNames();
	if (attributes.isEmpty())
	    logger.info("No attributes in suite");
	logger.info("Starting new suite with following attributes:");
	for (String attribute : attributes) {
	    System.out.println(String.format("Atrribute: %s, value: %s", attribute, suite.getAttribute(attribute)));
	}
    }

}
