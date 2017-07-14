package ru.aim.modelmanagers;

import java.io.IOException;
import java.io.Serializable;
import java.sql.ResultSet;

import java.time.LocalDate;
import java.util.ArrayList;

import ru.aim.dbhelpers.DBAimQueries;
import ru.aim.dbmodels.DBManager;

import ru.aim.models.WebAimGroup;
import ru.aim.models.WebCollector;

/**
 * 
 * Manages a WebAimGroup object creation cycle
 * (check-creation-validation-delete) by its methods
 * 
 */
public class WebAimGroupManager implements Serializable {
	private static final long serialVersionUID = 1L;

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
	 * Insert WebAimGroup object into DB
	 * 
	 * @param dbm
	 * @param aimId
	 * @param groupName
	 * @return WebAimGroup
	 */
	public WebAimGroup insertAimGroup(DBManager dbm, int aimId, String groupId) {

		if (!checkForConnection(dbm)) {
			return null;
		}
		// make the aim to be added
		WebAimGroup aimGroup = new WebAimGroup();
		aimGroup.setAimId(aimId);
		if (!(groupId == null)) {
			aimGroup.setGroupId(Integer.parseInt(groupId));
		}
		String query = DBAimQueries.insertAimGroup(aimGroup);
		try {
			// execute
			dbm.ExecuteNonQuery(query);
			return aimGroup;
		} catch (Exception ex) {
			// log it
			return null;
		}
	}

	/**
	 * Returns ArrayList with WebCollectors by a WebGroup's name where WebUser
	 * in by "byDeadline" or "byRate"
	 * 
	 * @param dbm
	 * @param group
	 * @return ArrayList with type WebCollector or null
	 */
	public ArrayList<WebCollector> getWebCollectors(DBManager dbm, String groupName) {

		if (!checkForConnection(dbm)) {
			return null;
		}
		String query = DBAimQueries.selectCollectorForGroup(groupName);
		;

		ArrayList<WebCollector> collectors = new ArrayList<>();
		LocalDate deadline = null;

		try {
			ResultSet rs = dbm.ExecuteResultSet(query);

			while (rs.next()) {
				WebCollector collector = new WebCollector();

				collector.getWebUser().setUserId(rs.getString("login"));
				collector.getWebUser().setSolved(rs.getInt("solved"));
				collector.getWebUser().setFailed(rs.getInt("failed"));
				collector.getWebAim().setName(rs.getString("name"));
				collector.getWebAim().setDescription(rs.getString("description"));
				collector.getWebAim().setControl(rs.getString("control"));
				collector.getWebAim().setArchive(rs.getBoolean("archive"));
				collector.getWebAim().setResult(rs.getBoolean("result"));
				collector.getWebGroup().setName(rs.getString("name_group"));
				
				try {
					deadline = rs.getObject("deadline", LocalDate.class);
				} catch (Exception e) {
					e.getMessage();
				} finally {
					collector.getWebAim().setDeadline(deadline);
				}

				collectors.add(collector);
			}
			if (collectors.isEmpty()) {
				return null;
			}
			return collectors;
		} catch (Exception ex) {
			return collectors;
		}
	}

	/**
	 * Returns ArrayList with WebCollectors by a WebGroup's name where WebUser
	 * in by rate=(solved-failed)
	 * 
	 * @param dbm
	 * @param groupName
	 * @return ArrayList with type WebCollector or null
	 */
	public ArrayList<WebCollector> getWebCollectorsForRate(DBManager dbm, String groupName) {
		if (!checkForConnection(dbm)) {
			return null;
		}
		String query = DBAimQueries.selectCollectorForGroupByRate(groupName);
		;

		ArrayList<WebCollector> collectors = new ArrayList<>();
		LocalDate deadline = null;

		try {
			ResultSet rs = dbm.ExecuteResultSet(query);

			while (rs.next()) {
				WebCollector collector = new WebCollector();

				collector.getWebUser().setUserId(rs.getString("login"));
				collector.getWebUser().setSolved(rs.getInt("solved"));
				collector.getWebUser().setFailed(rs.getInt("failed"));
				collector.getWebGroup().setName(rs.getString("name_group"));

				collectors.add(collector);
			}

			if (collectors.isEmpty()) {
				return null;
			}

			return collectors;
		} catch (Exception ex) {
			return collectors;
		}
	}

}
