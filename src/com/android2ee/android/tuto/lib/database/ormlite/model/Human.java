/**<ul>
 * <li>DataBaseSugarOrmTuto</li>
 * <li>com.android2ee.android.tuto.lib.databse.sugarorm.model</li>
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
package com.android2ee.android.tuto.lib.database.ormlite.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author Mathias Seguy (Android2EE)
 * @goals
 *        This class aims to:
 *        <ul>
 *        <li></li>
 *        </ul>
 */
@DatabaseTable(tableName = "humans")
public class Human {
	@DatabaseField(generatedId = true)
	public int id;
	/**
	 * The name
	 */
	@DatabaseField(canBeNull = false)
	String name;
	/**
	 * The firstName
	 */
	@DatabaseField(defaultValue = "John")
	String firstName;
	/**
	 * The hair color
	 */
	@DatabaseField
	String hair_color;
	/**
	 * The eyes color
	 */
	@DatabaseField
	String eyes_color;
	/**
	 * The age
	 */
	@DatabaseField
	int age;
	/**
	 * Ignored field in database
	 */
	@DatabaseField(persisted = false)
	String theIgnoredField;
	/**
	 * A relationship between table to make 1-n or 1-1 relationship
	 */
	@DatabaseField(foreign = true)
	Human relationShip;

	/**
	 * Please always retain the default constructor
	 */
	public Human() {
		super();
	}

	/**
	 * @param name
	 * @param firstName
	 * @param hair_color
	 * @param eyes_color
	 * @param age
	 * @param theIgnoredField
	 * @param relationShip
	 */
	public Human(String name, String firstName, String hair_color, String eyes_color, int age, String theIgnoredField,
			Human relationShip) {
		super();
		this.name = name;
		this.firstName = firstName;
		this.hair_color = hair_color;
		this.eyes_color = eyes_color;
		this.age = age;
		this.theIgnoredField = theIgnoredField;
		this.relationShip = relationShip;
	}

	/**
	 * @return the name
	 */
	public final String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public final void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the firstName
	 */
	public final String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public final void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the hair_color
	 */
	public final String getHair_color() {
		return hair_color;
	}

	/**
	 * @param hair_color
	 *            the hair_color to set
	 */
	public final void setHair_color(String hair_color) {
		this.hair_color = hair_color;
	}

	/**
	 * @return the eyes_color
	 */
	public final String getEyes_color() {
		return eyes_color;
	}

	/**
	 * @param eyes_color
	 *            the eyes_color to set
	 */
	public final void setEyes_color(String eyes_color) {
		this.eyes_color = eyes_color;
	}

	/**
	 * @return the age
	 */
	public final int getAge() {
		return age;
	}

	/**
	 * @param age
	 *            the age to set
	 */
	public final void setAge(int age) {
		this.age = age;
	}

	/**
	 * @return the theIgnoredField
	 */
	public final String getTheIgnoredField() {
		return theIgnoredField;
	}

	/**
	 * @param theIgnoredField
	 *            the theIgnoredField to set
	 */
	public final void setTheIgnoredField(String theIgnoredField) {
		this.theIgnoredField = theIgnoredField;
	}

	/**
	 * @return the relationShip
	 */
	public final Human getRelationShip() {
		return relationShip;
	}

	/**
	 * @param relationShip
	 *            the relationShip to set
	 */
	public final void setRelationShip(Human relationShip) {
		this.relationShip = relationShip;
	}

	/**
	 * @return the id
	 */
	public final int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public final void setId(int id) {
		this.id = id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder strB = new StringBuilder(name);
		strB.append("'");
		strB.append(firstName);
		strB.append("'");
		strB.append(hair_color);
		strB.append("'");
		strB.append(eyes_color);
		strB.append("'");
		strB.append(age);
		strB.append("'");
		strB.append(theIgnoredField);
		if (relationShip != null) {
			strB.append("'");
			strB.append(relationShip.toString());
		}
		strB.append("'");
		strB.append(id);
		return strB.toString();
	}

}
