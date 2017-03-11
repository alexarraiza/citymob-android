package com.bitbucket.hackersforgood.citymob.model;

import com.bitbucket.hackersforgood.citymob.database.CitymobMainDatabase;
import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;

import java.util.Date;

/**
 * Created by alexarraiza on 10/03/17.
 */
@Table(database = CitymobMainDatabase.class, name = "MARKER")
public class Marker {

	@SerializedName("ID_AUTO") @PrimaryKey Integer idAuto;
	@SerializedName("LNG") @Column double lng;
	@SerializedName("LAT") @Column double lat;
	@SerializedName("TITLE") @Column String title;
	@SerializedName("DESCRIPTION") @Column String description;
	@SerializedName("ACTIVE") @Column boolean active;
	@SerializedName("ID_MARKER_TYPE") @Column int idMarkerType;
	@SerializedName("ON_DATE") @Column Date onDate;
	@SerializedName("OFF_DATE") @Column Date offDate;

	public Integer getIdAuto() {
		return idAuto;
	}

	public void setIdAuto(Integer idAuto) {
		this.idAuto = idAuto;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public int getIdMarkerType() {
		return idMarkerType;
	}

	public void setIdMarkerType(int idMarkerType) {
		this.idMarkerType = idMarkerType;
	}

	public Date getOnDate() {
		return onDate;
	}

	public void setOnDate(Date onDate) {
		this.onDate = onDate;
	}

	public Date getOffDate() {
		return offDate;
	}

	public void setOffDate(Date offDate) {
		this.offDate = offDate;
	}
}
