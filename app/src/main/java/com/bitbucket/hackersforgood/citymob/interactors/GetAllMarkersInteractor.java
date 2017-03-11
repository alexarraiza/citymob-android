package com.bitbucket.hackersforgood.citymob.interactors;

import com.bitbucket.hackersforgood.citymob.interactors.base.Interactor;
import com.bitbucket.hackersforgood.citymob.model.Marker;

import java.util.List;

/**
 * Created by alexarraiza on 10/03/17.
 */

public interface GetAllMarkersInteractor extends Interactor {

	interface Callback {

		void onMarkersRetrieved(List<Marker> markerList);

		void onMarkersNotFound();

	}

}
