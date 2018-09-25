package my.app;

import org.testng.annotations.DataProvider;

//If data provider is another class methods providing data must be static
public class DataProvider2 {

    @DataProvider(name = "dataProvider2")
    public static Object[][] createData() {
	return new Object[][] { new Object[] { "Saly", new Integer(45) } };

    }

}
