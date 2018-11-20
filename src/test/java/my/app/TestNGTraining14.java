package my.app;

import org.testng.annotations.Test;

/**
 * 
 * For "SKIPPED" tests nothing is executed nor Before nor After methods. Test is
 * simple skipped
 *
 */
public class TestNGTraining14 extends TestNGTraining14Base {
    @Test
    public void f1() {
    }

    @Test(dependsOnMethods = { "f3" })
    public void f2() {
    }

    @Test
    public void f3() {
	throw new RuntimeException();
    }

    @Test
    public void f6() {
    }

    @Test(dependsOnMethods = { "f5" })
    public void f4() {
    }

    @Test
    public void f5() {
	throw new RuntimeException();
    }

}
