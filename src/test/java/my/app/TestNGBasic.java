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

    @BeforeClass
    public void beforeCLass() {
	logger.info("Before Class Root");
    }

    @AfterClass
    public void afterClass() {
	logger.info("After Class Root");
    }

    @BeforeMethod
    public void BeforeMethod() {
	logger.info("Before Method Root");
    }

    @AfterMethod
    public void afterMethod() {
	logger.info("After Method Root");
    }

    // Apparently it is enough to reference data provider by method name
    // @DataProvider(name = "dataProvidrBase")
    @DataProvider()
    public Object[][] createDataFromBase() {
	return new Object[][] { { "Ivo", new Integer(45) }, { "Zeljka", new Integer(34) } };

    }
}
