package my.app;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Objects;

import org.testng.annotations.ITestAnnotation;
import org.testng.internal.annotations.IAnnotationTransformer;

/**
 * 
 * This transformer is applied only for &#64;Test annotation </br>
 * If you need to modify another TestNG annotation (&#64;Factory,
 * &#64;DataProvider ...) use IAnnotationTransformer2 or even
 * IAnnotationTransformer3</br>
 * Only one of the three parameters testClass, testConstructor and testMethod
 * will be non-null.
 */
public class MyTestTransformer implements IAnnotationTransformer {

    @Override
    public void transform(ITestAnnotation arg0, Class arg1, Constructor arg2, Method arg3) {
	classTransformer(arg0, arg1);
	constructorTransformer(arg0, arg2);
	methodTransformer(arg0, arg3);

    }

    private void classTransformer(ITestAnnotation arg0, Class arg1) {
	if (Objects.nonNull(arg1)) {
	    System.out.println("Suite: " + arg0.getSuiteName() + " ,Test: " + arg0.getTestName() + " , Class: "
		    + arg1.getCanonicalName());
	    // set some attribute to a new value
	    // arg0.setEnabled(false);
	}
    }

    private void constructorTransformer(ITestAnnotation arg0, Constructor arg2) {
	if (Objects.nonNull(arg2)) {
	    System.out.println("Suite: " + arg0.getSuiteName() + " ,Test: " + arg0.getTestName() + " , Constructor: "
		    + arg2.getName());
	    // set some attribute to a new value
	}
    }

    private void methodTransformer(ITestAnnotation arg0, Method arg3) {
	if (Objects.nonNull(arg3)) {
	    System.out.println(
		    "Suite: " + arg0.getSuiteName() + " ,Test: " + arg0.getTestName() + " , Method: " + arg3.getName());
	    // set some attribute to a new value
	    if (arg3.getName().equals("f2")) {
		arg0.setInvocationCount(2);
	    }
	}
    }

}
