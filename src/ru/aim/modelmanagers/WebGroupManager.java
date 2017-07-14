package ru.aim.modelmanagers;

import java.io.IOException;
import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;

import ru.aim.dbhelpers.DBAimQueries;
import ru.aim.dbmodels.DBManager;
import ru.aim.models.WebGroup;

import ru.aim.utilities.AimManagerUtils;

/**
 * 
 * Manages a WebGroup object creation cycle (check-creation-validation-delete)
 * by its methods
 * 
 */
public class WebGroupManager implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Inserts WebGroup object into DB
	 * 
	 * @param dbm
	 * @param userId
	 * @param name
	 * @param password
	 * @return WebGroup or null
	 */
	public WebGroup insertGroup(DBManager dbm, int userId, String name, String password) {

		if (!checkForConnection(dbm)) {
			return null;
		}

		if (AimManagerUtils.stringNullOrEmpty(name) || AimManagerUtils.stringNullOrEmpty(password)) {
			return null;
		}

		WebGroup group = new WebGroup();
		group.setUserId(userId);
		group.setName(name);
		group.setPassword(password);
		group.setCreator(true);

		String query = DBAimQueries.insertGroup(group);

		try {
			// execute
			dbm.ExecuteNonQuery(query);

		} catch (Exception ex) {
			// log it
			return null;
		}
		return group;

	}

	/**
	 * Common method to check connection with DB, returns <b>true</b> if connect
	 * is OK
	 * 
	 * @param dbm
	 * @return boolean
	 */
	private boolean checkForConnection(DBManager dbm) {

		if (dbm == null)
			return false;
		if (!dbm.isConnected()) {
			if (!dbm.openConnection()) {
				try {
					throw new IOException("Could not connect to database and open connection");
				} catch (IOException e) {
					e.getMessage();
				}
				return false;
			}
		}
		return true;
	}

	/**
	 * Returns WebGroup if such object with given name already exist
	 * 
	 * @param dbm
	 * @param name
	 * @return WebGroup or <b>null</b>
	 */
	public WebGroup getWebGroupByName(DBManager dbm, String name) {
		if (!checkForConnection(dbm)) {
			return null;
		}
		WebGroup wg = new WebGroup();
		// see if there is a matching WebGroup
		String query = DBAimQueries.getWebGroupByName(name);
		try {
			ResultSet rs = dbm.ExecuteResultSet(query);
			while (rs.next()) {
				wg.setId(rs.getInt("id"));
				wg.setUserId(rs.getInt("users_id"));
				wg.setName(rs.getString("name_group"));
				wg.setPassword(rs.getString("password"));
				wg.setCreator(rs.getBoolean("creator"));
			}

			if (wg.getId() == 0) {
				return null;
			}
			return wg;
		} catch (Exception ex) {
			// log it
			return null;
		}
	}

	/**
	 * Returns a WebGroup if a WebUser has already joined
	 * 
	 * @param dbm
	 * @param userId
	 * @return WebGroup or null
	 */
	public WebGroup getWebGroupByUserIdAndGroup(DBManager dbm, int userId, String nameOfGroup) {
		if (!checkForConnection(dbm)) {
			return null;
		}
		WebGroup wg = new WebGroup();
		// see if there is a matching WebGroup
		String query = DBAimQueries.getWebGroupByUserIdAndGroup(userId, nameOfGroup);
		try {
			ResultSet rs = dbm.ExecuteResultSet(query);

			while (rs.next()) {
				wg.setId(rs.getInt("id"));
				wg.setUserId(rs.getInt("users_id"));
				wg.setName(rs.getString("name_group"));
				wg.setPassword(rs.getString("password"));
				wg.setCreator(rs.getBoolean("creator"));
			}
			if (wg.getId() == 0) {
				return null;
			}
			return wg;
		} catch (Exception ex) {
			// log it
			return null;
		}
	}

	/**
	 * Returns a WebGroup with a WebUser signed to it
	 * 
	 * @param dbm
	 * @param userId
	 * @param name
	 * @param password
	 * @return WebGroup
	 */
	public WebGroup joinGroup(DBManager dbm, int userId, String name, String password) {

		if (!checkForConnection(dbm)) {
			return null;
		}

		if (AimManagerUtils.stringNullOrEmpty(name) || AimManagerUtils.stringNullOrEmpty(password)) {
			return null;
		}

		WebGroup group = new WebGroup();
		group.setUserId(userId);
		group.setName(name);
		group.setPassword(password);
		group.setCreator(false);

		String query = DBAimQueries.insertGroup(group);

		try {
			// execute
			dbm.ExecuteNonQuery(query);

		} catch (Exception ex) {
			// log it
			return null;
		}
		return group;
	}

	/**
	 * Returns ArrayList of WebGroups for a WebUser
	 * 
	 * @param dbm
	 * @param userId
	 * @return ArrayList<WebGroup> or null
	 */
	public ArrayList<WebGroup> getGroupsByUser(DBManager dbm, int userId) {

		if (!checkForConnection(dbm)) {
			return null;
		}

		// build a list of aims objects using the query
		ArrayList<WebGroup> groups = new ArrayList<>();

		String query = DBAimQueries.getGroupsByUserId(userId);

		try {
			ResultSet rs = dbm.ExecuteResultSet(query);

			while (rs.next()) {
				WebGroup group = new WebGroup();
				group.setId(rs.getInt("id"));
				group.setUserId(rs.getInt("users_id"));
				group.setName(rs.getString("name_group"));
				group.setPassword(rs.getString("password"));
				;
				group.setCreator(rs.getBoolean("creator"));

				groups.add(group);
			}

			if (groups.isEmpty()) {
				return null;
			}

		}

		catch (Exception ex) {
			// log it
			return null;
		}
		return groups;
	}

	/**
	 * Erase User from defined WebGroup
	 * 
	 * @param dbm
	 * @param userId
	 * @param groupId
	 */
	public boolean signoutUserFromGroup(DBManager dbm, int userId, int groupId) {
		if (!checkForConnection(dbm)) {
			return false;
		}
		String query = DBAimQueries.signoutFromGroup(userId, groupId);

		try {
			// execute
			dbm.ExecuteNonQuery(query);
			return true;
		} catch (Exception ex) {
			// log it
			return false;
		}
	}

	/**
	 * Delete a WebGroup by name of the group, returns <b>true</b> if Group's
	 * been deleted
	 * 
	 * @param dbm
	 * @param userId
	 * @param groupId
	 * @return boolean
	 */
	public boolean deleteGroup(DBManager dbm, String groupName) {
		if (!checkForConnection(dbm)) {
			return false;
		}
		String query = DBAimQueries.deleteGroup(groupName);

		try {
			// execute
			dbm.ExecuteNonQuery(query);
			return true;
		} catch (Exception ex) {
			// log it
			return false;
		}
	}
}
