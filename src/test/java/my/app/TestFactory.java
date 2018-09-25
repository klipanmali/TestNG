package my.app;

import org.testng.annotations.Factory;

public class TestFactory {

    @Factory
    public Object[] testFactoryMethod() {
	Object[] result = new Object[2];
	result[1] = new TestNGTraining2(223, 24);
	result[0] = new TestNGTraining2(113, 15);
	return result;
    }

}
