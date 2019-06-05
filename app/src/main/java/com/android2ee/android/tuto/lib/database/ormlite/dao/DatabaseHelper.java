/**<ul>
 * <li>DataBaseOrmLite</li>
 * <li>com.android2ee.android.tuto.lib.database.ormlite.dao</li>
 * <li>16 oct. 2014</li>
 * 
 * <li>======================================================</li>
 *
 * <li>Projet : Mathias Seguy Project</li>
 * <li>Produit par MSE.</li>
 *
 /**
 * <ul>
 * Android Tutorial, An <strong>Android2EE</strong>'s project.</br> 
 * Produced by <strong>Dr. Mathias SEGUY</strong>.</br>
 * Delivered by <strong>http://android2ee.com/</strong></br>
 *  Belongs to <strong>Mathias Seguy</strong></br>
 ****************************************************************************************************************</br>
 * This code is free for any usage except training and can't be distribute.</br>
 * The distribution is reserved to the site <strong>http://android2ee.com</strong>.</br>
 * The intelectual property belongs to <strong>Mathias Seguy</strong>.</br>
 * <em>http://mathias-seguy.developpez.com/</em></br> </br>
 * 
 * *****************************************************************************************************************</br>
 *  Ce code est libre de toute utilisation mais n'est pas distribuable.</br>
 *  Sa distribution est reservée au site <strong>http://android2ee.com</strong>.</br> 
 *  Sa propriété intellectuelle appartient à <strong>Mathias Seguy</strong>.</br>
 *  <em>http://mathias-seguy.developpez.com/</em></br> </br>
 * *****************************************************************************************************************</br>
 */
package com.android2ee.android.tuto.lib.database.ormlite.dao;

import java.sql.SQLException;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.android2ee.android.tuto.lib.database.ormlite.model.Human;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

/**
 * @author Mathias Seguy (Android2EE)
 * @goals
 *        This class aims to:
 *        <ul>
 *        <li></li>
 *        </ul>
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
	/**
	 * The database name
	 */
	private static final String DATABASE_NAME = "android2ee_sqllite_humans.db";
	/**
	 * The database version
	 */
	private static final int DATABASE_VERSION = 1;

	/**
	 * The dao to use to access the Human table
	 */
	private Dao<Human, Integer> humanDao;

	/**
	 * @param context
	 * @param databaseName
	 * @param factory
	 * @param databaseVersion
	 */
	public DatabaseHelper(Context context) {
		// Context context, String databaseName, CursorFactory factory, int databaseVersion
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper#onCreate(android.database.sqlite
	 * .SQLiteDatabase, com.j256.ormlite.support.ConnectionSource)
	 */
	@Override
	public void onCreate(SQLiteDatabase sqliteDatabase, ConnectionSource connectionSource) {
		Log.i("DatabaseHelper", "Trying to create table humans... ");
		try {
			if (!isTableExists(sqliteDatabase, "humans")) {
				TableUtils.createTable(connectionSource, Human.class);
				Log.i(DatabaseHelper.class.getName(), "Creating table humans");
			}

		} catch (SQLException e) {
			// You should manage your exception in an other way, this one is bad
			Log.d("DatabaseHelper", "Unable to create datbases", e);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper#onUpgrade(android.database.sqlite
	 * .SQLiteDatabase, com.j256.ormlite.support.ConnectionSource, int, int)
	 */
	@Override
	public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
		// Migrate your database here
		try {
			TableUtils.dropTable(connectionSource, Human.class, true);
			onCreate(database, connectionSource);
		} catch (SQLException e) {
			Log.d("DatabaseHelper", "Unable to upgrade database from version " + oldVersion + " to new "
					+ newVersion, e);
		}
	}

	/******************************************************************************************/
	/** DAO **************************************************************************/
	/******************************************************************************************/
	/**
	 * Retrieve the DAO for table humans
	 * 
	 * @return the humans DAO
	 * @throws SQLException
	 */
	public Dao<Human, Integer> getHumanDao() throws SQLException {
		if (humanDao == null) {
			humanDao = getDao(Human.class);
		}
		return humanDao;
	}

	/******************************************************************************************/
	/** Usefull methods **************************************************************************/
	/******************************************************************************************/

	/**
	 * Return true if the table exists.
	 * 
	 * @param db
	 *            the DB name
	 * @param tableName
	 *            the table's name
	 * @return true if the table exists
	 */
	private boolean isTableExists(SQLiteDatabase db, String tableName) {
		if (tableName == null || db == null || !db.isOpen()) {
			return false;
		}
		Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM sqlite_master WHERE type = ? AND name = ?", new String[] {
				"table", tableName });
		if (!cursor.moveToFirst()) {
			return false;
		}
		int count = cursor.getInt(0);
		cursor.close();
		return count > 0;
	}
}
