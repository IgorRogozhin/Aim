package ru.aim.models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class WebGroupTest {

	WebGroup testGroup;
	
	private int id = 1;
	private int uid = 2;
	private String name = "group";
	private String pwd = "password";
	private boolean creator = true;
	
	@Before
	public void setUp() throws Exception {
		testGroup = new WebGroup();
	}

	@Test
	public void testDefaultConstructor() {
		assertNotNull("Could not create default WebGroup", testGroup);
	}
	

	@Test
	public void testMutatorsAndAccessors() {
		
		testGroup.setId(id);
		assertEquals("Could not set ID as expected"
				, id, testGroup.getId());
		
		testGroup.setUserId(uid);
		assertEquals("Could not set UserId as expected"
				, uid, testGroup.getUserId());
		
		testGroup.setName(name);
		assertEquals("Could not set name as expected"
				, name, testGroup.getName());
		
		testGroup.setPassword(pwd);
		assertEquals("Could not set User's password as expected"
				, pwd, testGroup.getPassword());
		
		testGroup.setCreator(creator);
		assertEquals("Could not set creator for User as expected"
				, creator, testGroup.isCreator());
		
	}
	
	@Test
	public void testToString() {
		
		testGroup.setId(id);
		testGroup.setUserId(uid);
		testGroup.setName(name);
		testGroup.setPassword(pwd);
		testGroup.setCreator(creator);
	
		String retStr = testGroup.toString();
		
		assertTrue("To String does not contain expected value for id "
				, retStr.contains(String.format("%d", id)));
		
		assertTrue("To String does not contain expected value for uid "
				, retStr.contains(String.format("%d", uid)));
		
		assertTrue("To String does not contain expected value for name "
				, retStr.contains(String.format("%s", name)));
		
		assertTrue("To String does not contain expected value for pwd "
				, retStr.contains(String.format("%s", pwd)));
		
		assertTrue("To String does not contain expected value for creator "
				, retStr.contains(String.format("%s", creator)));
		
	}
}
