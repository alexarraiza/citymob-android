package com.bitbucket.hackersforgood.citymob;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by alexarraiza on 10/03/17.
 */

public class CitymobApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		FlowManager.init(new FlowConfig.Builder(this)
				.openDatabasesOnInit(true).build());
		Stetho.initializeWithDefaults(this);
	}

}
