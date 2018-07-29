/**
 * 
 */
package com.iiht.wicket.application;

import org.apache.wicket.application.IComponentInstantiationListener;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author Vignesh Durairaj
 *
 */
@Component
public class TelecomAppTest extends AbstractTelecomApp implements ApplicationContextAware {

	private ApplicationContext context;
	
	private IComponentInstantiationListener instantiationListener;
	
	@Override
	protected void init() {
		super.init();
	}
	
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		this.context = context;
	}

	@Override
	protected void initializeSpringInjector() {
		instantiationListener = new SpringComponentInjector(this, context, true);
		setSpringInjector(instantiationListener);
	}

}
