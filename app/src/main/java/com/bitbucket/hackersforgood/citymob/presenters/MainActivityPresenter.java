package com.bitbucket.hackersforgood.citymob.presenters;

import com.bitbucket.hackersforgood.citymob.model.Marker;
import com.bitbucket.hackersforgood.citymob.model.MarkerType;
import com.bitbucket.hackersforgood.citymob.presenters.base.BasePresenter;
import com.bitbucket.hackersforgood.citymob.ui.BaseView;

import java.util.List;

/**
 * Created by alexarraiza on 10/03/17.
 */

public interface MainActivityPresenter extends BasePresenter {

	void getMarkersOnline();

	void getMarkerTypeOnline();

	interface View extends BaseView {

		void checkLocationPermission();

		void onMarkersDownloaded(List<Marker> markerList);

		void onMarkerTypeDownloaded(List<MarkerType> markerTypeList);

		void showMainMapFragment();

	}

}
