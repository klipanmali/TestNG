package my.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;

/**
 * 
 * Once TestNH calculates in what order the test methods are invoked, these
 * methods are split in two groups:</br>
 * - Methods run sequentially. these are all the test methods that have
 * dependencies or dependents. These methods will be run in a specific
 * order.</br>
 * - Methods run in no particular order. These are all the methods that don't
 * belong in the first category. The order in which these test methods are urn
 * is random and can vary from one run to the next run, although by default,
 * TestNG will try to group test methods by class</br>
 * The list of methods passed in parameters are all the methods that can be run
 * in any order
 * 
 * !!!!! Well this is not quite true.</br>
 * I did get all the methods, the ones that depends on the other methods or
 * other groups, and the ones that don't have any dependencies, although grouped
 * by class.</br>
 * After method reordering, the order of non-dependent methods didn't change,
 * the order of dependent methods did change</br>
 * During execution, non-dependent methods were executed first, but the
 * dependent methods were not executed as expected. Dependent methods from
 * TestNGTraining5 were executed first(although last in list of dependent
 * methods) following by dependent methods from TestNGTraining6(the execution of
 * these methods did change).!!!!!
 */
public class MyMethodInterceptor implements IMethodInterceptor {

    @Override
    public List<IMethodInstance> intercept(List<IMethodInstance> methods, ITestContext context) {

	System.out.println("current order of the execution");
	methods.stream().map(iMethod -> iMethod.getMethod()).forEach(method -> {
	    System.out.println(method.getMethodName());
	    System.out.println("   dependsOnMethod = " + Arrays.toString(method.getMethodsDependedUpon()));
	    System.out.println("   dependsOnGroups = " + Arrays.toString(method.getGroupsDependedUpon()));
	});
	// reorder the methods, dependant first
	List<IMethodInstance> newMethods = new ArrayList<>();
	for (IMethodInstance m : methods) {
	    if (isDependant(m)) {
		newMethods.add(0, m);
	    } else {
		newMethods.add(m);
	    }
	}
	System.out.println("\n\nnew order of the execution");
	newMethods.stream().map(iMethod -> iMethod.getMethod()).forEach(method -> {
	    System.out.println(method.getMethodName());
	    System.out.println("   dependsOnMethod = " + Arrays.toString(method.getMethodsDependedUpon()));
	    System.out.println("   dependsOnGroups = " + Arrays.toString(method.getGroupsDependedUpon()));
	});
	return newMethods;
	// return methods;
    }

    private boolean isDependant(IMethodInstance methodInstance) {
	ITestNGMethod testNGMethod = methodInstance.getMethod();
	return !(testNGMethod.getMethodsDependedUpon().length == 0)
		|| !(testNGMethod.getGroupsDependedUpon().length == 0);

    }

}
