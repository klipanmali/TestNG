package my.app;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

public class TestNGTraining13Listener implements IInvokedMethodListener {

    @Override
    public void afterInvocation(IInvokedMethod arg0, ITestResult arg1) {
    }

    @Override
    public void beforeInvocation(IInvokedMethod arg0, ITestResult arg1) {
	String executingMethod = arg0.getTestMethod().getMethodName();
	System.out.println("Executing method: " + executingMethod);
	Object currentClassFromTestResult = arg1.getInstance();
	System.out.println(
		"Executing class from ITestResult: " + currentClassFromTestResult.getClass().getCanonicalName());
	Object currentClassFormITestNGMethod = arg1.getMethod().getInstance();
	System.out.println(
		"Executing class from ITestNGMethod: " + currentClassFormITestNGMethod.getClass().getCanonicalName());
    }

}
