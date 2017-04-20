package ru.aim.models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class WebUserTest {

	WebUser testUser;
	private int id = 3;
	private String uid = "login";
	private String pwd = "password";
	private int solved = 1;
	private int failed = 0;
	
	@Before
	public void setUp() throws Exception {
		testUser = new WebUser();
	}

	@Test
	public void testDefaultConstructor() {
		assertNotNull("Could not create default WebUser", testUser);
	}
	

	@Test
	public void testMutatorsAndAccessors() {
		
		testUser.setId(id);
		assertEquals("Could not set ID as expected"
				, id, testUser.getId());
		
		testUser.setUserId(uid);
		assertEquals("Could not set UserId as expected"
				, uid, testUser.getUserId());
		
		testUser.setPassword(pwd);
		assertEquals("Could not set User's password as expected"
				, pwd, testUser.getPassword());
		
		testUser.setSolved(solved);
		assertEquals("Could not set solved for User's as expected"
				, solved, testUser.getSolved());
		
		testUser.setFailed(failed);
		assertEquals("Could not set failed for User's as expected"
				, failed, testUser.getFailed());
		
	}
	
	@Test
	public void testToString() {
		
		testUser.setId(id);
		testUser.setUserId(uid);
		testUser.setPassword(pwd);
		testUser.setSolved(solved);
		testUser.setFailed(failed);
		
	
		String retStr = testUser.toString();
		
		assertTrue("To String does not contain expected value for id "
				, retStr.contains(String.format("%d", id)));
		
		assertTrue("To String does not contain expected value for uid "
				, retStr.contains(String.format("%s", uid)));
		
		assertTrue("To String does not contain expected value for pwd "
				, retStr.contains(String.format("%s", pwd)));
		
		assertTrue("To String does not contain expected value for solved "
				, retStr.contains(String.format("%d", solved)));
		
		assertTrue("To String does not contain expected value for solved "
				, retStr.contains(String.format("%d", failed)));
		
	}
}
