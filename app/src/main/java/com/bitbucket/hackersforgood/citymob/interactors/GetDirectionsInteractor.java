package com.bitbucket.hackersforgood.citymob.interactors;

import com.bitbucket.hackersforgood.citymob.interactors.base.Interactor;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * Created by alexarraiza on 11/03/17.
 */

public interface GetDirectionsInteractor extends Interactor {

	interface Callback {

		void onDirectrionsRetrieved(List<LatLng> latLngList);

		void onDirectionsError();

	}

}
