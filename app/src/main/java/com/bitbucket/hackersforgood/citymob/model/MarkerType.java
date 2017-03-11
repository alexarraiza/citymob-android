package com.bitbucket.hackersforgood.citymob.model;

import com.bitbucket.hackersforgood.citymob.database.CitymobMainDatabase;
import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;

/**
 * Created by alexarraiza on 11/03/17.
 */
@Table(database = CitymobMainDatabase.class, name = "MARKER_TYPE")
public class MarkerType {

	@SerializedName("ID_AUTO") @PrimaryKey Integer idAuto;
	@SerializedName("DESCRIPTION") @Column String description;
	@SerializedName("ACTIVE") @Column boolean active;

	public MarkerType() {
	}

	public Integer getIdAuto() {
		return idAuto;
	}

	public void setIdAuto(Integer idAuto) {
		this.idAuto = idAuto;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return description;
	}
}
