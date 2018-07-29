/**
 * 
 */
package com.iiht.wicket.webpage;

import java.util.Arrays;

import org.apache.commons.lang.StringUtils;
import org.apache.wicket.Page;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.model.Model;

import com.iiht.wicket.panels.RepeaterViewPanel;
import com.iiht.wicket.utils.IConstants;

/**
 * @author Vignesh Durairaj
 *
 */
public class ModalTestPage extends WebPage implements IConstants {

	private ModalWindow sampleModalWindow;
	
	private ModalWindow modalPageWindow;
	
	public ModalTestPage() {
		addComponents();
	}
	
	private void addComponents (){
		
		final Form<Void> modalForm = new Form<Void> ("modalForm");
		
		final RadioChoice<String> colorSelector = new RadioChoice<String>("colorSelector", 
				new Model<String>(COLOR_BLUE), Arrays.asList(COLOR_BLUE, COLOR_GREY));
		colorSelector.setSuffix(StringUtils.EMPTY);
		
		sampleModalWindow = new ModalWindow("sampleModal");
		sampleModalWindow.setContent(new RepeaterViewPanel(sampleModalWindow.getContentId()));
		
		modalPageWindow = new ModalWindow("modalPageWindow");
		modalPageWindow.setResizable(Boolean.FALSE);
		modalPageWindow.setPageCreator(new ModalWindow.PageCreator(){

			/** Generated serial Version ID. */
			private static final long serialVersionUID = -3122952641736476552L;

			public Page createPage() {
				return new FormTestPage();
			}
			
		});
		
		modalPageWindow.setCloseButtonCallback(new ModalWindow.CloseButtonCallback() {
			
			/** Generated Serial Version ID. */
			private static final long serialVersionUID = -5761194989697315614L;

			public boolean onCloseButtonClicked(AjaxRequestTarget target) {
				target.appendJavascript("alert('Popup Window Closed!')");
				target.prependJavascript("alert('About to close the window!')");
				return true;
			}
		});
		
		AjaxSubmitLink showModalLink = new AjaxSubmitLink("showModalLink") {
			
			/** Generated Serial Version ID. */
			private static final long serialVersionUID = 8787608969442233914L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				String selectedColor = colorSelector.getModelObject();
				sampleModalWindow.setCssClassName(
						selectedColor.equals(COLOR_BLUE) ? ModalWindow.CSS_CLASS_BLUE : 
							ModalWindow.CSS_CLASS_GRAY);
				sampleModalWindow.show(target);
			}
		};
		
		AjaxSubmitLink showPageModalLink = new AjaxSubmitLink("showPageModalLink") {

			/** Generated Serial Version ID. */
			private static final long serialVersionUID = -2706605715324141929L;

			@Override
			public void onSubmit(AjaxRequestTarget target, Form<?> form) {
				String selectedColor = colorSelector.getModelObject();
				modalPageWindow.setCssClassName(
						selectedColor.equals(COLOR_BLUE) ? ModalWindow.CSS_CLASS_BLUE : 
							ModalWindow.CSS_CLASS_GRAY);
				modalPageWindow.show(target);
			}
			
		};
		
		modalForm.add(colorSelector);
		modalForm.add(showModalLink);
		modalForm.add(showPageModalLink);
		modalForm.add(sampleModalWindow);
		modalForm.add(modalPageWindow);
		this.add(modalForm);
	}
}
