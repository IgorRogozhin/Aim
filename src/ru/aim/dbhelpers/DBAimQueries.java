package ru.aim.dbhelpers;



import ru.aim.models.WebAim;
import ru.aim.models.WebAimGroup;
import ru.aim.models.WebGroup;


/**
 * 
 * Encapsulates any inquiry to DB
 *
 */
public class DBAimQueries 
{
	/**
	 * Get the query for getting actual User's aims, 
	 * actual, i.e. WebAim's attribute 'archive'=0
	 * @return String valid query to select aims
	 */
	public static String getUserAimsByDeadline(int userId)
	{
		return String.format("select * from user_aim " 
				+ "where users_id = '%s' AND archive = 0 " +
				"order by deadline ASC", userId);
	}


	/**
	 * Get the Web User by Login And Password.
	 * @param userName the login to match
	 * @param password the password to match
	 * @return String query to find any match to params.
	 */
	public static String getWebUserByUsernameAndPassword(String userName, String password)
	{
	
		return String.format("SELECT * FROM users "
						+ "where login = '%s' "
						+ "and password = '%s' "
						, userName, password);
	}

	/**
	 * Get the Web User by Login
	 * @param userName
	 * @return String
	 */
	public static String getWebUserByUsername(String userName) {

		return String.format("SELECT * FROM users "
				+ "where login = '%s' ", userName);
							
	}

	/**
	 * INSERT INTO users (String userName, String password)...
	 * @param userName
	 * @param password
	 * @return String
	 */
	public static String addWebUserByUserNameAndPassword(String userName, String password) {
		
		
		return String.format("INSERT INTO users (login, password) "
								+ "VALUES ('%s', '%s') "
								, userName, password);
	}
	
	/**
	 * Search for WebAim with aimName and userId filters
	 * @param aimName
	 * @param userId
	 * @return String
	 */
	public static String getWebAimByAimName(int userId, String aimName) {

		return String.format("SELECT * FROM user_aim "
				+ "where users_id = '%d' "
				+ "and name = '%s' "
				, userId, aimName);
							
	}

	/**
	 * According to has an aim date type or not,
	 * creates query to insert data:
	 * INSERT INTO user_aim...
	 * @param aim
	 * @param deadline_tmp
	 * @return String
	 */
	public static String insertAim(WebAim aim, String deadline_tmp) {
		
		if(deadline_tmp.equals("")) {
			return String.format("INSERT INTO user_aim "
					+ "(users_id, name, description, control) "
					+ "VALUES ('%d','%s', '%s', '%s')"
						, aim.getUserId()
						, aim.getName()
						, aim.getDescription()
						, aim.getControl());
						
		} else
			return String.format("INSERT INTO user_aim "
					+ "(users_id, name, description, control, deadline) "
					+ "VALUES ('%d','%s', '%s', '%s', '%s')"
						, aim.getUserId()
						, aim.getName()
						, aim.getDescription()
						, aim.getControl()
						, aim.getDeadline());
	}


	/**
	 * DELETE FROM user_aim...
	 * @param userId
	 * @param nameOfAim
	 * @param description
	 * @return String
	 */
	public static String deleteAim(int userId, String nameOfAim, String description) {
		
		return String.format("DELETE FROM user_aim "
				+ "WHERE users_id='%d'AND name='%s' AND description='%s' "
					, userId
					, nameOfAim
					, description);
	}


	/**
	 * UPDATE user_aim SET archive=1...
	 * @param userId
	 * @param nameOfAim
	 * @param description
	 * @return String
	 */
	public static String markAsArchived(int userId, String nameOfAim, String description) {
		
		return String.format("UPDATE user_aim SET archive=1 "
				+ "WHERE users_id='%d'AND name='%s' AND description='%s' "
					, userId
					, nameOfAim
					, description);
	}

	
	/**
	 * UPDATE users SET solved=solved+1...
	 * @param userId
	 * @return String
	 */
	public static String markAsSolved(int userId) {

		return String.format("UPDATE users SET solved=solved+1 "
				+ "WHERE id='%d' "
					, userId);
	}

	
	/**
	 * UPDATE user_aim SET result=1...
	 * @param userId
	 * @param nameOfAim
	 * @param description
	 * @return String
	 */
	public static String markAsComplited(int userId, String nameOfAim, String description) {
		
		return String.format("UPDATE user_aim SET result=1 "
				+ "WHERE users_id='%d'AND name='%s' AND description='%s' "
					, userId
					, nameOfAim
					, description);
	}

