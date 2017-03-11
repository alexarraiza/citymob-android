package com.bitbucket.hackersforgood.citymob.presenters.impl;

import com.bitbucket.hackersforgood.citymob.executor.Executor;
import com.bitbucket.hackersforgood.citymob.executor.MainThread;
import com.bitbucket.hackersforgood.citymob.interactors.GetAllMarkerTypesInteractor;
import com.bitbucket.hackersforgood.citymob.interactors.SaveMarkerOnlineInteractor;
import com.bitbucket.hackersforgood.citymob.interactors.impl.GetAllMarkerTypesInteractorImpl;
import com.bitbucket.hackersforgood.citymob.interactors.impl.SaveMarkerOnlineInteractorImpl;
import com.bitbucket.hackersforgood.citymob.model.Marker;
import com.bitbucket.hackersforgood.citymob.model.MarkerType;
import com.bitbucket.hackersforgood.citymob.presenters.AddMarkerPresenter;
import com.bitbucket.hackersforgood.citymob.presenters.base.AbstractPresenter;
import com.bitbucket.hackersforgood.citymob.repository.impl.MarkerRepositoryImpl;
import com.bitbucket.hackersforgood.citymob.repository.impl.MarkerTypeRepositoryImpl;

import java.util.List;

/**
 * Created by alexarraiza on 10/03/17.
 */

public class AddMarkerPresenterImpl extends AbstractPresenter implements AddMarkerPresenter,
		SaveMarkerOnlineInteractor.Callback,
		GetAllMarkerTypesInteractor.Callback {

	AddMarkerPresenter.View mView;

	public AddMarkerPresenterImpl(Executor executor, MainThread mainThread, View mView) {
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
	public void saveMarkerOnline(Marker marker) {

		SaveMarkerOnlineInteractor saveMarkerOnlineInteractor = new SaveMarkerOnlineInteractorImpl(
				mExecutor,
				mMainThread,
				marker,
				new MarkerRepositoryImpl(),
				this
		);

		saveMarkerOnlineInteractor.execute();

	}

	@Override
	public void getMarkerTypes() {

		GetAllMarkerTypesInteractor getAllMarkerTypesInteractor = new GetAllMarkerTypesInteractorImpl(
				mExecutor,
				mMainThread,
				new MarkerTypeRepositoryImpl(),
				this
		);

		getAllMarkerTypesInteractor.execute();

	}

	@Override
	public void onMarkerSaved(Marker marker) {
		mView.onMarkerSaved(marker);
	}

	@Override
	public void onMarkerSaveError() {
		mView.onMarkerSaveError();
	}

	@Override
	public void onMarkerTypesRetrieved(List<MarkerType> markerTypeList) {
		mView.showMarkerTypes(markerTypeList);
	}

	@Override
	public void onMarkerTypeNotFound() {

	}
}
