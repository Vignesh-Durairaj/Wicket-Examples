/**
 * 
 */
package com.iiht.wicket.application;

import org.apache.wicket.application.IComponentInstantiationListener;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.stereotype.Component;

import com.iiht.wicket.webpage.FormTestPage;
import com.iiht.wicket.webpage.ModalTestPage;
import com.iiht.wicket.webpage.ModelTestPage;
import com.iiht.wicket.webpage.PanelTest;
import com.iiht.wicket.webpage.TableTest;
import com.iiht.wicket.webpage.WicketTagsPage;

/**
 * @author Vignesh Durairaj
 *
 */
@Component
public class TelecomApp extends TelecomAppTest{

	private IComponentInstantiationListener instantiationListener;
	
	@Override
	protected void init() {
		super.init();
		mountBookmarkableUrls();
	}
	
	private void mountBookmarkableUrls () {
		mountBookmarkablePage("sampleForm", FormTestPage.class);
		mountBookmarkablePage("modalPage", ModalTestPage.class);
		mountBookmarkablePage("panelPage", PanelTest.class);
		mountBookmarkablePage("sampleTables", TableTest.class);
		mountBookmarkablePage("wicketModels", ModelTestPage.class);
		mountBookmarkablePage("tagsTest", WicketTagsPage.class);
	}
	
	@Override
	protected void initializeSpringInjector() {
		instantiationListener = new SpringComponentInjector(this);
		setSpringInjector(instantiationListener);
	}

}
