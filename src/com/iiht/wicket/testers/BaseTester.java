/**
 * 
 */
package com.iiht.wicket.testers;

import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.iiht.wicket.DAO.SpringDAO;
import com.iiht.wicket.model.TelecomModel;
import com.iiht.wicket.model.TypeModel;

/**
 * @author Vignesh Durairaj
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:WebContent/WEB-INF/spring_config_iiht.xml"})
public class BaseTester {

	@Autowired
	private SpringDAO springDao;
	
	@BeforeClass
	public static void init() {
		System.out.println("About to execute this test class.");
	}
	
	@Before
	public void postInit() {
		System.out.println("A test case is about to get executed");
	}
	
	@Test(timeout = 50)
	public void testForTimeOut() {
		Assert.assertNotNull(springDao);
		Assert.assertNull(null);
	}
	
	@Test(expected = NumberFormatException.class)
	public void testForException() {
		testForTimeOut();
		TypeModel testType = springDao.getSampleType();
		Integer.parseInt(testType.getTypeName());
	}
	
	@Test
	public void testEqualNumbers() {
		Assert.assertEquals("Number should be equal!", 0, 123 * 0);
	}
	
	@Ignore ("Ignored as a demonstration!")
	@Test
	public void testIgnore() {
		List<TelecomModel> providerList = springDao.getServiceProvidersList();
		if  (providerList instanceof List<?>)
			System.out.println("The object is of valid list type");
		Assert.assertTrue(null != providerList);
	}
	
	@Test
	public void testFailureCases() {
		List<Void> testList = Arrays.asList(new Void[] {});
		
		try {
			testList.get(2);
			TestCase.fail();
		} catch(Exception e) {
			Assert.assertTrue(null != e);
			Assert.assertFalse(testList == null);
		}
	}
	
	@Test
	public void testEquality() {
		TelecomModel telecomModel = springDao.getSampleService();
		List<TelecomModel> telecomList = springDao.getServiceProvidersList();
		String testString = "TEST";
		
		Assert.assertSame("Objects should be same !", testString, "TEST");
		Assert.assertNotSame(telecomModel, telecomList.get(0));
	}
	
	@After
	public void afterEachTest() {
		System.out.println("Test Case executed!");
	}
	
	@AfterClass
	public static void afterComplete() {
		System.out.println("Finished executing the class !");
	}
}
