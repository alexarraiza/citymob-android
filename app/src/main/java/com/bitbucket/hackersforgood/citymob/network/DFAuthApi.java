package com.bitbucket.hackersforgood.citymob.network;


import com.bitbucket.hackersforgood.citymob.NetworkConstants;
import com.bitbucket.hackersforgood.citymob.model.df.DreamFactoryLogin;
import com.bitbucket.hackersforgood.citymob.model.df.DreamFactorySession;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface DFAuthApi {


	//@Headers(NetworkConstants.DREAMFACTORY_APPLICATION_NAME)
	@POST(NetworkConstants.DREAMFACTORY_USER_SESSION)
	Call<DreamFactorySession> loginDreamfactoryObservable(@Body DreamFactoryLogin mDreamFactoryLogin);

}
