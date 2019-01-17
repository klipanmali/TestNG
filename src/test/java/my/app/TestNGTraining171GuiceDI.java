package my.app;

import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import com.google.inject.Inject;

@Guice(modules = TestNGTraining17Module.class)
public class TestNGTraining171GuiceDI {

	@Inject
	InjectedObject injectedObject;

	@Test
	public void doSomeWorkTest() {
		System.out.println("TestNGTraining171GuiceDI.doSomeWorkTest");
		injectedObject.doSomething();
	}
}
