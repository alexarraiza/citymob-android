package com.bitbucket.hackersforgood.citymob.interactors.impl;

import com.bitbucket.hackersforgood.citymob.executor.Executor;
import com.bitbucket.hackersforgood.citymob.executor.MainThread;
import com.bitbucket.hackersforgood.citymob.interactors.SaveMarkerOnlineInteractor;
import com.bitbucket.hackersforgood.citymob.interactors.base.AbstractInteractor;
import com.bitbucket.hackersforgood.citymob.model.Marker;
import com.bitbucket.hackersforgood.citymob.repository.MarkerRepository;

/**
 * Created by alexarraiza on 10/03/17.
 */

public class SaveMarkerOnlineInteractorImpl extends AbstractInteractor implements SaveMarkerOnlineInteractor {

	Marker marker;
	MarkerRepository markerRepository;
	SaveMarkerOnlineInteractor.Callback mCallback;

	public SaveMarkerOnlineInteractorImpl(Executor threadExecutor, MainThread mainThread, Marker marker, MarkerRepository markerRepository, Callback mCallback) {
		super(threadExecutor, mainThread);
		this.marker = marker;
		this.markerRepository = markerRepository;
		this.mCallback = mCallback;
	}

	@Override
	public void run() {

		Marker markerSaved = markerRepository.saveMarkerOnline(marker);

		if (markerSaved != null) {

			markerSaved = markerRepository.saveMarker(markerSaved);

			final Marker finalMarkerSaved = markerSaved;
			mMainThread.post(new Runnable() {
				@Override
				public void run() {
					mCallback.onMarkerSaved(finalMarkerSaved);
				}
			});

		} else {
			mMainThread.post(new Runnable() {
				@Override
				public void run() {
					mCallback.onMarkerSaveError();
				}
			});
		}

	}
}
