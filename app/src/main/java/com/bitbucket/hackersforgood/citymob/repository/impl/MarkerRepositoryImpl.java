package com.bitbucket.hackersforgood.citymob.repository.impl;

import android.util.Log;

import com.bitbucket.hackersforgood.citymob.NetworkConstants;
import com.bitbucket.hackersforgood.citymob.model.Marker;
import com.bitbucket.hackersforgood.citymob.model.df.DreamFactoryLogin;
import com.bitbucket.hackersforgood.citymob.model.df.DreamFactorySession;
import com.bitbucket.hackersforgood.citymob.model.df.GetRecordsRequest;
import com.bitbucket.hackersforgood.citymob.model.df.RecordsRequest;
import com.bitbucket.hackersforgood.citymob.model.df.RecordsResponseTyped;
import com.bitbucket.hackersforgood.citymob.network.DFAuthApi;
import com.bitbucket.hackersforgood.citymob.network.DataApi;
import com.bitbucket.hackersforgood.citymob.repository.MarkerRepository;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.Delete;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by alexarraiza on 10/03/17.
 */

public class MarkerRepositoryImpl implements MarkerRepository {

	private static final String TAG = "MarkerRepositoryImpl";

	@Override
	public List<Marker> getMarkers() {

		return new Select()
				.from(Marker.class)
				.queryList();

	}

	@Override
	public List<Marker> getMarkersOnline() {

		List<Marker> markerList = null;

		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(NetworkConstants.DREAMFACTORY_DATA_DOWNLOAD_CONNECTION)
				.addConverterFactory(GsonConverterFactory.create())
				.build();

		DFAuthApi service = retrofit.create(DFAuthApi.class);

		try {

			Response<DreamFactorySession> loginResponse = service.loginDreamfactoryObservable(new DreamFactoryLogin("citymob@citymob.com", "city!123", 0)).execute();

			Log.i(TAG, "getMarkersOnline: " + loginResponse.toString());

			DataApi dataApi = retrofit.create(DataApi.class);

			Response<RecordsRequest<Marker>> response = dataApi.getMarkers(
					loginResponse.body().getSession_id(),
					"GET",
					NetworkConstants.DREAMFACTORY_API_KEY,
					new GetRecordsRequest("ACTIVE = 1")).execute();

			Log.i(TAG, "getMarkersOnline: " + response.toString());

			if (response.body() != null && response.body().getResource() != null) {
				markerList = response.body().getResource();
			}

		} catch (Exception e) {
			Log.e(TAG, "getMarkersOnline: ", e);
		}

		return markerList;
	}

	@Override
	public void deleteAllMarkers() {
		new Delete()
				.from(Marker.class)
				.execute();
	}

	@Override
	public Marker saveMarker(Marker marker) {

		FlowManager.getModelAdapter(Marker.class).save(marker);

		return marker;
	}

	@Override
	public List<Marker> saveMarkerList(List<Marker> markerList) {

		for (Marker marker : markerList) {
			saveMarker(marker);
		}

		return markerList;
	}

	@Override
	public Marker saveMarkerOnline(Marker marker) {

		Marker markerSaved = null;

		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(NetworkConstants.DREAMFACTORY_DATA_DOWNLOAD_CONNECTION)
				.addConverterFactory(GsonConverterFactory.create())
				.build();

		DFAuthApi service = retrofit.create(DFAuthApi.class);

		try {

			Response<DreamFactorySession> loginResponse = service.loginDreamfactoryObservable(new DreamFactoryLogin("citymob@citymob.com", "city!123", 0)).execute();

			Log.i(TAG, "getMarkersOnline: " + loginResponse.toString());

			DataApi dataApi = retrofit.create(DataApi.class);

			List<Marker> markerList = new ArrayList<Marker>();
			markerList.add(marker);

			RecordsRequest<Marker> recordsRequest = new RecordsRequest<>(markerList);

			Response<RecordsResponseTyped<Marker>> response = dataApi.uploadMarker(
					loginResponse.body().getSession_id(),
					"POST",
					NetworkConstants.DREAMFACTORY_API_KEY,
					recordsRequest,
					"ID_AUTO"
			).execute();

			Log.i(TAG, "getMarkersOnline: " + response.toString());

			if (response.body() != null && response.body().getResource() != null && response.body().getResource().length > 0) {
				marker.setIdAuto(response.body().getResource()[0].getIdAuto());
				markerSaved = saveMarker(marker);
			}

		} catch (Exception e) {
			Log.e(TAG, "getMarkersOnline: ", e);
		}

		return markerSaved;
	}
}
