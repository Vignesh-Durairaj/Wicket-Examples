/**
 * 
 */
package com.iiht.wicket.panels;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.validator.DateValidator;

import com.iiht.wicket.utils.IConstants;

/**
 * @author 1444058
 *
 */
public class ValidationPanel extends Panel implements IConstants {

	/** Generated Serial Version ID. */
	private static final long serialVersionUID = 2632282281336074227L;

	public ValidationPanel(String id) {
		super(id);
		addComponents();
	}
	
	private void addComponents() {
	 
		final Form<Void> validationForm = new Form<Void>("validationForm");
		final FeedbackPanel feedbackPanel = new FeedbackPanel("feedbackPanel");
		
		final TextField<Integer> mandatoryText = new TextField<Integer>(
				"mandatoryText", new Model<Integer>(), Integer.class);
		mandatoryText.setRequired(Boolean.TRUE);
		
		final TextField<Integer> customValidationText = new TextField<Integer>(
				"customValidationText", new Model<Integer>(), Integer.class);
		customValidationText.setRequired(Boolean.TRUE);
		
		final TextField<String> outOfBoxText = new TextField<String>("outOfBoxText", new Model<String>());
		
		final Button submitButton = new Button("submitButton"){
			
			/** Generated Serial Version class. */
			private static final long serialVersionUID = 446093100275936966L;
			
			@Override
			public void onSubmit() {
				super.onSubmit();
				
				String enteredValue = outOfBoxText.getModelObject();
				
				if (null == enteredValue || StringUtils.isEmpty(enteredValue))
					feedbackPanel.error("Please enter a value for the Third text field.");
				else if (!StringUtils.isNumeric(enteredValue))
					feedbackPanel.error("The third Text field should be a numeric value");
				else {
					Integer myIntegerValue = Integer.parseInt(enteredValue);
					if (myIntegerValue < 0 || myIntegerValue > 20)
						feedbackPanel.error("Enter a value between 0 to 20 for the third text field.");
				}
			}
		};
		
		final TextField<Date> dateText = new TextField<Date>("dateText", new Model<Date>(), Date.class);
		dateText.add(new DatePicker());
		dateText.setRequired(Boolean.TRUE);
		dateText.add(new MyDateValidator());
		
		validationForm.add(feedbackPanel);
		validationForm.add(mandatoryText);
		validationForm.add(customValidationText);
		validationForm.add(outOfBoxText);
		validationForm.add(dateText);
		validationForm.add(submitButton);
		this.add(validationForm);
		
		
	}

	private class MyDateValidator extends DateValidator {

		/** Generated Serial Version ID. */
		private static final long serialVersionUID = -4833997936869471257L;

		@Override
		protected void onValidate(IValidatable<Date> validatable) {
			
			Date selectedDate = validatable.getValue();
			
			if (new Date().compareTo(selectedDate) >= 0)
				error(validatable, "date.expired");
			
		}
		
	}
}
