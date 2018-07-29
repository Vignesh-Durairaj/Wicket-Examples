/**
 * 
 */
package com.iiht.wicket.webpage;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

/**
 * @author 1444058
 *
 */
public class MyExceptionPage extends WebPage {
	
	public MyExceptionPage(final Exception e) {
		StringBuffer errorMessage = new StringBuffer(0);
		StringBuffer exceptionMessage = new StringBuffer(0);
		
		for (StackTraceElement stackTrace : e.getStackTrace())
			errorMessage.append(stackTrace).append(System.getProperty("line.separator"));
		
		String[] myTrace = ExceptionUtils.getStackFrames(e);
		for (int i = 0; i < myTrace.length; i++)
			exceptionMessage.append(myTrace[i]).append(System.getProperty("line.separator"));
		
		this.add(new Label("exceptionString", errorMessage.toString()).setEscapeModelStrings(Boolean.FALSE));
		this.add(new Label("exceptionMessage", exceptionMessage.toString()).setEscapeModelStrings(Boolean.FALSE));
	}
	
}
