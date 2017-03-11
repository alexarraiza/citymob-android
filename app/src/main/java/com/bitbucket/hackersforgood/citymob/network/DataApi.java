package com.bitbucket.hackersforgood.citymob.network;

import com.bitbucket.hackersforgood.citymob.NetworkConstants;
import com.bitbucket.hackersforgood.citymob.model.Marker;
import com.bitbucket.hackersforgood.citymob.model.MarkerType;
import com.bitbucket.hackersforgood.citymob.model.df.GetRecordsRequest;
import com.bitbucket.hackersforgood.citymob.model.df.RecordsRequest;
import com.bitbucket.hackersforgood.citymob.model.df.RecordsResponseTyped;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by alexarraiza on 10/03/17.
 */

public interface DataApi {

	@Headers(NetworkConstants.DREAMFACTORY_APPLICATION_NAME)
	@POST(NetworkConstants.DREAMFACTORY_DATA_DOWNLOAD_CONNECTION + "api/v2/citymob/_table/MARKER")
	Call<RecordsRequest<Marker>> getMarkers(@Header(NetworkConstants.DREAMFACTORY_SESSION_HEADER) String sessionId,
											@Header(NetworkConstants.DREAMFACTORY_HTTP_METHOD_HEADER) String httpMethod, @Header(NetworkConstants.DREAMFACTORY_API_KEY_HEADER) String apiKey,
											@Body GetRecordsRequest filter);

	@Headers(NetworkConstants.DREAMFACTORY_APPLICATION_NAME)
	@POST(NetworkConstants.DREAMFACTORY_DATA_DOWNLOAD_CONNECTION + "api/v2/citymob/_table/MARKER_TYPE")
	Call<RecordsRequest<MarkerType>> getMarkerTypes(@Header(NetworkConstants.DREAMFACTORY_SESSION_HEADER) String sessionId,
													@Header(NetworkConstants.DREAMFACTORY_HTTP_METHOD_HEADER) String httpMethod, @Header(NetworkConstants.DREAMFACTORY_API_KEY_HEADER) String apiKey,
													@Body GetRecordsRequest filter);

	@Headers(NetworkConstants.DREAMFACTORY_APPLICATION_NAME)
	@POST(NetworkConstants.DREAMFACTORY_DATA_DOWNLOAD_CONNECTION + "api/v2/citymob/_table/MARKER")
	Call<RecordsResponseTyped<Marker>> uploadMarker(@Header(NetworkConstants.DREAMFACTORY_SESSION_HEADER) String sessionId,
													@Header(NetworkConstants.DREAMFACTORY_HTTP_METHOD_HEADER) String httpMethod, @Header(NetworkConstants.DREAMFACTORY_API_KEY_HEADER) String apiKey,
													@Body RecordsRequest<Marker> recordsRequest,
													@Query("fields") String fields);
}
