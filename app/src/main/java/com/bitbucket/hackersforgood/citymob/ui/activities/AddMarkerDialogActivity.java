package com.bitbucket.hackersforgood.citymob.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.content.MutableContextWrapper;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.bitbucket.hackersforgood.citymob.R;
import com.bitbucket.hackersforgood.citymob.ui.fragments.AddMarkerFragment;
import com.google.android.gms.maps.model.LatLng;

import butterknife.ButterKnife;

/**
 * Created by alexarraiza on 10/03/17.
 */

public class AddMarkerDialogActivity extends AppCompatActivity {

	public static final String EXTRA_LAT = "EXTRA_LAT";
	public static final String EXTRA_LNG = "EXTRA_LNG";

	double lat;
	double lng;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_marker_dialog);
		ButterKnife.bind(this);

		lat = getIntent().getExtras().getDouble(EXTRA_LAT,0);
		lng = getIntent().getExtras().getDouble(EXTRA_LNG,0);

		android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
		fragmentTransaction.replace(R.id.parentFrame, AddMarkerFragment.getAddMarkerFragment(lat, lng));
		fragmentTransaction.commit();

	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	public static Intent getAddMarkerDialogActivity(Context context, LatLng latLng){

		Intent intent = new Intent(context, AddMarkerDialogActivity.class);

		intent.putExtra(EXTRA_LAT, latLng.latitude);
		intent.putExtra(EXTRA_LNG, latLng.longitude);

		return intent;

	}

}
