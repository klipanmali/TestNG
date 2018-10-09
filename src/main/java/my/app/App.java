package my.app;

import java.util.ArrayList;
import java.util.List;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

/**
 * Hello world!
 *
 * Programmatically TestNG execution </br>
 * Programmatically test execution via virtual XML</br>
 */
public class App {
    public static void main(String[] args) {
	System.out.println("Hello World!");

	// Running TestNG programmatically

	System.out.println();
	System.out.println("Programmatically execution");
	System.out.println();

	TestListenerAdapter tla = new TestListenerAdapter();
	TestNG testng = new TestNG();
	testng.setTestClasses(new Class[] { TestNGTraining5.class, TestNGTraining6.class });
	testng.addListener(tla);
	testng.run();
	List<ITestResult> passedTests = tla.getPassedTests();
	System.out.println("Test Execution: ");
	passedTests.forEach(tResult -> {
	    System.out.println("Test: " + tResult.getName());
	    System.out.println("Status: " + tResult.getStatus());
	});

	System.out.println();
	System.out.println("Programmatically execution via virtual XML");
	System.out.println();

	XmlSuite suite = new XmlSuite();
	suite.setName("Virtual XML suite");

	XmlTest test = new XmlTest(suite);
	test.setName("Testing 5");
	List<XmlClass> classes = new ArrayList<XmlClass>();
	classes.add(new XmlClass(TestNGTraining5.class.getCanonicalName()));
	test.setXmlClasses(classes);

	test = new XmlTest(suite);
	test.setName("Testing 6");
	classes = new ArrayList<XmlClass>();
	classes.add(new XmlClass(TestNGTraining6.class.getCanonicalName()));
	test.setXmlClasses(classes);

	List<XmlSuite> suites = new ArrayList<XmlSuite>();
	suites.add(suite);
	tla = new TestListenerAdapter();
	testng = new TestNG();
	testng.setXmlSuites(suites);
	testng.addListener(tla);
	testng.run();
	System.out.println("Test Execution: ");
	passedTests.forEach(tResult -> {
	    System.out.println("Test: " + tResult.getName());
	    System.out.println("Status: " + tResult.getStatus());
	});

    }
}
