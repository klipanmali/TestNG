package my.app;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNGTrainingRetry {
    @Test
    public void f1() {
	System.out.println("f1 executed, passed");
    }

    @Test
    public void f2() {
	System.out.println("f2 executed, failed");
	Assert.fail();
    }

    @Test
    public void f3() {
	System.out.println("f3 executed, failed");
	Assert.fail();
    }

    @Test
    public void f4() {
	System.out.println("f4 executed, passed");
    }
}
