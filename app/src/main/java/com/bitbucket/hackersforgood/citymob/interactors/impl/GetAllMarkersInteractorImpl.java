package com.bitbucket.hackersforgood.citymob.interactors.impl;

import com.bitbucket.hackersforgood.citymob.executor.Executor;
import com.bitbucket.hackersforgood.citymob.executor.MainThread;
import com.bitbucket.hackersforgood.citymob.interactors.base.AbstractInteractor;
import com.bitbucket.hackersforgood.citymob.interactors.GetAllMarkersInteractor;
import com.bitbucket.hackersforgood.citymob.model.Marker;
import com.bitbucket.hackersforgood.citymob.repository.MarkerRepository;

import java.util.List;

/**
 * Created by alexarraiza on 10/03/17.
 */

public class GetAllMarkersInteractorImpl extends AbstractInteractor implements GetAllMarkersInteractor {

	MarkerRepository mMarkerRepository;
	GetAllMarkersInteractor.Callback mCallback;

	public GetAllMarkersInteractorImpl(Executor threadExecutor, MainThread mainThread, MarkerRepository mMarkerRepository, Callback mCallback) {
		super(threadExecutor, mainThread);
		this.mMarkerRepository = mMarkerRepository;
		this.mCallback = mCallback;
	}

	@Override
	public void run() {

		final List<Marker> markerList = mMarkerRepository.getMarkers();

		if (markerList.size() > 0){

			mMainThread.post(new Runnable() {
				@Override
				public void run() {
					mCallback.onMarkersRetrieved(markerList);
				}
			});

		}else{

			mMainThread.post(new Runnable() {
				@Override
				public void run() {
					mCallback.onMarkersNotFound();
				}
			});

		}

	}
}
