package com.android2ee.android.tuto.lib.database.ormlite;

import java.sql.SQLException;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android2ee.android.tuto.lib.database.ormlite.dao.HumanProvider;
import com.android2ee.android.tuto.lib.database.ormlite.model.Human;

public class MainActivity extends Activity {
	/**
	 * The textview that displays the whathappens
	 */
	private TextView txvWhatHappens = null;
	/**
	 * The WhatHappens messages
	 */
	private StringBuilder strWhatHappens;
	/**
	 * Carriage return
	 */
	private final String carriageReturn = "\r\n\r\n";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setContentView(R.layout.activity_main);
		txvWhatHappens = (TextView) findViewById(R.id.txvWhatHappens);
		strWhatHappens = new StringBuilder(getString(R.string.whatHappens_title));
		try {
			// First Find your provider
			// ------------------------
			HumanProvider humanProvider;
			humanProvider = new HumanProvider();

			// Insert a new record
			// -------------------
			Human george = new Human("Bush", "Georges", "blond", "green", 76, "WhoCareOfThatField", null);
			humanProvider.save(george);
			int idGeorge = george.getId();
			updateTxvWhatHappens(getString(R.string.success_creating_human, george, idGeorge));
			Human sadam = new Human("Sadam", "Hussein", "black", "black", 52, "WhoCareOfThatField", george);
			humanProvider.save(sadam);
			int idSadam = sadam.getId();
			updateTxvWhatHappens(getString(R.string.success_creating_human, sadam, idSadam));
			Log.e("MainActivity", "Human created");
			// update that line
			// ----------------
			george.setAge(56);
			humanProvider.update(george);
			updateTxvWhatHappens(getString(R.string.success_updating_human, george, idGeorge));
			Log.e("MainActivity", "Human updated");
			// Query that line
			// ---------------
			Human sameSadam = humanProvider.findById(idSadam);
			int idSameSadam = sameSadam.getId();
			Log.e("MainActivity", "Human loaded");
			// Load the relationShip else every attribute will be null except id
			humanProvider.refreshRelationShip(sameSadam);

			Log.e("MainActivity", "Human getRelationShip :" + sameSadam.getRelationShip());
			updateTxvWhatHappens(getString(R.string.retieve_element, sameSadam, idSameSadam));
			Log.e("MainActivity", "Human getRelationShip write");
			// Query all lines:
			// ----------------
			List<Human> humans = humanProvider.findAll();
			Log.e("MainActivity", "Human findAll");
			for (Human human : humans) {
				Log.e("MainActivity", "Human  write one");
				humanProvider.refreshRelationShip(human);
				updateTxvWhatHappens(getString(R.string.retieve_element, human, human.getId()));
				Log.e("MainActivity", "Human  one wrote");
			}
			// And then delete it:
			// -------------------
			humanProvider.delete(sadam);
			Log.e("MainActivity", "Human sadam deleted");
			updateTxvWhatHappens(getString(R.string.success_deleting_human, sadam));
			// Query all lines (to see if george has been deleted)
			// ----------------
			humans = humanProvider.findAll();
			Log.e("MainActivity", "Human findAll");
			for (Human human : humans) {
				Log.e("MainActivity", "Human  write one");
				humanProvider.refreshRelationShip(human);
				updateTxvWhatHappens(getString(R.string.retieve_element, human, human.getId()));
				Log.e("MainActivity", "Human  one wrote");
			}
			// Or delete All
			// -------------
			humanProvider.deleteAll();
			Log.e("MainActivity", "Human deleting all");
			updateTxvWhatHappens(getString(R.string.delete_all));
			// Query that line
			// ---------------
			humans = humanProvider.findAll();
			if (humans != null && humans.size() == 0) {
				updateTxvWhatHappens(getString(R.string.retrieve_no_element));
			}

			// And more important than every think the close:
			humanProvider.close();

		} catch (SQLException e) {
			// You should manage your exception in an other way, this one is bad
			Log.d("HumanProvider", "Unable to create datbases", e);
		}
	}

	/******************************************************************************************/
	/** Displaying what happens **************************************************************************/
	/******************************************************************************************/
	/**
	 * Displays the information to the user of what's happening
	 * 
	 * @param message
	 */
	private void updateTxvWhatHappens(String message) {
		strWhatHappens.append(carriageReturn);
		strWhatHappens.append(message);
		txvWhatHappens.setText(strWhatHappens);
		Log.e("MainActivity SugarORM", message);
		// Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

	}
}
