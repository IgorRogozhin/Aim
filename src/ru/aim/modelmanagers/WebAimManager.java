package ru.aim.modelmanagers;

import java.io.IOException;
import java.io.Serializable;
import java.sql.ResultSet;

import java.time.LocalDate;
import java.util.ArrayList;

import ru.aim.dbhelpers.DBAimQueries;
import ru.aim.dbmodels.DBManager;
import ru.aim.models.WebAim;

import ru.aim.utilities.AimManagerUtils;

/**
 * 
 * Manages a WebAim object creation cycle (check-creation-validation-delete) by
 * its methods
 * 
 */
public class WebAimManager implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Insert WebAim object into DB
	 * 
	 * @param dbm
	 * @param userId
	 * @param name
	 * @param description
	 * @param control
	 * @param deadline
	 * @return WebAim or null
	 */
	public WebAim insertAim(DBManager dbm, int userId, String name, String description, String control,
			LocalDate deadline, String deadline_tmp) {

		if (!checkForConnection(dbm)) {
			return null;
		}

		if (AimManagerUtils.stringNullOrEmpty(name) || AimManagerUtils.stringNullOrEmpty(description)) {
			return null;
		}

		WebAim aim = new WebAim();
		aim.setUserId(userId);
		aim.setName(name);
		aim.setDescription(description);
		aim.setControl(control);
		aim.setName(name);
		aim.setDeadline(deadline);

		String query = DBAimQueries.insertAim(aim, deadline_tmp);

		try {
			// execute
			dbm.ExecuteNonQuery(query);
			query = DBAimQueries.getWebAimByAimName(userId, name);

			try {
				ResultSet rs = dbm.ExecuteResultSet(query);
				while (rs.next()) {
					aim.setId(rs.getInt("id"));
				}
				if (aim.getId() == 0) {
					return null;
				}
				return aim;
			} catch (Exception ex) {
				// log it
				return null;
			}
		} catch (Exception ex) {
			// log it
			return null;
		}
	}

	/**
	 * Creates and returns WebAim object only if aim with exact name already
	 * exist for a WebUser
	 * 
	 * @param dbm
	 * @param uid
	 * @param name
	 * @return WebAim or null
	 */
	public WebAim getWebAimByCredentials(DBManager dbm, int userId, String name) {
		if (!checkForConnection(dbm)) {
			return null;
		}

		WebAim wa = new WebAim();
		// see if there is a matching WebAim
		String query = DBAimQueries.getWebAimByAimName(userId, name);
		try {
			ResultSet rs = dbm.ExecuteResultSet(query);

			while (rs.next()) {
				wa.setId(rs.getInt("id"));
				wa.setUserId(rs.getInt("users_id"));
				wa.setName(rs.getString("name"));

			}
			if (wa.getId() == 0) {
				return null;
			}

			return wa;
		} catch (Exception ex) {
			// log it
			return wa;
		}
	}

	/**
	 * Returns List of WebUser's aims by ascending order, excludes archive's
	 * aims from list
	 * 
	 * @param dbm
	 * @param userId
	 * @return ArrayList<WebAim> or null
	 */
	public ArrayList<WebAim> getUserAimsByDeadline(DBManager dbm, int userId) {

		if (!checkForConnection(dbm)) {
			return null;
		}

		// build a list of aims objects using the query
		ArrayList<WebAim> allAims = new ArrayList<>();
		String query = DBAimQueries.getUserAimsByDeadline(userId);

		try {
			ResultSet rs = dbm.ExecuteResultSet(query);
			while (rs.next()) {
				WebAim aim = new WebAim();
				aim.setId(rs.getInt("id"));
				aim.setUserId(rs.getInt("users_id"));
				aim.setName(rs.getString("name"));
				aim.setDescription(rs.getString("description"));
				aim.setControl(rs.getString("control"));
				aim.setResult(rs.getBoolean("result"));
				
				LocalDate deadline = null;

				try {
					deadline = rs.getObject("deadline", LocalDate.class);
				} catch (Exception e) {
					e.getMessage();
				} finally {
					aim.setDeadline(deadline);
					allAims.add(aim);
				}
			}
			if (allAims.isEmpty()) {
				return null;
			}
		} catch (Exception ex) {
			// log it
			return null;
		}
		return allAims;
	}

	/**
	 * Delete WebUser's aim by aim's name and description, returns <b>true</b>
	 * if aim deleted and vice versa
	 * 
	 * @param dbm
	 * @param userId
	 * @param name
	 * @param description
	 * @return boolean
	 */
	public boolean deleteAim(DBManager dbm, int userId, String nameOfAim, String description) {

		if (!checkForConnection(dbm)) {
			return false;
		}

		String query = DBAimQueries.deleteAim(userId, nameOfAim, description);
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
	 * Marks a WebAim as done, i.e. WebAim attribute "archive" is set to
	 * <b>true</b>, WebUser attribute "solved" is incremented by 1
	 * 
	 * @param dbm
	 * @param userId
	 * @param name
	 * @param description
	 * @return boolean
	 */
	public boolean markAsAchieved(DBManager dbm, int userId, String nameOfAim, String description) {

		if (!checkForConnection(dbm)) {
			return false;
		}

		String query = DBAimQueries.markAsArchived(userId, nameOfAim, description);
		String query_two = DBAimQueries.markAsSolved(userId);
		String query_three = DBAimQueries.markAsComplited(userId, nameOfAim, description);

		try {
			dbm.ExecuteNonQuery(query);
			dbm.ExecuteNonQuery(query_two);
			dbm.ExecuteNonQuery(query_three);
		}

		catch (Exception ex) {
			// log it
			return false;
		}

		return true;
	}

	/**
	 * Marks a WebAim as done, i.e. WebAim attribute "archive" is set to
	 * <b>true</b>, WebUser attribute "failed" is incremented by 1
	 * 
	 * @param dbm
	 * @param userId
	 * @param name
	 * @param description
	 * @return boolean
	 */
	public boolean markAsFailed(DBManager dbm, int userId, String nameOfAim, String description) {

		if (!checkForConnection(dbm)) {
			return false;
		}

		String query = DBAimQueries.markAsArchived(userId, nameOfAim, description);
		String query_two = DBAimQueries.markAsFailed(userId);

		try {
			dbm.ExecuteNonQuery(query);
			dbm.ExecuteNonQuery(query_two);
		}

		catch (Exception ex) {
			// log it
			return false;
		}

		return true;
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
	 * Returns List of WebUser's aims by ascending order, excludes actual aims
	 * from list
	 * 
	 * @param dbm
	 * @param userId
	 * @return ArrayList<WebAim> or null
	 */
	public ArrayList<WebAim> getArchivesUserAims(DBManager dbm, int userId) {

		if (!checkForConnection(dbm)) {
			return null;
		}

		// build a list of aims objects using the query
		ArrayList<WebAim> archiveAims = new ArrayList<>();
		String query = DBAimQueries.getArchiveUserAimsByDeadline(userId);

		try {
			ResultSet rs = dbm.ExecuteResultSet(query);
			while (rs.next()) {
				WebAim aim = new WebAim();
				aim.setId(rs.getInt("id"));
				aim.setUserId(rs.getInt("users_id"));
				aim.setName(rs.getString("name"));
				aim.setDescription(rs.getString("description"));
				aim.setControl(rs.getString("control"));
				aim.setArchive(rs.getBoolean("archive"));
				aim.setResult(rs.getBoolean("result"));

				LocalDate deadline = null;
				try {
					deadline = rs.getObject("deadline", LocalDate.class);
				} catch (Exception e) {
					e.getMessage();
				} finally {
					aim.setDeadline(deadline);
					archiveAims.add(aim);
				}
			}

			if (archiveAims.isEmpty()) {
				return null;
			}
		}

		catch (Exception ex) {
			// log it
			return null;
		}
		return archiveAims;
	}

	/**
	 * Insert WebAim object into DB
	 * 
	 * @param dbm
	 * @param userId
	 * @param name
	 * @param description
	 * @param control
	 * @param deadline
	 * @param deadline_tmp
	 * @param groups
	 * @return WebAim or null
	 */
	public WebAim insertAim(DBManager dbm, int userId, String name, String description, String control,
			LocalDate deadline, String deadline_tmp, String[] groups) {

		if (!checkForConnection(dbm)) {
			return null;
		}

		if (AimManagerUtils.stringNullOrEmpty(name) || AimManagerUtils.stringNullOrEmpty(description)) {
			return null;
		}

		// make the aim to be added
		WebAim aim = new WebAim();
		aim.setUserId(userId);
		aim.setName(name);
		aim.setDescription(description);
		aim.setControl(control);
		aim.setName(name);
		aim.setDeadline(deadline);

		String query = DBAimQueries.insertAim(aim, deadline_tmp);

		try {
			// execute
			dbm.ExecuteNonQuery(query);

		} catch (Exception ex) {
			// log it
			return null;
		}
		return aim;
	}

}
