/**
 * 
 */
package com.iiht.wicket.panels;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import com.iiht.wicket.DAO.ServiceDAO;
import com.iiht.wicket.model.TelecomModel;
import com.iiht.wicket.utils.IConstants;

/**
 * @author Vignesh Durairaj	
 *
 */
public class ListViewPanel extends Panel implements IConstants {

	/** Generated serial version Id. */
	private static final long serialVersionUID = -4617912464155148982L;
	
	private WebMarkupContainer serviceContainer;

	public ListViewPanel(String id) {
		super(id);
		
		serviceContainer = new WebMarkupContainer("serviceContainer");
		serviceContainer.setOutputMarkupPlaceholderTag(Boolean.TRUE);
		
		serviceContainer.add(new ServiceListView("serviceListView"));
		this.add(serviceContainer);
	}

	private class ServiceListView extends ListView<TelecomModel> {

		/** Generated Serial Version ID. */
		private static final long serialVersionUID = -2355942435692566387L;

		public ServiceListView(final String id) {
			super(id, ServiceDAO.getServiceProvidersList());
		}
		
		@Override
		protected void populateItem(ListItem<TelecomModel> item) {
			TelecomModel telecomModel = item.getModelObject();
			
			Label statusLabel = new Label("statusLabel", telecomModel.getActiveStatus() ? "Active" : "Defunct");
			statusLabel.add(new AttributeModifier("style", new Model<String>("color:" + 
					(telecomModel.getActiveStatus() ? "green" : "red") + ";")));
			
			item.add(new Label("serviceIdLabel", telecomModel.getServiceId().toString()));
			item.add(new Label("serviceNameLabel", telecomModel.getServiceName()));
			item.add(new Label("typeLabel", telecomModel.getType().getTypeId() + " - " 
					+ telecomModel.getType().getTypeName()));
			item.add(new Label("addressLabel", telecomModel.getAddress()));
			item.add(new Label("countryLabel", telecomModel.getCountry()));
			item.add(statusLabel);
			item.add(new AjaxLink<TelecomModel> ("removeLink", new Model<TelecomModel>(telecomModel)){

				/** Generated Serial Version ID. */
				private static final long serialVersionUID = -2362811819945919601L;

				@Override
				public void onClick(AjaxRequestTarget target) {
					ServiceListView.this.getList().remove(this.getModelObject());
					target.addComponent(serviceContainer);
				}
				
			});
			
		}
		
	}
}
