package com.bitbucket.hackersforgood.citymob.presenters;

import com.bitbucket.hackersforgood.citymob.model.Marker;
import com.bitbucket.hackersforgood.citymob.presenters.base.BasePresenter;
import com.bitbucket.hackersforgood.citymob.ui.BaseView;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * Created by alexarraiza on 10/03/17.
 */

public interface MainMapPresenter extends BasePresenter {

	interface View extends BaseView {

		void showMap();

		void showAdapter(List<Marker> markerList);

		void showMarkersOnMap(List<Marker> markerList);

		void emptyAdapter();

		void centerMapOnUser(GoogleMap googleMap);

		void onClickMarkerAdapter(Marker marker);

		void onClickMarkerMap();

		void onLongClickMap(LatLng latLng);

		void showSpinnerDiscapacidades();

		void onClickRouteBtn();

	}

	void getMarkers();

}
