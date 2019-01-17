package my.app;

import java.util.Random;

public class InjectedObjectImpl implements InjectedObject {

	Integer randomNum;
	
	public InjectedObjectImpl() {
		Random rnd = new Random();
		randomNum = rnd.nextInt();
	}
	
	@Override
	public void doSomething() {
		System.out.println("Injected Object random Num = " + randomNum);
	}

}
