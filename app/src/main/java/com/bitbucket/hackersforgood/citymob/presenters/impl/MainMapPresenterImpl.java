package com.bitbucket.hackersforgood.citymob.presenters.impl;

import com.bitbucket.hackersforgood.citymob.executor.Executor;
import com.bitbucket.hackersforgood.citymob.executor.MainThread;
import com.bitbucket.hackersforgood.citymob.interactors.GetAllMarkersInteractor;
import com.bitbucket.hackersforgood.citymob.interactors.impl.GetAllMarkersInteractorImpl;
import com.bitbucket.hackersforgood.citymob.model.Marker;
import com.bitbucket.hackersforgood.citymob.presenters.base.AbstractPresenter;
import com.bitbucket.hackersforgood.citymob.presenters.MainMapPresenter;
import com.bitbucket.hackersforgood.citymob.repository.impl.MarkerRepositoryImpl;

import java.util.List;

/**
 * Created by alexarraiza on 10/03/17.
 */

public class MainMapPresenterImpl extends AbstractPresenter implements MainMapPresenter,
		GetAllMarkersInteractor.Callback {

	MainMapPresenter.View mView;

	public MainMapPresenterImpl(Executor executor, MainThread mainThread, View mView) {
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
	public void onMarkersRetrieved(List<Marker> markerList) {
		mView.showAdapter(markerList);
		mView.showMarkersOnMap(markerList);
	}

	@Override
	public void onMarkersNotFound() {
		mView.emptyAdapter();
	}
}
