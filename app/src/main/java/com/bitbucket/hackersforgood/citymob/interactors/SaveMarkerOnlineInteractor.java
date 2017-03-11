package com.bitbucket.hackersforgood.citymob.interactors;

import com.bitbucket.hackersforgood.citymob.interactors.base.Interactor;
import com.bitbucket.hackersforgood.citymob.model.Marker;

/**
 * Created by alexarraiza on 10/03/17.
 */

public interface SaveMarkerOnlineInteractor extends Interactor {

	interface Callback {

		void onMarkerSaved(Marker marker);

		void onMarkerSaveError();

	}

}