	/**
	 * Get the query for getting archive User's aims, 
	 * archive, i.e. WebAim's attribute 'archive'=1
	 * @param userId
	 * @return String
	 */
	public static String getArchiveUserAimsByDeadline(int userId) {
		return String.format("SELECT * FROM user_aim " 
				+ "WHERE users_id = '%s' AND archive = 1 " +
				"ORDER BY deadline ASC", userId);
	}
	
	/**
	 * INSERT INTO user_group (users_id, name, password, creator)...
	 * @param group
	 * @return String
	 */
	public static String insertGroup(WebGroup group) {
		
		int creator = 0;
		 if(group.isCreator()) creator = 1;
		 
		return String.format("INSERT INTO user_group "
					+ "(users_id, name_group, password, creator) "
					+ "VALUES ('%d','%s', '%s', '%d')"
						, group.getUserId()
						, group.getName()
						, group.getPassword()
						, creator);
						
		}


	/**
	 * Select WebGroup by name of group and where creator=true
	 * @param name
	 * @return String
	 */
	public static String getWebGroupByName(String name) {
		
		return String.format("SELECT * FROM user_group "
				+ "WHERE name_group = '%s' AND creator=1 ", name);
	}


	/**
	 * SELECT * FROM user_group WHERE users_id = uid AND name = groupName
	 * @param userId
	 * @param nameOfGroup
	 * @return String
	 */
	public static String getWebGroupByUserIdAndGroup(int userId, String nameOfGroup) {
		return String.format("SELECT * FROM user_group " 
				+ "WHERE users_id = '%d' AND name_group = '%s' "
				, userId, nameOfGroup);
	}


	/**
	 * SELECT * FROM user_group WHERE users_id=userId
	 * @param userId
	 * @return String
	 */
	public static String getGroupsByUserId(int userId) {
		return String.format("SELECT * FROM user_group " 
				+ "WHERE users_id = '%d' ", userId);
	}


	/**
	 * INSERT INTO aim_group (users_aim_id, users_group_id)..."
	 * @param aimGroup
	 * @return String
	 */
	public static String insertAimGroup(WebAimGroup aimGroup) {
		
		if (!(aimGroup.getGroupId() == 0)) {
			
			return String.format("INSERT INTO aim_group "
					+ "(user_aim_id, user_group_id) "
					+ "VALUES ('%d','%d')"
						, aimGroup.getAimId()
						, aimGroup.getGroupId());
		} else {
			
			return String.format("INSERT INTO aim_group "
					+ "(user_aim_id) "
					+ "VALUES ('%d')"
						, aimGroup.getAimId());
		}
			
		
	}


	/**
	 * SELECT * FROM aim_group WHERE user_group_id IS NOT NULL
	 * @return String
	 */
	public static String selectAimGroupNotNull() {
		
		return new String("SELECT * FROM aim_group WHERE user_group_id IS NOT NULL");
	}


	/**
	 * Returns all information from User,Aim,Group by group name
	 * by deadline
	 * @param groupName 
	 * @param groupId
	 * @return String
	 */
	public static String selectCollectorForGroup(String groupName) {
	
		return String.format("SELECT * FROM user_group g "
				+ "INNER JOIN aim_group ag ON g.id=ag.user_group_id " 
				+ "INNER JOIN user_aim a ON ag.user_aim_id=a.id "
				+ "INNER JOIN users u ON u.id=a.users_id "
				+ "WHERE g.name_group='%s' ORDER BY a.deadline"
				, groupName);
	}


	/**
	 * Delete WebUser from a given WebGroup
	 * @param userId
	 * @param groupId
	 * @return String
	 */
	public static String signoutFromGroup(int userId, int groupId) {
		return String.format("DELETE FROM user_group "
				+ "WHERE users_id='%d'AND id='%d' AND creator=0"
					, userId
					, groupId);
	}


	/**
	 * Delete a WebGroup by name of the group
	 * @param userId
	 * @param groupId
	 * @return
	 */
	public static String deleteGroup(String groupName) {
		return String.format("DELETE FROM user_group "
				+ "WHERE id>0 AND name_group='%s' "
					, groupName);
	}


	/**
	 * Returns all information from User,Aim,Group by group name
	 * by rate
	 * @param groupName
	 * @return
	 */
	public static String selectCollectorForGroupByRate(String groupName) {
		return String.format("SELECT * FROM users u "
				+ "INNER JOIN user_group g ON u.id=g.users_id " 
				+ "WHERE g.name_group='%s' ORDER BY u.solved DESC"
				, groupName);
		}
	
	
	
	
}
