package com.bitbucket.hackersforgood.citymob.presenters;

import com.bitbucket.hackersforgood.citymob.model.Marker;
import com.bitbucket.hackersforgood.citymob.presenters.base.BasePresenter;
import com.bitbucket.hackersforgood.citymob.ui.BaseView;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * Created by alexarraiza on 11/03/17.
 */

public interface RouteActivityPresenter extends BasePresenter {

	void getRouteFromStartToFinish(LatLng start, LatLng end);

	void getMarkers();

	interface View extends BaseView {

		void showMap();

		void showRoute(List<LatLng> latLngList);

		void showMarkersOnMap(List<Marker> markerList);

	}

}
