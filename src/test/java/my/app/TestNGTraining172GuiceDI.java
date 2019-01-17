package my.app;

import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import com.google.inject.Inject;

/**
 * Guice DI with help of Module Factory Since ModuleFactory returns another
 * instance of TestNGTraining17Module, (not the same as used
 * TestNGTraining17/171GuiceDI classes) singleton instance in
 * TestNGTraining17/171GuiceDI is not the same as here
 *
 */

@Guice(moduleFactory = TestNGTraining17ModuleFactory.class)
public class TestNGTraining172GuiceDI {

	@Inject
	InjectedObject injectedObject;

	@Test
	public void doSomeWorkTest() {
		System.out.println("TestNGTraining172GuiceDI.doSomeWorkTest");
		injectedObject.doSomething();
	}

}
