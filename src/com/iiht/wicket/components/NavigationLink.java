/**
 * 
 */
package com.iiht.wicket.components;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;

/**
 * @author Vignesh Durairaj
 *
 */
public class NavigationLink extends Link<Void> {

	/** Generated Serial Version ID. */
	private static final long serialVersionUID = 5068827392806591861L;
	
	private Class<? extends WebPage> pageClass;

	public NavigationLink(String id) {
		super(id);
	}
	
	public NavigationLink (final String id, final Class<? extends WebPage> pageClass) {
		super(id);
		this.pageClass = pageClass;
	}

	@Override
	public void onClick() {
		setResponsePage(pageClass);
	}

}
