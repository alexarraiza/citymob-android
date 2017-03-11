package com.bitbucket.hackersforgood.citymob.repository;

import com.bitbucket.hackersforgood.citymob.model.Marker;

import java.util.List;

/**
 * Created by alexarraiza on 10/03/17.
 */

public interface MarkerRepository {

	List<Marker> getMarkers();

	List<Marker> getMarkersOnline();

	void deleteAllMarkers();

	Marker saveMarker(Marker marker);

	List<Marker> saveMarkerList(List<Marker> markerList);

	Marker saveMarkerOnline(Marker marker);

}
