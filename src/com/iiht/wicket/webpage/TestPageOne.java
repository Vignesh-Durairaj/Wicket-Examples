/**
 * 
 */
package com.iiht.wicket.webpage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.wicket.Response;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AbstractAutoCompleteRenderer;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteTextField;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.Loop;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.util.ListModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.iiht.wicket.DAO.SpringDAO;
import com.iiht.wicket.components.TelecomPalette;
import com.iiht.wicket.model.TelecomModel;


/**
 * @author Vignesh Durairaj
 *
 */
public class TestPageOne extends BasePage {

	@SpringBean
	private SpringDAO springDAO;
	
	public TestPageOne() {
		
		Loop fragmentList = new Loop ("fragmentList", 6){

			/** Generated Serial Version Id. */
			private static final long serialVersionUID = -8659720445507043095L;

			@Override
			protected void populateItem(LoopItem item) {
				
				Fragment myFragment = new Fragment("placeholderContainer", "myFragment" + 
						(item.getIteration() % 2 + 1), this);
				myFragment.add(new Label("elementOne", item.getIteration() % 2 == 0 ? 
						"Element One" : "Element Two"));
				item.add(myFragment);
			}
			
		};
		
		ServiceAutoCompleteText autoCompleteText = new ServiceAutoCompleteText("autoCompleteText");
		
		Form<Void> paletteForm = new Form<Void> ("paletteForm");
		final TelecomPalette myPalette = new TelecomPalette("myPalette", new ListModel<TelecomModel>(new ArrayList<TelecomModel>(0)), 
				new ListModel<TelecomModel>(springDAO.getServiceProvidersList()));
		final AjaxSubmitLink submitLink = new AjaxSubmitLink("submitLink") {
			
			/** Generated Serial Version ID. */
			private static final long serialVersionUID = 1119129834641901241L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				System.out.println(myPalette.getAllItems().toString());
				System.out.println(myPalette.getSelectedItems().toString());
			}
		};
		
		paletteForm.add(myPalette);
		paletteForm.add(submitLink);
		
		this.add(autoCompleteText);
		this.add(fragmentList);
		this.add(paletteForm);
		
	}
	
	private class ServiceAutoCompleteText extends AutoCompleteTextField<TelecomModel> {

		/** Generated Serial Version ID. */
		private static final long serialVersionUID = 1377796627934740684L;
		
		private List<TelecomModel> serviceList = springDAO.getServiceProvidersList();
		
		public ServiceAutoCompleteText(final String id) {
			super(id, new Model<TelecomModel>(null), new TelecomServiceRenderer());
		}

		@Override
		protected Iterator<TelecomModel> getChoices(String inputStr) {
			
			List<TelecomModel> resultsList = new ArrayList<TelecomModel>(0);
			
			if (StringUtils.isNotEmpty(inputStr))
				for (final TelecomModel serviceModel : serviceList)
					if (serviceModel.getServiceName().toLowerCase()
							.startsWith(inputStr.toLowerCase()))
						resultsList.add(serviceModel);
			
			
			return resultsList.iterator();
		}
		
	}
	
	private class TelecomServiceRenderer extends AbstractAutoCompleteRenderer<TelecomModel> {

		/** Generated Serial Version ID. */
		private static final long serialVersionUID = -6936671336611121192L;

		@Override
		protected void renderChoice(TelecomModel serviceModel, Response response,
				String criteria) {
			response.write(getTextValue(serviceModel));
		}

		@Override
		protected String getTextValue(TelecomModel serviceModel) {
			return serviceModel.getServiceId() + " - " + serviceModel.getServiceName();
		}
		
	}
	
}
