package my.app;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Properties on method </br>
 * Properties on constructor</br>
 * Simple data provider</br>
 * Data provider from another class</br>
 * Data provider from inherited class</br>
 * Data Provider Dependency injection - method</br>
 * Data Provider Dependency injection - Context</br>
 * Data Provider Dependency injection - method + Context</br>
 * 
 * @author etkhrto
 *
 */
public class TestNGTraining extends TestNGBasic {
    private Integer timeout;

    // private Logger logger = Logger.getLogger(TestNGTraining.class.getName());
    private Logger logger = Logger.getLogger(TestNGTraining.class);

    @BeforeClass
    public void beforeCLass() {
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

    // If Parameters are provided to constructor, this constructor will be used
    // Parameters can be provided on at most one constructor
    @Parameters("timeout")
    public TestNGTraining(@Optional("2000") Integer timeout) {
	super();
	this.timeout = timeout;
    }

    // Parameters can be provided on any method that has @Test, @Before/@After
    // @Factory
    // Parameters are mapped to Java Parameters in the same order as specified
    // in annotation
    @Parameters({ "lname", "fname" })
    @Test
    public Integer f(@Optional("OIME") String fname, @Optional("OPREZIME") String lname) {
	logger.info("TestNGTraining, test f, optional parameters");
	logger.info("First name:" + fname + ", Last name:" + lname);
	logger.info("Timeout value :" + timeout);// parameter from the
						 // constructor
	return new Integer(1);
    }

    @Test(dataProvider = "dataProvider1")
    public void f2(String str, Integer i) {
	logger.info("TestNGTraining, test f2, simple data provider");
	logger.info(str + " " + i);

    }

    // DataProvidedr in another class
    @Test(dataProvider = "dataProvider2", dataProviderClass = DataProvider2.class)
    public void f3(String str, Integer i) {
	logger.info("TestNGTraining, test f3, data provider in anoter class");
	logger.info(str + " " + i);

    }

    // Data Provider form the inherited class
    // Data Provider can be referenced by method name only
    @Test(dataProvider = "createDataFromBase")
    public void f4(String str, Integer i) {
	logger.info("TestNGTraining, test f4, data provider form inhereted class");
	logger.info(str + " " + i);
    }

    // DataProvider dependency injection method
    @Test(dataProvider = "createDataMethod")
    public void f5(String method) {
	logger.info("TestNGTraining, test f5, dependency injection - Method");
	logger.info("Method " + method);
    }

    // DataProvider dependency injection Test Context
    @Test(dataProvider = "createDataContext")
    public void f6(String parameterName, String parameterValue) {
	logger.info("TestNGTraining, test f6, dependency injection - Context");
	logger.info("Parameter name  = " + parameterName + " ; parameter value  = " + parameterValue);
    }

    // DataProvider dependency injection Test Context and Method
    @Test(dataProvider = "createDataContextMethod")
    public void f7(String contParamValue, String method) {
	logger.info("TestNGTraining, test f7, dependency inhection - Method and Context");
	logger.info("Method = " + method + " ; Parameter value  = " + contParamValue);
    }

    @DataProvider(name = "dataProvider1")
    public Object[][] createData() {
	return new Object[][] { { "Ivo", new Integer(30) }, { "Mara", new Integer(33) }, };
    }

    // if "name" attribute not specified, it is equal to method name
    // executing method as argument
    @DataProvider()
    public Object[][] createDataMethod(Method m) {

	return new Object[][] { new Object[] { m.getName() } };
    }

    // Test Context as argument
    @DataProvider()
    public Object[][] createDataContext(ITestContext context) {
	ArrayList<Object[]> resultArray = new ArrayList<>();
	Map<String, String> parameters = context.getCurrentXmlTest().getAllParameters();
	Set<Map.Entry<String, String>> parameterSet = parameters.entrySet();

	Object[][] result = new Object[parameterSet.size()][2];
	int index = 0;
	for (Map.Entry<String, String> parameter : parameterSet) {
	    result[index] = new Object[] { parameter.getKey(), parameter.getValue() };
	    index++;
	}
	return result;
	// for (Map.Entry<String, String> parameter : parameterSet) {
	// result.add(new Object[] { parameter.getKey(), parameter.getValue()
	// });
	// }

	// return (Object[][]) result.toArray(new Object[result.size()]);
    }

    // MEthod and Test Context provided as arguments
    @DataProvider()
    public Object[][] createDataContextMethod(ITestContext context, Method m) {
	return new Object[][] { { context.getCurrentXmlTest().getParameter("contextPrameter"), m.getName() } };
    }

}
