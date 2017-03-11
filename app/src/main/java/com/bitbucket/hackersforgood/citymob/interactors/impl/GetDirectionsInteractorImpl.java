package com.bitbucket.hackersforgood.citymob.interactors.impl;

import com.bitbucket.hackersforgood.citymob.executor.Executor;
import com.bitbucket.hackersforgood.citymob.executor.MainThread;
import com.bitbucket.hackersforgood.citymob.interactors.base.AbstractInteractor;
import com.bitbucket.hackersforgood.citymob.interactors.GetDirectionsInteractor;
import com.directions.route.AbstractRouting;
import com.directions.route.Route;
import com.directions.route.RouteException;
import com.directions.route.Routing;
import com.directions.route.RoutingListener;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * Created by alexarraiza on 11/03/17.
 */

public class GetDirectionsInteractorImpl extends AbstractInteractor implements GetDirectionsInteractor, RoutingListener {

	LatLng start;
	LatLng end;
	GetDirectionsInteractor.Callback mCallback;

	public GetDirectionsInteractorImpl(Executor threadExecutor, MainThread mainThread, LatLng start, LatLng end, Callback mCallback) {
		super(threadExecutor, mainThread);
		this.start = start;
		this.end = end;
		this.mCallback = mCallback;
	}

	@Override
	public void run() {

		Routing routing = new Routing.Builder()
				.travelMode(Routing.TravelMode.WALKING)
				.withListener(this)
				.waypoints(start, end)
				.build();
		routing.execute();

	}

	@Override
	public void onRoutingFailure(RouteException e) {
		mMainThread.post(new Runnable() {
			@Override
			public void run() {
				mCallback.onDirectionsError();
			}
		});
	}

	@Override
	public void onRoutingStart() {

	}

	@Override
	public void onRoutingSuccess(final ArrayList<Route> arrayList, int i) {

		mMainThread.post(new Runnable() {
			@Override
			public void run() {
				mCallback.onDirectrionsRetrieved(arrayList.get(0).getPoints());
			}
		});

	}

	@Override
	public void onRoutingCancelled() {

	}
}
