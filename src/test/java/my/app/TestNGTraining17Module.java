package my.app;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class TestNGTraining17Module extends AbstractModule {

	@Override
	protected void configure() {
		bind(InjectedObject.class).to(InjectedObjectImpl.class).in(Singleton.class);
	}

}
