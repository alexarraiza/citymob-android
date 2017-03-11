package com.bitbucket.hackersforgood.citymob.presenters.impl;

import com.bitbucket.hackersforgood.citymob.executor.Executor;
import com.bitbucket.hackersforgood.citymob.executor.MainThread;
import com.bitbucket.hackersforgood.citymob.interactors.DownloadMarkerTypeInteractor;
import com.bitbucket.hackersforgood.citymob.interactors.DownloadMarkersInteractor;
import com.bitbucket.hackersforgood.citymob.interactors.impl.DownloadMarkerTypeInteractorImpl;
import com.bitbucket.hackersforgood.citymob.interactors.impl.DownloadMarkersInteractorImpl;
import com.bitbucket.hackersforgood.citymob.model.Marker;
import com.bitbucket.hackersforgood.citymob.model.MarkerType;
import com.bitbucket.hackersforgood.citymob.presenters.MainActivityPresenter;
import com.bitbucket.hackersforgood.citymob.presenters.base.AbstractPresenter;
import com.bitbucket.hackersforgood.citymob.repository.impl.MarkerRepositoryImpl;
import com.bitbucket.hackersforgood.citymob.repository.impl.MarkerTypeRepositoryImpl;

import java.util.List;

/**
 * Created by alexarraiza on 10/03/17.
 */

public class MainActivityPresenterImpl extends AbstractPresenter implements MainActivityPresenter,
		DownloadMarkersInteractor.Callback,
		DownloadMarkerTypeInteractor.Callback {

	MainActivityPresenter.View mView;

	public MainActivityPresenterImpl(Executor executor, MainThread mainThread, View mView) {
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
	public void getMarkersOnline() {

		mView.showProgress();

		DownloadMarkersInteractor downloadMarkersInteractor = new DownloadMarkersInteractorImpl(
				mExecutor,
				mMainThread,
				new MarkerRepositoryImpl(),
				this
		);

		downloadMarkersInteractor.execute();
	}

	@Override
	public void getMarkerTypeOnline() {

		mView.showProgress();

		DownloadMarkerTypeInteractor downloadMarkerTypeInteractor = new DownloadMarkerTypeInteractorImpl(
				mExecutor,
				mMainThread,
				new MarkerTypeRepositoryImpl(),
				this
		);

		downloadMarkerTypeInteractor.execute();

	}

	@Override
	public void onMarkersDownloaded(List<Marker> markerList) {
		mView.onMarkersDownloaded(markerList);
	}

	@Override
	public void onMarkersNotFound() {
		mView.showError("Error de descarga");
	}

	@Override
	public void onMarkerTypeDownloaded(List<MarkerType> markerTypeList) {
		mView.onMarkerTypeDownloaded(markerTypeList);
	}

	@Override
	public void onMarkerTypeDownloadFailed() {
		mView.showError("Error de descarga");
	}
}
