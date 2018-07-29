/**
 * 
 */
package com.iiht.wicket.webpage;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;

/**
 * @author Vignesh Durairaj
 *
 */
public class WicketTagsPage extends WebPage {

	public WicketTagsPage() {
		addComponents();
	}
	
	private void addComponents(){
		
		Form<Void> tagForm = new Form<Void> ("tagForm");
		
		tagForm.add(new TextField<String>("sampleText", new Model<String>("")));
		tagForm.add(new TextArea<String>("testTextArea", new Model<String>(null)));
		
		final TextField<String> enclosureText = new TextField<String>("enclosureText", new Model<String>(null));
		enclosureText.setOutputMarkupPlaceholderTag(Boolean.TRUE);
		
		final AjaxButton popupButton = new AjaxButton("popupButton") {
			
			private static final long serialVersionUID = 4062870724271549582L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				target.appendJavascript("alert('The value entered is : " + enclosureText.getModelObject() + "');");
			}
		};
		
		final SubmitLink hideButton = new SubmitLink("hideButton") {
			
			private static final long serialVersionUID = -1897893696771560091L;

			@Override
			public void onSubmit() {
				super.onSubmit();
				enclosureText.setVisible(!enclosureText.isVisible());
			}
			
		};
		
		tagForm.add(new Label("dynamicLabel", "Enter a Value : "));
		tagForm.add(enclosureText);
		tagForm.add(popupButton);
		tagForm.add(hideButton);
		
		this.add(tagForm);
		
		
	} 
}
