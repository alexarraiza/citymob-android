package com.bitbucket.hackersforgood.citymob.presenters.impl;

import com.bitbucket.hackersforgood.citymob.executor.Executor;
import com.bitbucket.hackersforgood.citymob.executor.MainThread;
import com.bitbucket.hackersforgood.citymob.interactors.GetAllMarkersInteractor;
import com.bitbucket.hackersforgood.citymob.interactors.GetDirectionsInteractor;
import com.bitbucket.hackersforgood.citymob.interactors.impl.GetAllMarkersInteractorImpl;
import com.bitbucket.hackersforgood.citymob.interactors.impl.GetDirectionsInteractorImpl;
import com.bitbucket.hackersforgood.citymob.model.Marker;
import com.bitbucket.hackersforgood.citymob.presenters.base.AbstractPresenter;
import com.bitbucket.hackersforgood.citymob.presenters.RouteActivityPresenter;
import com.bitbucket.hackersforgood.citymob.repository.impl.MarkerRepositoryImpl;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * Created by alexarraiza on 11/03/17.
 */

public class RouteActivityPresenterImpl extends AbstractPresenter implements RouteActivityPresenter,
		GetDirectionsInteractor.Callback,
		GetAllMarkersInteractor.Callback {

	RouteActivityPresenter.View mView;

	public RouteActivityPresenterImpl(Executor executor, MainThread mainThread, View mView) {
		super(executor, mainThread);
		this.mView = mView;
	}

	@Override
	public void resume() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void stop() {

	}

	@Override
	public void destroy() {

	}

	@Override
	public void onError(String message) {

	}

	@Override
	public void getRouteFromStartToFinish(LatLng start, LatLng end) {
		GetDirectionsInteractor getDirectionsInteractor = new GetDirectionsInteractorImpl(
				mExecutor,
				mMainThread,
				start,
				end,
				this
		);

		getDirectionsInteractor.execute();
	}

	@Override
	public void getMarkers() {
		GetAllMarkersInteractor getAllMarkersInteractor = new GetAllMarkersInteractorImpl(
				mExecutor,
				mMainThread,
				new MarkerRepositoryImpl(),
				this
		);

		getAllMarkersInteractor.execute();
	}

	@Override
	public void onDirectrionsRetrieved(List<LatLng> latLngList) {
		mView.showRoute(latLngList);
	}

	@Override
	public void onDirectionsError() {

	}

	@Override
	public void onMarkersRetrieved(List<Marker> markerList) {
		mView.showMarkersOnMap(markerList);
	}

	@Override
	public void onMarkersNotFound() {

	}
}
