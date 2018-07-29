/**
 * 
 */
package com.iiht.wicket.webpage;

import com.iiht.wicket.others.SampleBorder;
import com.iiht.wicket.panels.ValidationPanel;


/**
 * @author Vignesh Durairaj
 *
 */
public class TestPageTwo extends BasePage {

	public TestPageTwo() {
		
		SampleBorder borderElement = new SampleBorder("borderElement");
		borderElement.getBodyContainer().add(new ValidationPanel("validationPanel"));
		
		this.add(borderElement);
	}
	
}
