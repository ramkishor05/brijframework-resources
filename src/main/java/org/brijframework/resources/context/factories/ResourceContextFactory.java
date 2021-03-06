package org.brijframework.resources.context.factories;

import org.brijframework.factories.impl.bootstrap.AbstractBootstrapFactory;
import org.brijframework.resources.context.ResourceContext;
import org.brijframework.support.factories.SingletonFactory;
import org.brijframework.support.ordering.OrderOn;
import org.brijframework.util.factories.ReflectionFactory;
import org.brijframework.util.printer.LoggerConsole;
import org.brijframework.util.reflect.InstanceUtil;

@OrderOn(1)
public class ResourceContextFactory extends AbstractBootstrapFactory<String, ResourceContext> {

    private static ResourceContextFactory factory ;
	
	@SingletonFactory
	public static ResourceContextFactory getFactory() {
		if(factory==null) {
		    factory=new ResourceContextFactory();
		}
		return factory;
	}
	
	@Override
	public ResourceContextFactory loadFactory() {
		try {
			LoggerConsole.screen("BootstrapFactory -> "+this.getClass().getSimpleName(), "Lunching the factory for ResourceContext");
			ReflectionFactory.getFactory().getExternalClassList().forEach(cls->{
				if(ResourceContext.class.isAssignableFrom(cls) && InstanceUtil.isAssignable(cls)) {
					ResourceContext resourceContext = (ResourceContext) InstanceUtil.getInstance(cls);
					resourceContext.start();
					this.register(resourceContext.getClass().getName(), resourceContext);
					this.register(resourceContext.getClass().getSimpleName(), resourceContext);
				}
			});
			ReflectionFactory.getFactory().getInternalClassList().forEach(cls->{
				if(ResourceContext.class.isAssignableFrom(cls) && InstanceUtil.isAssignable(cls)) {
					ResourceContext resourceContext = (ResourceContext) InstanceUtil.getInstance(cls);
					resourceContext.start();
					this.register(resourceContext.getClass().getName(), resourceContext);
					this.register(resourceContext.getClass().getSimpleName(), resourceContext);
				}
			});
			LoggerConsole.screen("BootstrapFactory -> "+this.getClass().getSimpleName(), "Lunched the factory for ResourceContext");
		} catch (Exception e) {
			LoggerConsole.screen("BootstrapFactory -> "+this.getClass().getSimpleName(), "Error to lunch the factory for ResourceContext");
		}
		return this;
	}

	@Override
	protected void preregister(String key, ResourceContext value) {
		
	}

	@Override
	protected void postregister(String key, ResourceContext value) {
	}

}
