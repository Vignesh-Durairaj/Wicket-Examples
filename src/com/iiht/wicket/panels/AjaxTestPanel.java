/**
 * 
 */
package com.iiht.wicket.panels;

import org.apache.commons.lang.StringUtils;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormChoiceComponentUpdatingBehavior;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import com.iiht.wicket.components.ErrorPanel;
import com.iiht.wicket.utils.IConstants;

/**
 * @author Vignesh Durairaj
 *
 */
public class AjaxTestPanel extends Panel implements IConstants{

	/** Generated Serial Version ID. */
	private static final long serialVersionUID = 7826010570962277253L;
	
	private ErrorPanel errorPanel = new ErrorPanel("errorPanel");

	private TextField<String> serviceIdText;
	
	private TextField<String> serviceNameText;
	
	private TextField<String> countryText;
	
	private RadioChoice<String> activeRadioChoices;
	
	private Label dynamicLabel;
	
	public AjaxTestPanel(String id) {
		super(id);
		addComponents();
	}
	
	private void addComponents() {
		
		final Form<Void> ajaxForm = new Form<Void>("ajaxForm");
		serviceIdText = new TextField<String>("serviceIdText", new Model<String>());
		serviceNameText = new TextField<String>("serviceNameText", new Model<String>());
		countryText = new TextField<String>("countryText", new Model<String>());
		
		activeRadioChoices = new RadioChoice<String>("activeRadioChoices", new Model<String>(), YES_NO_LIST);
		activeRadioChoices.setSuffix(StringUtils.EMPTY);
		
		dynamicLabel = new Label("dynamicLabel", new Model<String>());
		dynamicLabel.setOutputMarkupPlaceholderTag(Boolean.TRUE);
		
		serviceNameText.add(new AjaxFormComponentUpdatingBehavior("onkeyup") {
			
			/** Generated Serial Version ID. */
			private static final long serialVersionUID = -4234957779960923441L;

			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				
				dynamicLabel.setDefaultModelObject(serviceNameText.getModelObject());
				target.addComponent(dynamicLabel);
			}
		});
		
		countryText.add(new AjaxFormComponentUpdatingBehavior("onblur") {
			
			/** Generated Serial Version ID. */
			private static final long serialVersionUID = 3416287867121863018L;

			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				
				dynamicLabel.setDefaultModelObject(countryText.getModelObject());
				target.addComponent(dynamicLabel);
			}
		});
		
		activeRadioChoices.add(new ActiveChoiceClickBehavior());
		
		AjaxSubmitLink submitButton = new AjaxSubmitLink("submitButton") {
			
			/** Generated Serial version ID. */
			private static final long serialVersionUID = 3596263362498811169L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				validateForm();
				target.addComponent(errorPanel);
			}
		};
		
		AjaxButton toggleButton = new AjaxButton("toggleButton") {
			
			/** Generated Serial Version ID. */
			private static final long serialVersionUID = 6656646073944481367L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				dynamicLabel.setVisible(!dynamicLabel.isVisible());
				target.addComponent(dynamicLabel);
			}
		};
				
		ajaxForm.add(errorPanel);
		ajaxForm.add(serviceIdText);
		ajaxForm.add(serviceNameText);
		ajaxForm.add(countryText);
		ajaxForm.add(activeRadioChoices);
		ajaxForm.add(submitButton);
		ajaxForm.add(toggleButton);
		ajaxForm.add(dynamicLabel);
		this.add(ajaxForm);
		
	}
	
	private Boolean validateForm() {
		Boolean isValid = Boolean.TRUE;
		
		String serviceId = serviceIdText.getModelObject();
		String serviceName = serviceNameText.getModelObject();
		String country = countryText.getModelObject();
		
		if (null == serviceId || StringUtils.isEmpty(serviceId)) {
			errorPanel.error("Please enter the service Id.");
			isValid = Boolean.FALSE;
		} else if (!StringUtils.isNumeric(serviceId)) {
			errorPanel.error("The service Id should be numeric.");
			isValid = Boolean.FALSE;
		} else if (null == serviceName || StringUtils.isEmpty(serviceName)) {
			errorPanel.error("Please enter the service provider name.");
			isValid = Boolean.FALSE;
		} else if (null == country || StringUtils.isEmpty(country)) {
			errorPanel.error("Please enter the country name.");
			isValid = Boolean.FALSE;
		} else 
			errorPanel.info("The data are valid.");
		
		errorPanel.setIsErrorMessage(!isValid);
		return isValid;
	}
	
	private class ActiveChoiceClickBehavior extends AjaxFormChoiceComponentUpdatingBehavior {

		/** Generated Serial Version Id. */
		private static final long serialVersionUID = -187319297551463035L;

		public ActiveChoiceClickBehavior() {
			super();
		}
		
		@Override
		protected void onUpdate(AjaxRequestTarget target) {
			dynamicLabel.setDefaultModelObject(getComponent().getDefaultModelObject().equals(YES) ? 
					"The service is Active." : "Its a defunct one.");
			target.addComponent(dynamicLabel);
		}
		
	}
	
}
