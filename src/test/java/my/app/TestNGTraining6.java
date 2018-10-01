package my.app;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

/**
 * 
 * Dependences - groups version
 *
 */
public class TestNGTraining6 {
    private Logger logger = Logger.getLogger(this.getClass());

    @BeforeGroups("security")
    public void setUpSecurity() {
	logger.info("Setting up security");
    }

    @AfterGroups("security")
    public void teraDownSecurity() {
	logger.info("tearing down security");
    }

    @BeforeGroups("database")
    public void setUpDatabase() {
	logger.info("Setting up database");
    }

    @AfterGroups("database")
    public void tearDownDatabase() {
	logger.info("tearing up database");
    }

    @BeforeGroups("ui")
    public void setUpUI() {
	logger.info("Setting up UI");
    }

    @AfterGroups("ui")
    public void tearDownUI() {
	logger.info("tearing up UI");
    }

    @Test(groups = "database")
    public void testInsert() {
	logger.info("testing INSERT to DB");
    }

    @Test(groups = "database")
    public void testDelete() {
	logger.info("testing DELETE from DB");
    }

    @Test(groups = "security")
    public void testUserAccess() {
	logger.info("testing User Access");
    }

    @Test(groups = "security")
    public void TestAdminAccess() {
	logger.info("testing Admin Access");
	throw new RuntimeException();
    }

    @Test(groups = "ui")
    public void openAddDialog() {
	logger.info("Opening Add dialog");
    }

    @Test(groups = "ui")
    public void openFileDialog() {
	logger.info("Opening File dialog");
    }

    @Test(dependsOnGroups = "ui")
    public void uiTest() {
	logger.info("UI test");
    }

    @Test(dependsOnGroups = { "security", "database" })
    public void backendTest1() {
	logger.info("backend test 1");
    }

    // depends on any security group ("securityall" "security1")
    // depends on any database group ("databaseOracle" "databaseSQL")
    @Test(dependsOnGroups = { "security.*", "database.*" }, alwaysRun = true)
    public void backendTest2() {
	logger.info("backend test 2");
    }
}
