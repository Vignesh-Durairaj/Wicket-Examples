/**
 * 
 */
package com.iiht.wicket.application;

import org.apache.wicket.Page;
import org.apache.wicket.Request;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.Response;
import org.apache.wicket.Session;
import org.apache.wicket.application.IComponentInstantiationListener;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WebRequest;
import org.apache.wicket.protocol.http.WebRequestCycle;
import org.apache.wicket.protocol.http.WebResponse;
import org.apache.wicket.util.file.WebApplicationPath;

import com.iiht.wicket.others.CustomResourceFinder;
import com.iiht.wicket.others.CustomSession;
import com.iiht.wicket.webpage.MyExceptionPage;
import com.iiht.wicket.webpage.TelecomHome;

/**
 * @author Vignesh Durairaj
 *
 */
public abstract class AbstractTelecomApp extends WebApplication {

	private IComponentInstantiationListener springInjector;
	
	@Override
	public Class<? extends Page> getHomePage() {
		return TelecomHome.class;
	}

	@Override
	protected void init() {
		super.init();
		
		initializeSpringInjector();
		
		getDebugSettings().setAjaxDebugModeEnabled(Boolean.TRUE);

		getResourceSettings().setResourceStreamLocator(getMyCustomerResourceLocator());
		addComponentInstantiationListener(springInjector);

		getMarkupSettings().setAutomaticLinking(Boolean.TRUE);
		getMarkupSettings().setStripComments(Boolean.TRUE);
		getMarkupSettings().setStripWicketTags(Boolean.TRUE);
		getMarkupSettings().setStripXmlDeclarationFromOutput(Boolean.TRUE);
	}
	
	@Override
	public RequestCycle newRequestCycle(Request request, Response response) {
		return (new WebRequestCycle(this, (WebRequest) request, (WebResponse) response){
			@Override
			public Page onRuntimeException(Page page, RuntimeException e) {
				e.printStackTrace();
				return (new MyExceptionPage(e));
			}
		});
	}
	
	@Override
	public Session newSession(Request request, Response response) {
		return (new CustomSession(request));
	}
	
	private CustomResourceFinder getMyCustomerResourceLocator () {
		
		CustomResourceFinder resourceFinder = new CustomResourceFinder();
		
		WebApplicationPath applicationPath = new WebApplicationPath(getServletContext());
		applicationPath.add("/WEB-INF/html/");
		resourceFinder.addFinder(applicationPath);
		return resourceFinder;
		
	}

	protected abstract void initializeSpringInjector();

	public IComponentInstantiationListener getSpringInjector() {
		return springInjector;
	}

	public void setSpringInjector(IComponentInstantiationListener springInjector) {
		this.springInjector = springInjector;
	}
	
	
}
