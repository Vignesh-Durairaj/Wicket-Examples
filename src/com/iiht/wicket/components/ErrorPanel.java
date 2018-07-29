/**
 * 
 */
package com.iiht.wicket.components;

import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

/**
 * @author Vignesh Durairaj
 *
 */
public class ErrorPanel extends FeedbackPanel {

	/** Generated Serial Version ID. */
	private static final long serialVersionUID = 1113132239533431940L;

	private Boolean isErrorMessage = Boolean.TRUE;
	
	public ErrorPanel(String id) {
		super(id);
		initErrorPanel();
	}
	
	@Override
	protected void onBeforeRender() {
		super.onBeforeRender();
		this.add(new SimpleAttributeModifier("style", isErrorMessage ? "color:red;" : "color:green;"));
	}
	
	private void initErrorPanel() {
		this.setOutputMarkupPlaceholderTag(Boolean.TRUE);
	}

	/**
	 * @return the isErrorMessage
	 */
	public Boolean getIsErrorMessage() {
		return isErrorMessage;
	}

	/**
	 * @param isErrorMessage the isErrorMessage to set
	 */
	public void setIsErrorMessage(Boolean isErrorMessage) {
		this.isErrorMessage = isErrorMessage;
	}
}
