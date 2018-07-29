/**
 * 
 */
package com.iiht.wicket.testers;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.iiht.wicket.application.TelecomAppTest;
import com.iiht.wicket.webpage.TestPageOne;

/**
 * @author Vignesh Durairaj
 *
 */
public class WebPageTester extends BaseTester{

	@Autowired
	private TelecomAppTest myTelecomAppTest;
	
	private WicketTester pageTester;
	
	@Before
	public void initTester() {
		pageTester = new WicketTester(myTelecomAppTest);
	}
	
	@Test
	public void homePageTest() {
		pageTester.startPage(TestPageOne.class);
		pageTester.assertRenderedPage(TestPageOne.class);
	}
}
