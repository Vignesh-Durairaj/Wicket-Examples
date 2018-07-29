/**
 * 
 */
package com.iiht.wicket.webpage;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.ResourceModel;

import com.iiht.wicket.DAO.ServiceDAO;
import com.iiht.wicket.model.TelecomModel;

/**
 * @author Vignesh Durairaj
 *
 */
public class ModelTestPage extends WebPage {

	private TelecomModel sampleService = ServiceDAO.getSampleService();
	
	public ModelTestPage() {
		addComponents();
	}
	
	private void addComponents() {
		addSimpleModelElements();
		addPropertyModelElements();
		addCompundPropertyModelElements();
		addResourceModelElements();
		addLDModelElements();
	}
	
	private void addSimpleModelElements() {
		this.add(new Label("providerId", new Model<Integer>(sampleService.getServiceId())));
		this.add(new Label("providerName", new Model<String>(sampleService.getServiceName())));
		this.add(new Label("serviceType", new Model<String>(sampleService.getType().getTypeName())));
		this.add(new Label("countryLabel", new Model<String>(sampleService.getAddress() + " - " + sampleService.getCountry())));
	}
	
	private void addPropertyModelElements() {
		this.add(new Label("propProviderId", new PropertyModel<TelecomModel>(sampleService, "serviceId")));
		this.add(new Label("propProviderName", new PropertyModel<TelecomModel>(sampleService, "serviceName")));
		this.add(new Label("propServiceType", new PropertyModel<TelecomModel>(sampleService, "type.typeName")));
		this.add(new Label("propCountryLabel", new PropertyModel<TelecomModel>(sampleService, "country")));
	}
	
	private void addCompundPropertyModelElements() {
		
		WebMarkupContainer cpContainer = new WebMarkupContainer("cpContainer", new CompoundPropertyModel<TelecomModel>(sampleService));
		
		cpContainer.add(new Label("serviceId"));
		cpContainer.add(new Label("serviceName"));
		cpContainer.add(new Label("type.typeName"));
		cpContainer.add(new Label("country"));
		
		this.add(cpContainer);
	}
	
	private void addResourceModelElements() {
		this.add(new Label ("resourceLabel", new ResourceModel("sample.label")));
	}
	
	private void addLDModelElements() {
		final String serviceProvider = sampleService.getServiceName() + ", " + sampleService.getAddress() + " - "
				+ sampleService.getCountry();
		
		this.add(new Label ("ldLabel", new LoadableDetachableModel<String>() {

			/** Generated Serial Version ID. */
			private static final long serialVersionUID = -4924600867139358401L;

			@Override
			protected String load() {
				return serviceProvider;
			}
			
			@Override
			public void detach() {
				super.detach();
			}
			
		}));
	}
}
