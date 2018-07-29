/**
 * 
 */
package com.iiht.wicket.panels;

import org.apache.commons.lang.StringUtils;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormSubmitBehavior;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import com.iiht.wicket.utils.IConstants;

/**
 * @author Vignesh Durairaj
 *
 */
@SuppressWarnings("unchecked")
public class LabelButtonPanel extends Panel implements IConstants{

	private static final long serialVersionUID = -5921854043524521974L;

	private Form<Void> myForm;
	
	public LabelButtonPanel(String id) {
		super(id);
		
		myForm = new Form<Void>("myForm");
		
		final Label myLabel = new Label("myLabel", new Model<String>(StringUtils.EMPTY));
		final TextField<String> myTextField = new TextField<String>("myTextField", new Model<String>(null));
		
		myLabel.setOutputMarkupPlaceholderTag(Boolean.TRUE);
		
		myForm.add(new SubmitLabel("submitLabel", "Click Here !"));
		myForm.add(myTextField);
		myForm.add(myLabel);
		add(myForm);
	}
	
	private class SubmitLabel extends Label {

		private static final long serialVersionUID = -1493348079890867838L;

		public SubmitLabel(String id, String label) {
			super(id, label);
			this.add(new SubmitBehavior());
		}
		
	}
	
	public class SubmitBehavior extends AjaxFormSubmitBehavior {

		private static final long serialVersionUID = 1578409364528489961L;

		public SubmitBehavior() {
			super(ON_CLICK);
		}

		@Override
		protected void onSubmit(AjaxRequestTarget target) {
			
			if (null != myForm) {
				TextField<String> formText = (TextField<String>) myForm.get("myTextField");
				Label formLabel = (Label)myForm.get("myLabel");
				
				formLabel.setDefaultModelObject(formText.getModelObject());
				target.addComponent(formLabel);
				
				System.out.println("The form has been submitted !");
			}
			
		}

		@Override
		protected void onError(AjaxRequestTarget target) {
			System.err.println("Error in submitting the form !");
		}
		
	}

}
