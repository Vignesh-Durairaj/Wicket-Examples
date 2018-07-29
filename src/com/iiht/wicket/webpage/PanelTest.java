/**
 * 
 */
package com.iiht.wicket.webpage;

import org.apache.wicket.markup.html.WebPage;

import com.iiht.wicket.panels.AjaxTestPanel;
import com.iiht.wicket.panels.ValidationPanel;

/**
 * @author Vignesh Durairaj
 *
 */
public class PanelTest extends WebPage {

	public PanelTest() {
		this.add(new ValidationPanel("validationPanel"));
		this.add(new AjaxTestPanel("ajaxTextPanel"));
	}
}
