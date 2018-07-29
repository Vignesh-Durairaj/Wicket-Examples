/**
 * 
 */
package com.iiht.wicket.webpage;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.HiddenField;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;

import com.iiht.wicket.DAO.ServiceDAO;
import com.iiht.wicket.model.TelecomModel;
import com.iiht.wicket.panels.LabelButtonPanel;
import com.iiht.wicket.utils.IConstants;

/**
 * @author Vignesh Durairaj
 *
 */
public class FormTestPage extends WebPage {
	
	public FormTestPage() {
		addComponents();
	}
	
	private void addComponents () {
		final Form<Void> myTestForm = new Form<Void> ("testForm");
		final FeedbackPanel feedbackPanel = new FeedbackPanel("feedbackPanel");
		final TextField<String> sampleText = new TextField<String>("sampleText", new Model<String>(null));
		final PasswordTextField passwordText = new PasswordTextField("passwordText", new Model<String>(null));
		final HiddenField<String> hiddenTextField = new HiddenField<String>("hiddenText", new Model<String>("Am a Hidden data."));
		
		final DropDownChoice<TelecomModel> serviceDropDown = new DropDownChoice<TelecomModel>("serviceDropDown", new Model<TelecomModel>(), 
				ServiceDAO.getServiceProvidersList(), new ChoiceRenderer<TelecomModel>() {
			
			/** Generated Serial Version ID. */
			private static final long serialVersionUID = -7644313365923330179L;

			@Override
			public Object getDisplayValue(TelecomModel object) {
				return object.getServiceId() + " - " + object.getServiceName();
			}
		}){

			/** Generated Serial Version ID */
			private static final long serialVersionUID = 3678760770482641897L;
			
			protected CharSequence getDefaultChoice(Object selected) {
				return "<option>Select a Serice provider.</option>";
			};
		};
		
		final RadioChoice<String> activityChoices = new RadioChoice<String>("activeRadioChoices", new Model<String>(null), IConstants.YES_NO_LIST);
		activityChoices.setSuffix(" ");
		
		final RadioGroup<String> supportRadioGroup = new RadioGroup<String>("supportRadioGroup", new Model<String>(IConstants.MAY_BE));
		final Radio<String> yesRadio = new Radio<String>("yesRadio", new Model<String>(IConstants.YES));
		final Radio<String> noRadio = new Radio<String>("noRadio", new Model<String>(IConstants.NO));
		final Radio<String> mayBeRadio = new Radio<String>("mayBeRadio", new Model<String>(IConstants.MAY_BE));
		
		supportRadioGroup.add(yesRadio);
		supportRadioGroup.add(noRadio);
		supportRadioGroup.add(mayBeRadio);
		
		final CheckBox telCheckBox = new CheckBox("telCheckBox", new Model<Boolean>());
		final CheckBox mobileCheckBox = new CheckBox("mobileCheckBox", new Model<Boolean>());
		final CheckBox interNetCheckBox = new CheckBox("interNetCheckBox", new Model<Boolean>());
		
		final Button submitButton = new Button("submitButton") {
			
			/** Generated Serial Version ID. */
			private static final long serialVersionUID = -7332630351377360206L;

			@Override
			public void onSubmit() {
				super.onSubmit();
				System.err.println("The sample Text is : " + sampleText.getModelObject());
				System.err.println("And the password entered is : " + passwordText.getModelObject());
				System.err.println("The Hidden data is : " + hiddenTextField.getDefaultModelObject());
				System.err.println("The Selected Service Provider is : " + serviceDropDown.getDefaultModelObjectAsString());
				System.err.println("Active : " + activityChoices.getModelObject());
				System.err.println("Is Support available : " + supportRadioGroup.getModelObject());
				System.err.println("Service Available : Telephone - " + telCheckBox.getModelObject() + 
						", Mobile - " + mobileCheckBox.getModelObject() + ", Internet - " + interNetCheckBox.getModelObject());
			}
			 
		};
		
		final SubmitLink submitLink = new SubmitLink("submitLink"){
		
			/** Generated Serial Version ID. */
			private static final long serialVersionUID = 6726911769509316700L;

			@Override
			public void onSubmit() {
				super.onSubmit();
				System.err.println("The sample Text is : " + sampleText.getModelObject());
				System.err.println("And the password entered is : " + passwordText.getModelObject());
				System.err.println("The Hidden data is : " + hiddenTextField.getDefaultModelObject());
				System.err.println("The Selected Service Provider is : " + serviceDropDown.getModelObject().getServiceName());
				System.err.println("Active : " + activityChoices.getModelObject());
				System.err.println("Is Support available : " + supportRadioGroup.getModelObject());
				System.err.println("Service Available : Telephone - " + telCheckBox.getModelObject() + 
						", Mobile - " + mobileCheckBox.getModelObject() + ", Internet - " + interNetCheckBox.getModelObject());
			}
		};
		
		final LabelButtonPanel labelButtonPanel = new LabelButtonPanel("labelButtonPanel");
		
		myTestForm.add(feedbackPanel);
		myTestForm.add(sampleText);
		myTestForm.add(passwordText);
		myTestForm.add(hiddenTextField);
		myTestForm.add(serviceDropDown);
		myTestForm.add(activityChoices);
		myTestForm.add(supportRadioGroup);
		myTestForm.add(telCheckBox);
		myTestForm.add(mobileCheckBox);
		myTestForm.add(interNetCheckBox);
		myTestForm.add(submitButton);
		myTestForm.add(submitLink);
		
		add(myTestForm);
		add(labelButtonPanel);
	}
	
}
