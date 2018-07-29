/**
 * 
 */
package com.iiht.wicket.panels;

import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;

import com.iiht.wicket.DAO.ServiceDAO;
import com.iiht.wicket.model.TelecomModel;
import com.iiht.wicket.others.CustomSession;
import com.iiht.wicket.utils.IConstants;

/**
 * @author Vignesh Durairaj
 *
 */
public class RepeaterViewPanel extends Panel implements IConstants{

	/** Generated Serial Version ID. */
	private static final long serialVersionUID = -1046018253296298998L;

	private WebMarkupContainer container;
	
	public RepeaterViewPanel(String id) {
		super(id);
		
		container = new WebMarkupContainer("container");
		container.setOutputMarkupPlaceholderTag(Boolean.TRUE);
		
		final ServiceRepeaterView repeaterView = new ServiceRepeaterView ("serviceRepeaterView", ServiceDAO.getServiceProvidersList());
		
		container.add(repeaterView);
		this.add(container);
		this.add(new AjaxLink<Void>("addButton"){

			/** Generated Serial Version ID. */
			private static final long serialVersionUID = -6564862938487120444L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				repeaterView.getServiceList().add(CustomSession.get().getTelecomModel());
				target.addComponent(container);
			}
			
		});
		
	}

	private class ServiceRepeaterView extends RepeatingView {

		/** Generated Serial Version ID. */
		private static final long serialVersionUID = 99369484843562342L;
		
		private List<TelecomModel> serviceList;
		
		public ServiceRepeaterView(String id) {
			super(id);
		}
		
		public ServiceRepeaterView (final String id, final List<TelecomModel> serviceList){
			super(id);
			this.serviceList = serviceList;
		}
		
		public List<TelecomModel> getServiceList() {
			return serviceList;
		}

		@Override
		protected void onPopulate() {
			super.onPopulate();
			
			this.removeAll();
			int index = 0;
			
			for (TelecomModel serviceModel : serviceList) {
				
				Item<TelecomModel> serviceItem = new Item<TelecomModel>(this.newChildId(), 
						index, new CompoundPropertyModel<TelecomModel>(serviceModel));
				
				serviceItem.add(new Label("serviceId"));
				serviceItem.add(new Label("serviceName"));
				serviceItem.add(new Label("type.typeName"));
				serviceItem.add(new Label("address"));
				serviceItem.add(new Label("country"));
				serviceItem.add(new Label("activeStatus"));
				serviceItem.add(new AjaxLink<TelecomModel>("removeLink", new Model<TelecomModel>(serviceModel)){

					/** Generated Serial Version ID. */
					private static final long serialVersionUID = 4930422348966259796L;

					@Override
					public void onClick(AjaxRequestTarget target) {
						serviceList.remove(this.getModelObject());
						target.addComponent(container);
					}
					
				});
				
				this.add(serviceItem);
				index ++;
			}
			
		}

	}
}
