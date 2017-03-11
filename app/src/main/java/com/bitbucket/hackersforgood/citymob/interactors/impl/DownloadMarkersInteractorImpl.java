package com.bitbucket.hackersforgood.citymob.interactors.impl;

import com.bitbucket.hackersforgood.citymob.executor.Executor;
import com.bitbucket.hackersforgood.citymob.executor.MainThread;
import com.bitbucket.hackersforgood.citymob.interactors.DownloadMarkersInteractor;
import com.bitbucket.hackersforgood.citymob.interactors.base.AbstractInteractor;
import com.bitbucket.hackersforgood.citymob.model.Marker;
import com.bitbucket.hackersforgood.citymob.repository.MarkerRepository;

import java.util.List;

/**
 * Created by alexarraiza on 10/03/17.
 */

public class DownloadMarkersInteractorImpl extends AbstractInteractor implements DownloadMarkersInteractor {

	MarkerRepository markerRepository;
	DownloadMarkersInteractor.Callback mCallback;

	public DownloadMarkersInteractorImpl(Executor threadExecutor, MainThread mainThread, MarkerRepository markerRepository, Callback mCallback) {
		super(threadExecutor, mainThread);
		this.markerRepository = markerRepository;
		this.mCallback = mCallback;
	}

	@Override
	public void run() {

		final List<Marker> markerList = markerRepository.getMarkersOnline();

		if (markerList != null && markerList.size() > 0) {

			markerRepository.deleteAllMarkers();

			markerRepository.saveMarkerList(markerList);

			mMainThread.post(new Runnable() {
				@Override
				public void run() {
					mCallback.onMarkersDownloaded(markerList);
				}
			});

		} else {

			mMainThread.post(new Runnable() {
				@Override
				public void run() {
					mCallback.onMarkersNotFound();
				}
			});
		}

	}
}
