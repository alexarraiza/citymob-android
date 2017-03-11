package com.bitbucket.hackersforgood.citymob.interactors;

import com.bitbucket.hackersforgood.citymob.interactors.base.Interactor;
import com.bitbucket.hackersforgood.citymob.model.MarkerType;

import java.util.List;

/**
 * Created by alexarraiza on 11/03/17.
 */

public interface DownloadMarkerTypeInteractor extends Interactor {

	interface Callback {

		void onMarkerTypeDownloaded(List<MarkerType> markerTypeList);

		void onMarkerTypeDownloadFailed();

	}

}
