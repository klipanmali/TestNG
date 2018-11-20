package my.app;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGTraining14Listener implements ITestListener {

    @Override
    public void onFinish(ITestContext arg0) {
	// TODO Auto-generated method stub

    }

    @Override
    public void onStart(ITestContext arg0) {
	// TODO Auto-generated method stub

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
	// TODO Auto-generated method stub

    }

    @Override
    public void onTestFailure(ITestResult arg0) {
	setDetails("Fail", arg0);
    }

    @Override
    public void onTestSkipped(ITestResult arg0) {
	setDetails("Skip", arg0);
    }

    @Override
    public void onTestStart(ITestResult arg0) {
    }

    @Override
    public void onTestSuccess(ITestResult arg0) {
	setDetails("Succ", arg0);

    }

    public void setDetails(String result, ITestResult testResult) {
	Object currentClass = testResult.getMethod().getInstance();
	TestNGTraining14Base classHelper = ((TestNGTraining14Base) currentClass);

	if (result.equalsIgnoreCase("Skip")) {
	    classHelper.setReporting();
	}

	Reporting reporting = classHelper.getReport();

	reporting.report.put("testStatus", result);

	reporting.report.put("className", testResult.getMethod().getInstance().getClass().getName());

	if (!result.equalsIgnoreCase("Skip")) {
	    reporting.report.put("model", "Something to add for non Sipped tests");
	}

	// Upon failure check that throwable and stacktrace aren't null
	// before collecting the strack trace information otherwise
	// a null pointer exception will be thrown
	if (result.equalsIgnoreCase("Fail")) {
	    if (testResult.getThrowable() != null)
		if (testResult.getThrowable().getStackTrace() != null) {
		    StringWriter sw = new StringWriter();
		    testResult.getThrowable().printStackTrace(new PrintWriter(sw));
		    reporting.report.put("stackTrace", sw.toString());
		}
	}

	reporting.report.put("testName", testResult.getTestContext().getName());
	reporting.report.put("methodName", testResult.getMethod().getMethodName());

	///////////////////////////////////////////////////////////////////////
	// if (result.equalsIgnoreCase("Skip")) {
	// do something here with the reporting info as afterMethod won't
	// be called
	// }
	//////////////////////////////////////////////////////////////////////
    }
}
