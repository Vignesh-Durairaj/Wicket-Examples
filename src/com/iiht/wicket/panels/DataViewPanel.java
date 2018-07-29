package com.iiht.wicket.panels;


import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.IRequestTarget;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.iiht.wicket.DAO.SpringDAO;
import com.iiht.wicket.model.TelecomModel;
import com.iiht.wicket.model.TypeModel;

public class DataViewPanel extends Panel{

	private static final long serialVersionUID = 8228588129072328660L;

	@SpringBean
	private SpringDAO springDao;
	
	public DataViewPanel(String id) {
		super(id);
		addComponents();
	}
	
	private void addComponents(){
		final WebMarkupContainer dataViewContainer = new WebMarkupContainer("dataViewContainer");
		dataViewContainer.setOutputMarkupPlaceholderTag(Boolean.TRUE);
		
		final TelecomDataView telecomDataView = new TelecomDataView("telecomDataView", 
				springDao.getServiceProvidersList());
		
		final AjaxLink<Void> addButton = new AjaxLink<Void>("addButton") {

			private static final long serialVersionUID = -7417450094979420704L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				telecomDataView.addElement(springDao.getSampleService());
			}
		};
		
		dataViewContainer.add(telecomDataView);
		this.add(dataViewContainer);
		this.add(addButton);
	}
	
	private void updateModelWithDummy (final TelecomModel telecomModel) {
		if (null != telecomModel) {
			telecomModel.setServiceName("Updated Service Name");
			telecomModel.setType(new TypeModel(8, "SatCom", "Satellite Communication based Links"));
			telecomModel.setCountry("Global");
			telecomModel.setAddress("World wide");
			telecomModel.setActiveStatus(Boolean.FALSE);
		} 
	}
	
	private class TelecomDataView extends DataView<TelecomModel> {

		private static final long serialVersionUID = -940655951784045655L;

		public TelecomDataView(final String id, final List<TelecomModel> telecomModelList) {
			super(id, new TelecomDataProvider(telecomModelList));
		}
		
		@Override
		protected void populateItem(Item<TelecomModel> item) {
			
			TelecomModel telecomModel = item.getModelObject();
			item.add(new Label("serviceIdLabel", telecomModel.getServiceId().toString()));
			item.add(new Label("serviceNameLabel", telecomModel.getServiceName()));
			item.add(new Label("typeLabel", telecomModel.getType().getTypeId() + " - " 
					+ telecomModel.getType().getTypeName()));
			item.add(new Label("addressLabel", telecomModel.getAddress()));
			item.add(new Label("countryLabel", telecomModel.getCountry()));
			item.add(new Label("statusLabel", telecomModel.getActiveStatus() ? "Active" : "Defunct").add(
					new AttributeModifier("style", new Model<String>(
							"color: " + (telecomModel.getActiveStatus() ? "green" : "red")
							+ ";"))));
			
			item.add(new AjaxLink<TelecomModel> ("deleteLink", new Model<TelecomModel>(telecomModel)) {

				private static final long serialVersionUID = 9177986386048382927L;

				@Override
				public void onClick(AjaxRequestTarget target) {
					TelecomModel currentRowModel = getModelObject();
					removeElement(currentRowModel);
				}
				
			});
			
			item.add(new AjaxLink<TelecomModel> ("updateLink", new Model<TelecomModel>(telecomModel)){

				private static final long serialVersionUID = 5662270427427314353L;

				@Override
				public void onClick(AjaxRequestTarget target) {
					TelecomModel currentRowModel = getModelObject();
					updateModelWithDummy(currentRowModel);
					updateElement(currentRowModel);
				}
				
			});
		}
		
		private List<TelecomModel> getList() {
			List<TelecomModel> inlineList = null;
			TelecomDataProvider myDataProvider = (TelecomDataProvider) this.getDataProvider();
			if (null != myDataProvider)
				inlineList = myDataProvider.getData();
			
			return inlineList;
		}
		
		public void addElement (final TelecomModel modelToAdd) {
			
			if (null != modelToAdd){
				List<TelecomModel> inLineList = getList();
				if (null != inLineList)
					inLineList.add(modelToAdd);
				
				refreshDataView(getAjaxRequestTarget());
					
			}
		}
		
		private void removeElement (final TelecomModel modelToRemove) {
			
			if (null != modelToRemove){
				List<TelecomModel> inLineList = getList();
				if (null != inLineList)
					inLineList.remove(modelToRemove);
				
				AjaxRequestTarget target = getAjaxRequestTarget();
				refreshDataView(target);
					
			}
		}

		private void updateElement (final TelecomModel modelToUpdate) {
			
			if (null != modelToUpdate) {
				int index = -1;
				List <TelecomModel> inlineList = getList();
				if (null != inlineList && !inlineList.isEmpty()) {
					for (int i = 0; i < inlineList.size(); i ++)
						if (modelToUpdate.equals(inlineList.get(i))) {
							index = i;
							break;
						}
					
					inlineList.set(index, modelToUpdate);
					refreshDataView(getAjaxRequestTarget());
							
				}
					
			}
		}
		
		private AjaxRequestTarget getAjaxRequestTarget () {
			AjaxRequestTarget target = null;
			IRequestTarget requestTarget = getRequestCycle().getRequestTarget();
			if (requestTarget instanceof AjaxRequestTarget)
				target = (AjaxRequestTarget) requestTarget;
			
			return target;
		}
		
		private void refreshDataView (final AjaxRequestTarget target) {
			Component parentElement = this.getParent();
			if (parentElement instanceof WebMarkupContainer && null != target)
				target.addComponent(parentElement);
		}
		
	}
	
	private class TelecomDataProvider extends ListDataProvider<TelecomModel> {
		
		private static final long serialVersionUID = -577922830343635825L;

		public TelecomDataProvider(final List<TelecomModel> telecomModelList) {
			super(telecomModelList);
		}
		
		@Override
		protected List<TelecomModel> getData() {
			return super.getData();
		}
		
	}

}
