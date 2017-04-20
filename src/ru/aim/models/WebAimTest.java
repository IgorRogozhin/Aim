package ru.aim.models;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

public class WebAimTest {
	
	WebAim testAim;
	private int id = 1;
	private int userId = 1;
	private String name = "name";
	private String description = "description";
	private String control = "control";
	private LocalDate deadline = LocalDate.of(2017, 01, 01);
	private boolean archive = true;
	private boolean result = false;
	
	@Before
	public void setUp() throws Exception {
		testAim = new WebAim();
	}
		

	@Test
	public void testDefaultConstructor() {
		assertNotNull("Could not create default WebAim", testAim);
	}

	@Test
	public void testMutatorsAndAccessors() {
		
		testAim.setId(id);
		assertEquals("Could not set ID as expected"
				, id, testAim.getId());
		
		testAim.setUserId(userId);
		assertEquals("Could not set UserID as expected"
				, userId, testAim.getUserId());
		
		testAim.setName(name);
		assertEquals("Could not set name as expected"
				, name, testAim.getName());
		
		testAim.setDescription(description);
		assertEquals("Could not set description as expected"
				, description, testAim.getDescription());
		
		testAim.setControl(control);
		assertEquals("Could not set control as expected"
				, control, testAim.getControl());
		
		testAim.setDeadline(deadline);
		assertEquals("Could not set deadline as expected"
				, deadline, testAim.getDeadline());
		
		testAim.setArchive(archive);
		assertEquals("Could not set archive as expected"
				, archive, testAim.isArchive());
		
		testAim.setResult(result);
		assertEquals("Could not set result as expected"
				, result, testAim.isResult());
				
		}


	@Test
	public void testToString() {
		
		testAim.setId(id);
		testAim.setUserId(userId);
		testAim.setName(name);
		testAim.setDescription(description);
		testAim.setControl(control);
		testAim.setDeadline(deadline);
		testAim.setArchive(archive);
		testAim.setResult(result);
		
		String retStr = testAim.toString();
		
		assertTrue("To String does not contain expected value for id "
				, retStr.contains(String.format("%d", id)));
		
		assertTrue("To String does not contain expected value for userId "
				, retStr.contains(String.format("%d", userId)));
		
		assertTrue("To String does not contain expected value for name "
				, retStr.contains(String.format("%s", name)));
		
		assertTrue("To String does not contain expected value for description "
				, retStr.contains(String.format("%s", description)));
		
		assertTrue("To String does not contain expected value for control "
				, retStr.contains(String.format("%s", control)));
		
		assertTrue("To String does not contain expected value for deadline "
				, retStr.contains(deadline.toString()));
		
		assertTrue("To String does not contain expected value for archive "
				, retStr.contains(String.format("%s", archive)));
		
		assertTrue("To String does not contain expected value for result "
				, retStr.contains(String.format("%s", result)));
	}

}
