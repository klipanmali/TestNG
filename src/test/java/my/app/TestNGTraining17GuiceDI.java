package my.app;

import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import com.google.inject.Inject;

/**
 * Simple Guice DI injection using exact Guice Module
 *
 */

@Guice(modules = TestNGTraining17Module.class)
public class TestNGTraining17GuiceDI {

	@Inject
	InjectedObject injectedObject;

	@Test
	public void doSomeWorkTest() {
		System.out.println("TestNGTraining17GuiceDI.doSomeWorkTest");
		injectedObject.doSomething();
	}
}
