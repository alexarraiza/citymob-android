package com.bitbucket.hackersforgood.citymob.interactors.impl;

import com.bitbucket.hackersforgood.citymob.executor.Executor;
import com.bitbucket.hackersforgood.citymob.executor.MainThread;
import com.bitbucket.hackersforgood.citymob.interactors.GetAllMarkerTypesInteractor;
import com.bitbucket.hackersforgood.citymob.interactors.base.AbstractInteractor;
import com.bitbucket.hackersforgood.citymob.model.MarkerType;
import com.bitbucket.hackersforgood.citymob.repository.MarkerTypeRepository;

import java.util.List;

/**
 * Created by alexarraiza on 11/03/17.
 */

public class GetAllMarkerTypesInteractorImpl extends AbstractInteractor implements GetAllMarkerTypesInteractor {

	MarkerTypeRepository markerTypeRepository;
	GetAllMarkerTypesInteractor.Callback mCallback;

	public GetAllMarkerTypesInteractorImpl(Executor threadExecutor, MainThread mainThread, MarkerTypeRepository markerTypeRepository, Callback mCallback) {
		super(threadExecutor, mainThread);
		this.markerTypeRepository = markerTypeRepository;
		this.mCallback = mCallback;
	}

	@Override
	public void run() {

		final List<MarkerType> markerTypeList = markerTypeRepository.getAllMarkerTypes();

		if (markerTypeList.size() > 0){

			mMainThread.post(new Runnable() {
				@Override
				public void run() {
					mCallback.onMarkerTypesRetrieved(markerTypeList);
				}
			});

		}else{

			mMainThread.post(new Runnable() {
				@Override
				public void run() {
					mCallback.onMarkerTypeNotFound();
				}
			});

		}

	}
}
