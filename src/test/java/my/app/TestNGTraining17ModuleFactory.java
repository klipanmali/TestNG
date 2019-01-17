package my.app;

import org.testng.IModuleFactory;
import org.testng.ITestContext;

import com.google.inject.Module;

/**
 * This class should be Module factory class. Factory will be passed an instance
 * of the test Context and the test class that TestNG needs to instantiate. For
 * simplicity this factory returns TestNGTraining17Module instance
 */

public class TestNGTraining17ModuleFactory implements IModuleFactory {

	@Override
	public Module createModule(ITestContext arg0, Class<?> arg1) {
		return new TestNGTraining17Module();
	}

}
