package com.bitbucket.hackersforgood.citymob.interactors.impl;

import com.bitbucket.hackersforgood.citymob.executor.Executor;
import com.bitbucket.hackersforgood.citymob.executor.MainThread;
import com.bitbucket.hackersforgood.citymob.interactors.base.AbstractInteractor;
import com.bitbucket.hackersforgood.citymob.interactors.DownloadMarkerTypeInteractor;
import com.bitbucket.hackersforgood.citymob.model.MarkerType;
import com.bitbucket.hackersforgood.citymob.repository.MarkerTypeRepository;

import java.util.List;

/**
 * Created by alexarraiza on 11/03/17.
 */

public class DownloadMarkerTypeInteractorImpl extends AbstractInteractor implements DownloadMarkerTypeInteractor {

	MarkerTypeRepository markerTypeRepository;
	DownloadMarkerTypeInteractor.Callback mCallback;

	public DownloadMarkerTypeInteractorImpl(Executor threadExecutor, MainThread mainThread, MarkerTypeRepository markerTypeRepository, Callback mCallback) {
		super(threadExecutor, mainThread);
		this.markerTypeRepository = markerTypeRepository;
		this.mCallback = mCallback;
	}

	@Override
	public void run() {

		final List<MarkerType> markerTypeList = markerTypeRepository.getMarkerTypesOnline();

		if (markerTypeList != null && markerTypeList.size() > 0){

			markerTypeRepository.deleteAllMarkerTypes();

			markerTypeRepository.saveMarkerTypeList(markerTypeList);

			mMainThread.post(new Runnable() {
				@Override
				public void run() {
					mCallback.onMarkerTypeDownloaded(markerTypeList);
				}
			});

		}else{

			mMainThread.post(new Runnable() {
				@Override
				public void run() {
					mCallback.onMarkerTypeDownloadFailed();
				}
			});

		}

	}
}
