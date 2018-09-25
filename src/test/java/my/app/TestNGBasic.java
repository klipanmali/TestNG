package my.app;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

public class TestNGBasic {
    // private Logger logger = Logger.getLogger(TestNGBasic.class.getName());
    private Logger logger = Logger.getLogger(TestNGBasic.class);

    /**
     * For @Before(@After) methods from the superclass to be executed
     * before(after) methods from the subclass, method naming must be different
     * in superclass and subclass. If method naming is the same, only methods
     * from subclass are executed
     */
    @BeforeClass
    public void beforeBasicClass() {
	logger.info("Before Class");
    }

    @AfterClass
    public void afterBasicClass() {
	logger.info("After Class");
    }

    @BeforeMethod
    public void beforeBasicMethod() {
	logger.info("Before Method");
    }

    @AfterMethod
    public void afterBasicMethod() {
	logger.info("After Method");
    }

    // Apparently it is enough to reference data provider by method name
    // @DataProvider(name = "dataProvidrBase")
    @DataProvider()
    public Object[][] createDataFromBase() {
	return new Object[][] { { "Ivo", new Integer(45) }, { "Zeljka", new Integer(34) } };

    }
}
