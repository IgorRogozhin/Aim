package ru.aim.models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class WebAimGroupTest {

	WebAimGroup testAimGroup;
	
	private int id = 1;
	private int aimId = 2;
	private int groupId = 3;
		
	@Before
	public void setUp() throws Exception {
		testAimGroup = new WebAimGroup();
	}

	@Test
	public void testDefaultConstructor() {
		assertNotNull("Could not create default WebAimGroup", testAimGroup);
	}
	

	@Test
	public void testMutatorsAndAccessors() {
		
		testAimGroup.setId(id);
		assertEquals("Could not set ID as expected"
				, id, testAimGroup.getId());
		
		testAimGroup.setAimId(aimId);
		assertEquals("Could not set aimId as expected"
				, aimId, testAimGroup.getAimId());
		
		testAimGroup.setGroupId(groupId);
		assertEquals("Could not set groupId as expected"
				, groupId, testAimGroup.getGroupId());
				
		
	}
	
	@Test
	public void testToString() {
		
		testAimGroup.setId(id);
		testAimGroup.setAimId(aimId);
		testAimGroup.setGroupId(groupId);
		
	
		String retStr = testAimGroup.toString();
		
		assertTrue("To String does not contain expected value for id "
				, retStr.contains(String.format("%d", id)));
		
		assertTrue("To String does not contain expected value for aimId "
				, retStr.contains(String.format("%d", aimId)));
		
		assertTrue("To String does not contain expected value for groupId "
				, retStr.contains(String.format("%d", groupId)));
		
				
	}
}
