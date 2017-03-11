package com.bitbucket.hackersforgood.citymob.repository.impl;

import android.util.Log;

import com.bitbucket.hackersforgood.citymob.NetworkConstants;
import com.bitbucket.hackersforgood.citymob.model.MarkerType;
import com.bitbucket.hackersforgood.citymob.model.df.DreamFactoryLogin;
import com.bitbucket.hackersforgood.citymob.model.df.DreamFactorySession;
import com.bitbucket.hackersforgood.citymob.model.df.GetRecordsRequest;
import com.bitbucket.hackersforgood.citymob.model.df.RecordsRequest;
import com.bitbucket.hackersforgood.citymob.network.DFAuthApi;
import com.bitbucket.hackersforgood.citymob.network.DataApi;
import com.bitbucket.hackersforgood.citymob.repository.MarkerTypeRepository;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.Delete;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.List;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by alexarraiza on 11/03/17.
 */

public class MarkerTypeRepositoryImpl implements MarkerTypeRepository {

	private static final String TAG = "MarkerTypeRepository";

	@Override
	public List<MarkerType> getAllMarkerTypes() {
		return new Select()
				.from(MarkerType.class)
				.queryList();
	}

	@Override
	public List<MarkerType> getMarkerTypesOnline() {

		List<MarkerType> markerList = null;

		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(NetworkConstants.DREAMFACTORY_DATA_DOWNLOAD_CONNECTION)
				.addConverterFactory(GsonConverterFactory.create())
				.build();

		DFAuthApi service = retrofit.create(DFAuthApi.class);

		try {

			Response<DreamFactorySession> loginResponse = service.loginDreamfactoryObservable(new DreamFactoryLogin("citymob@citymob.com", "city!123", 0)).execute();

			Log.i(TAG, "getMarkersOnline: " + loginResponse.toString());

			DataApi dataApi = retrofit.create(DataApi.class);

			Response<RecordsRequest<MarkerType>> response = dataApi.getMarkerTypes(
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
	public MarkerType saveMarkerType(MarkerType marker) {
		FlowManager.getModelAdapter(MarkerType.class).save(marker);

		return marker;
	}

	@Override
	public void deleteAllMarkerTypes() {
		new Delete()
				.from(MarkerType.class)
				.execute();
	}

	@Override
	public List<MarkerType> saveMarkerTypeList(List<MarkerType> markerList) {
		for (MarkerType marker : markerList) {
			saveMarkerType(marker);
		}

		return markerList;
	}
}
