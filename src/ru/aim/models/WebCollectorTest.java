package ru.aim.models;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

//Awful test case, update ASAP
public class WebCollectorTest {

	WebCollector testCollector;
	private WebUser webUser = new WebUser();
	private WebAim webAim = new WebAim();
	private WebGroup webGroup = new WebGroup();
	
	private int id = 3;
	private String uid = "login";
	private String pwd = "password";
	private int solved = 1;
	private int failed = 0;
	
	private int idAim = 1;
	private int userId = 1;
	private String name = "name";
	private String description = "description";
	private String control = "control";
	private LocalDate deadline = LocalDate.of(2017, 01, 01);
	private boolean archive = true;
	private boolean result = false;
	
	private int idGroup = 1;
	private int usersId = 2;
	private String nameGroup = "group";
	private String pwdGroup = "password";
	private boolean creator = true;
	
	@Before
	public void setUp() throws Exception {
		testCollector = new WebCollector();
	}
	
	@Before
	public void initializeUser() {
			webUser.setId(id);
			webUser.setUserId(uid);
			webUser.setPassword(pwd);
			webUser.setSolved(solved);
			webUser.setFailed(failed);
		}
	@Before
	public void initializeAim() {
			webAim.setId(idAim);
			webAim.setUserId(userId);
			webAim.setName(name);
			webAim.setDescription(description);
			webAim.setControl(control);
			webAim.setDeadline(deadline);
			webAim.setArchive(archive);
			webAim.setResult(result);
		}
	@Before
	public void initializeGroup() {
			webGroup.setId(idGroup);
			webGroup.setUserId(usersId);
			webGroup.setName(nameGroup);
			webGroup.setPassword(pwdGroup);
			webGroup.setCreator(creator);
		}

	@Test
	public void testDefaultConstructor() {
		assertNotNull("Could not create default WebCollector", testCollector);
	}
	

	@Test
	public void testMutatorsAndAccessors() {
		
		testCollector.setWebUser(webUser);
		assertEquals("Could not set WebUser as expected"
				, webUser, testCollector.getWebUser());
		
		testCollector.setWebAim(webAim);
		assertEquals("Could not set WebAim as expected"
				, webAim, testCollector.getWebAim());
		
		testCollector.setWebGroup(webGroup);
		assertEquals("Could not set WebGroup as expected"
				, webGroup, testCollector.getWebGroup());
				
		
	}
	
	@Test
	public void testToString() {
		
		testCollector.setWebUser(webUser);
		testCollector.setWebAim(webAim);
		testCollector.setWebGroup(webGroup);
		
	
		String retStr = testCollector.toString();
		
		assertTrue("To String does not contain expected value for webUser "
				, retStr.contains(webUser.toString()));
		
		assertTrue("To String does not contain expected value for webAim "
				, retStr.contains(webAim.toString()));
		
		assertTrue("To String does not contain expected value for webGroup "
				, retStr.contains(webGroup.toString()));
		
				
	}
}
