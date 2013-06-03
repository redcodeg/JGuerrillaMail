/**
 * 
 */
package com.guerrillamail.www;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import java.util.ArrayList;

/**
 * Test class for email address.
 * @author <a reef="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class GuerrillaMailTest {
	
	private GuerrillaMail tester;
	private ArrayList<EMail> emails;
	private ArrayList<Integer> emailIdsToDelete;
	
	/**
	 * Setup the test.
	 * @throws Exception 
	 */
	@org.junit.Before
	public void testSetup() throws Exception{
		tester = new GuerrillaMail();
		emails = new ArrayList<EMail>();
		emailIdsToDelete = new ArrayList<Integer>();
		emailIdsToDelete.add(1);
		emailIdsToDelete.add(2);
	}

	/**
	 * Test for email address.
	 */
	@org.junit.Test
	public void testEmailAddress() {
		try{
			assertNotNull("Email address must not be null", tester.getEmailAddress());
			
			tester.setEmailUser("pingas");
			assertNotNull("Email address must not be null after changing the user name", tester.getEmailAddress());
			
			assertNotNull("Email list must not be null after reading messages", tester.getEmailList());
			
			assertNotNull("Email list must not be null after checking messages",  tester.checkEmail());
			
			assertNotNull("Email must not be null after fetching message",  tester.fetchEmail(1));
			tester.delEmail(emailIdsToDelete);

		} catch(Exception ex){
			ex.printStackTrace();
			fail(ex.getLocalizedMessage());
		}
	}
	
	/**
	 * Test for account expired with extend.
	 * @throws Exception 
	 */
	@org.junit.Test(expected=Exception.class)
	public void testAccountExpiredWithExtend() throws Exception {
		forget();
		tester.extend();
	}
	
	/**
	 * Test for double forget.
	 * @throws Exception 
	 */
	public void testDoubleForget() throws Exception {
		forget();
		forget();
	}
	
	
	/**
	 * Test for account expired with getEmailAddress.
	 * @throws Exception 
	 */
	@org.junit.Test
	public void testAccountExpiredWithGetEmailAddress() throws Exception {
		forget();
		assertNotNull("Email address must not be null", tester.getEmailAddress());
	}
	
	
	/**
	 * Test for account expired with setEmailUser.
	 * @throws Exception 
	 */
	@org.junit.Test
	public void testAccountExpiredWithSetEmailUser() throws Exception {
		forget();
		tester.setEmailUser("pingas");
		assertNotNull("Email address must not be null", tester.getEmailAddress());
	}
	
	
	/**
	 * Delete all messages and read the number of the emails.
	 * @throws Exception
	 */
	@org.junit.Test
	public void testDeleteAllMessagesAndReadNumber() throws Exception{
		tester.delEmail(emailIdsToDelete);
		emails = tester.getEmailList();
		assertSame("The emails must be 0", emails.size(), 0);
	}
	
	
	/**
	 * Forget the email address.
	 */
	private void forget(){
		try {
			tester.forgetMe();
		} catch (Exception ex) {
			ex.printStackTrace();
			fail(ex.getLocalizedMessage());
		}
	}
	

}
