/**
 * 
 */
package com.iiht.wicket.webpage;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.iiht.wicket.components.NavigationLink;
import com.iiht.wicket.model.TelecomModel;
import com.iiht.wicket.others.CustomSession;

/**
 * @author Vignesh Durairaj
 *
 */
@SuppressWarnings("null")
public class TelecomHome extends WebPage{

	@SpringBean
	private TelecomModel newService;
	
	public TelecomHome() {
		addComponents();
	}
	
	private void addComponents () {
		
		CustomSession.get().setTelecomModel(newService);
		
		this.add(new Label("welcomeNote", "This application is used to test our wicket application."));
		this.add(new Label("modeledWelcomeNote", new Model<String>("We'll Try out all the features available in the Wicket Application.")));
		this.add(new Link<Void>("modelTestLink"){

			/** Generated Serial Version ID. */
			private static final long serialVersionUID = 6597748042913228793L;

			@Override
			public void onClick() {
				setResponsePage(ModelTestPage.class);
			}
			
		});
		
		this.add(new Link<Void>("exceptionTestLink"){

			/** Generated Serial Version ID. */
			private static final long serialVersionUID = 6597748042913228793L;

			
			@Override
			public void onClick() {
				String nullString = null;
				System.out.println(nullString.toUpperCase());
			}
			
		});
		this.add(new FormTestLink("formTestLink"));
		this.add(new NavigationLink("panelTestLink", PanelTest.class));
		this.add(new NavigationLink("tableTestLink", TableTest.class));
		this.add(new NavigationLink("showModalLink", ModalTestPage.class));
		this.add(new NavigationLink("pageOneLink", TestPageOne.class));
		this.add(new NavigationLink("pageTwoLink", TestPageTwo.class));
		this.add(new NavigationLink("tagsTestLink", WicketTagsPage.class));
		this.add(new ExternalLink("referenceLink", "https://www.google.com"));
	}
	
	private class FormTestLink extends Link<Void> {
		
		/** Generated Serial Version ID. */
		private static final long serialVersionUID = -9086062135759704781L;

		public FormTestLink(String id) {
			super(id);
		}

		@Override
		public void onClick() {
			setResponsePage(FormTestPage.class);
		}
		
	}
}
