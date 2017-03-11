package com.bitbucket.hackersforgood.citymob.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.bitbucket.hackersforgood.citymob.R;
import com.bitbucket.hackersforgood.citymob.executor.impl.ThreadExecutor;
import com.bitbucket.hackersforgood.citymob.presenters.RouteActivityPresenter;
import com.bitbucket.hackersforgood.citymob.presenters.impl.RouteActivityPresenterImpl;
import com.bitbucket.hackersforgood.citymob.threading.MainThreadImpl;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alexarraiza on 11/03/17.
 */

public class RouteActivity extends AppCompatActivity implements OnMapReadyCallback,
		RouteActivityPresenter.View {

	public static final String EXTRA_LAT = "EXTRA_LAT";
	public static final String EXTRA_LNG = "EXTRA_LNG";

	@BindView(R.id.editStart) EditText editStart;
	@BindView(R.id.editEnd) EditText editEnd;
	@BindView(R.id.btnOk) Button btnOk;
	@BindView(R.id.frameMapa) FrameLayout frameMapa;

	SupportMapFragment mMapFragment;

	double lat;
	double lng;

	Marker startPoint;
	Marker endPoint;

	GoogleMap googleMap;

	RouteActivityPresenter mPresenter;

	public static Intent getRouteActivityIntent(Context context, double latitude, double longitude) {

		Intent intent = new Intent(context, RouteActivity.class);

		intent.putExtra(EXTRA_LAT, latitude);
		intent.putExtra(EXTRA_LNG, longitude);

		return intent;
	}

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_route);
		ButterKnife.bind(this);

		lat = getIntent().getExtras().getDouble(EXTRA_LAT, 0);
		lng = getIntent().getExtras().getDouble(EXTRA_LNG, 0);

		editStart.setText("Su ubicación");

		mPresenter = new RouteActivityPresenterImpl(
				ThreadExecutor.getInstance(),
				MainThreadImpl.getInstance(),
				this
		);

		btnOk.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				if (startPoint != null && endPoint != null) {

					mPresenter.getRouteFromStartToFinish(startPoint.getPosition(), endPoint.getPosition());

				}

			}
		});

		showMap();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	public void onMapReady(final GoogleMap googleMap) {

		this.googleMap = googleMap;

		googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
			@Override
			public boolean onMarkerClick(Marker marker) {
				marker.showInfoWindow();
				return false;
			}
		});

		startPoint = googleMap.addMarker(new MarkerOptions()
				.position(new LatLng(lat, lng))
				.title("Inicio")
				.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

		startPoint.showInfoWindow();

		googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), 16));


		googleMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
			@Override
			public void onMapLongClick(LatLng latLng) {

				googleMap.clear();

				startPoint = googleMap.addMarker(new MarkerOptions()
						.position(new LatLng(lat, lng))
						.title("Inicio")
						.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

				startPoint.showInfoWindow();

				endPoint = googleMap.addMarker(new MarkerOptions()
						.position(latLng)
						.title("Final")
						.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

				endPoint.showInfoWindow();

				editEnd.setText(String.valueOf(endPoint.getPosition().latitude) + " " + String.valueOf(endPoint.getPosition().longitude));

			}
		});

	}

	@Override
	public void showMap() {

		if (mMapFragment == null && getSupportFragmentManager() != null) {
			mMapFragment = SupportMapFragment.newInstance();
			getSupportFragmentManager().beginTransaction().replace(R.id.frameMapa, mMapFragment).commit();
			if (mMapFragment != null) {
				mMapFragment.getMapAsync(this);
			}
		} else {
			//mPresenter.getMarkers();
		}

	}

	@Override
	public void showRoute(List<LatLng> latLngList) {

		PolylineOptions rectOptions = new PolylineOptions();

		for (LatLng latLng : latLngList) {
			rectOptions.add(latLng);
		}

		this.googleMap.addPolyline(rectOptions);

		mPresenter.getMarkers();

	}

	@Override
	public void showMarkersOnMap(List<com.bitbucket.hackersforgood.citymob.model.Marker> markerList) {
		if (this.googleMap != null) {

			for (com.bitbucket.hackersforgood.citymob.model.Marker marker : markerList) {

				com.google.android.gms.maps.model.Marker mapMarker = this.googleMap.addMarker(new MarkerOptions()
						.position(new LatLng(marker.getLat(), marker.getLng()))
						.title(marker.getTitle())
						.snippet(marker.getDescription())
						.icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_marker)));

			}

		}
	}

	@Override
	public void showProgress() {

	}

	@Override
	public void hideProgress() {

	}

	@Override
	public void showError(String message) {

	}
}
