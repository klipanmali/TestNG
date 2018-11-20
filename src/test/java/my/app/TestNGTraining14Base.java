package my.app;

import java.lang.reflect.Method;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestNGTraining14Base {

    public void setReporting() {
	ReportingManager.setReporting(ReportingFactory.createReportingInstance());
    }

    public Reporting getReport() {
	return ReportingManager.getReporting();
    }

    @BeforeMethod
    public void beforeMethod(Method method) {
	setReporting();
	getReport().setTestExecutionStar(System.currentTimeMillis());
	getReport().report.put("threadNo:", Thread.currentThread().getName() + " " + Thread.currentThread().getId());
    }

    @AfterMethod
    public void afterMethod(Method method, ITestResult testResult, ITestContext testContext) {
	getReport().report.put("EndOfTest", null);

	/////////////////////////////////////////////////////////////////////
	// do something here with the reporting data, pass it wherever you wish:
	// database, excel, splunk, etc
	/////////////////////////////////////////////////////////////////////
	System.out.println(getReport().toString());
    }
}
