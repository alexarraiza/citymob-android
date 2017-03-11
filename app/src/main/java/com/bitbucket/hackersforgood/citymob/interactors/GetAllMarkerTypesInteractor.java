package com.bitbucket.hackersforgood.citymob.interactors;

import com.bitbucket.hackersforgood.citymob.interactors.base.Interactor;
import com.bitbucket.hackersforgood.citymob.model.MarkerType;

import java.util.List;

/**
 * Created by alexarraiza on 11/03/17.
 */

public interface GetAllMarkerTypesInteractor extends Interactor {

	interface Callback {

		void onMarkerTypesRetrieved(List<MarkerType> markerTypeList);

		void onMarkerTypeNotFound();

	}

}
