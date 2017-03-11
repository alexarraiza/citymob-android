package com.bitbucket.hackersforgood.citymob.repository;

import com.bitbucket.hackersforgood.citymob.model.MarkerType;

import java.util.List;

/**
 * Created by alexarraiza on 11/03/17.
 */

public interface MarkerTypeRepository {

	List<MarkerType> getAllMarkerTypes();

	List<MarkerType> getMarkerTypesOnline();

	MarkerType saveMarkerType(MarkerType marker);

	void deleteAllMarkerTypes();

	List<MarkerType> saveMarkerTypeList(List<MarkerType> markerList);
}
