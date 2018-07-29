/**
 * 
 */
package com.iiht.wicket.webpage;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.wicket.ajax.AbstractAjaxTimerBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.time.Duration;

/**
 * @author Vignesh Durairaj
 *
 */
public class BasePage extends WebPage {
	
	private Label dateTimeLabel;
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss a");
	
	public BasePage() {
		addComponents();
	}
	
	private void addComponents() {
		
		dateTimeLabel = new Label("dateTimeLabel", new Model<String>(
				dateFormat.format(new Date())));
		dateTimeLabel.add(new AjaxTimerBehaviour());
		this.add(dateTimeLabel);
	}
	
	private class AjaxTimerBehaviour extends AbstractAjaxTimerBehavior {

		/** Generated Serial Version ID. */
		private static final long serialVersionUID = 8387360410193512237L;

		public AjaxTimerBehaviour() {
			super(Duration.ONE_SECOND);
		}

		@Override
		protected void onTimer(AjaxRequestTarget target) {
			dateTimeLabel.setDefaultModelObject(dateFormat.format(new Date()));
			target.addComponent(dateTimeLabel);
		}
		
	}
}
