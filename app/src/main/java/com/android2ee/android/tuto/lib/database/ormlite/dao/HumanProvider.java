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
import java.util.List;

import android.content.Context;
import android.util.Log;

import com.android2ee.android.tuto.lib.database.ormlite.MyApplication;
import com.android2ee.android.tuto.lib.database.ormlite.model.Human;
import com.j256.ormlite.dao.Dao;

/**
 * @author Mathias Seguy (Android2EE)
 * @goals
 *        This class aims to:
 *        <ul>
 *        <li></li>
 *        </ul>
 */
public class HumanProvider {

	/******************************************************************************************/
	/** Attribute **************************************************************************/
	/******************************************************************************************/

	/**
	 * The context
	 */
	private Context mContext;
	/**
	 * your database helper
	 */
	private DatabaseHelper mDbHelper;
	/**
	 * your Human Dao to access to the humans table
	 */
	private Dao<Human, Integer> mHumanDao = null;

	/******************************************************************************************/
	/** Constructors and destructor**************************************************************************/
	/******************************************************************************************/

	/**
	 * Constructor of the provider
	 * 
	 * @throws SQLException
	 */
	public HumanProvider() throws SQLException {
		mContext = MyApplication.instance.getApplicationContext();
		mDbHelper = new DatabaseHelper(mContext);
		mHumanDao = mDbHelper.getHumanDao();
	}
	/**
	 * You have to call this method when you have finished to use the dao
	 * In a way in the onStop of your activity
	 */
	public void close() {
		mHumanDao = null;
		mDbHelper.close();
	}
	/******************************************************************************************/
	/** DAO methods **************************************************************************/
	/******************************************************************************************/



	/**
	 * Find an human according to its id
	 * 
	 * @param humanId
	 * @return
	 * @throws SQLException
	 */
	public Human findById(int humanId) throws SQLException {
		return getHumanDao().queryForId(humanId);
	}
	
	/**
	 * Save an Human in DataBase
	 * @param human The human to create
	 * @return The number of element created
	 * @throws SQLException
	 */
	public int save(Human human)throws SQLException{
		return getHumanDao().create(human);
	}
	
	/**
	 * Update an Human in DataBase
	 * @param human The human to update
	 * @return The number of element updated
	 * @throws SQLException
	 */
	public int update(Human human)throws SQLException{
		return getHumanDao().update(human);
	}
	
	/**
	 * When loading an Human with a relationship
	 * If you want to access this relationsship you have to refresh it (else each of its fields except id are null)
	 * @param human
	 * @throws SQLException
	 */
	public void refreshRelationShip(Human human) throws SQLException{
		getHumanDao().refresh(human.getRelationShip());
	}
	
	/**
	 * Delete an Human in DataBase
	 * @param human The human to delete
	 * @return The number of element deleted
	 * @throws SQLException
	 */
	public int delete(Human human)throws SQLException{
		return getHumanDao().delete(human);
	}

	/**
	 * Delete all the humans in base and add those given as parameter
	 * 
	 * @param humans
	 * @throws SQLException
	 */
	public int  deleteAll() throws SQLException {
		// To avoid bad cache of data removeAll and createAll is needed
		// Remove data first:
		return getHumanDao().delete(findAll());
	}
	/**
	 * Find all the humans
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Human> findAll() throws SQLException {
		return getHumanDao().queryForAll();
	}

	/**
	 * Delete all the humans in base and add those given as parameter
	 * 
	 * @param humans
	 * @throws SQLException
	 */
	public void deleteAllAndsaveAll(List<Human> humans) throws SQLException {
		// To avoid bad cache of data removeAll and createAll is needed
		// Remove data first:
		getHumanDao().delete(findAll());
		// then create them again
		saveAll(humans);
	}

	/**
	 * Save All the humans given as parameter
	 * 
	 * @param speakers
	 * @throws SQLException
	 */
	public void saveAll(List<Human> humans) throws SQLException {
		if (null != humans) {
			for (Human human : humans) {
				getHumanDao().createOrUpdate(human);

			}
		}
	}

	/******************************************************************************************/
	/** Getter **************************************************************************/
	/******************************************************************************************/

	/**
	 * The getter for the HumanDao
	 * 
	 * @return
	 */
	private Dao<Human, Integer> getHumanDao() {
		if (mHumanDao == null) {
			try {
				mHumanDao = mDbHelper.getHumanDao();
			} catch (SQLException e) {
				// You should manage your exception in an other way, this one is bad
				Log.d("HumanProvider", "Unable to create datbases", e);
			}
		}
		return mHumanDao;
	}

}
