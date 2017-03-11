package com.bitbucket.hackersforgood.citymob.ui.fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;

import com.bitbucket.hackersforgood.citymob.R;
import com.bitbucket.hackersforgood.citymob.RequestCodeConstants;
import com.bitbucket.hackersforgood.citymob.executor.impl.ThreadExecutor;
import com.bitbucket.hackersforgood.citymob.model.Marker;
import com.bitbucket.hackersforgood.citymob.presenters.MainMapPresenter;
import com.bitbucket.hackersforgood.citymob.presenters.impl.MainMapPresenterImpl;
import com.bitbucket.hackersforgood.citymob.threading.MainThreadImpl;
import com.bitbucket.hackersforgood.citymob.ui.activities.AddMarkerDialogActivity;
import com.bitbucket.hackersforgood.citymob.ui.activities.RouteActivity;
import com.bitbucket.hackersforgood.citymob.ui.adabter.MarkersAdapter;
import com.bitbucket.hackersforgood.citymob.ui.custom_views.SimpleDividerItemDecoration;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by alexarraiza on 10/03/17.
 */

public class MainMapFragment extends Fragment implements OnMapReadyCallback,
		MainMapPresenter.View {

	@BindView(R.id.frameMainMap) FrameLayout frameMainMap;

	@BindView(R.id.gridmarkers) RecyclerView gridmarkers;

	SupportMapFragment mMapFragment;

	Context mContext;

	GoogleMap mGoogleMap;

	MainMapPresenter mPresenter;

	List<com.google.android.gms.maps.model.Marker> mapMarkersList = new ArrayList<>();
	@BindView(R.id.fabRoute) FloatingActionButton fabRoute;
	@BindView(R.id.spinnerDiscapacidades) Spinner spinnerDiscapacidades;


	public static Fragment getMainMapFragment() {

		return new MainMapFragment();

	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		this.mContext = context;
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View v = inflater.inflate(R.layout.fragment_main_map, container, false);
		ButterKnife.bind(this, v);
		return v;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		mPresenter = new MainMapPresenterImpl(
				ThreadExecutor.getInstance(),
				MainThreadImpl.getInstance(),
				this
		);

		showSpinnerDiscapacidades();

		showMap();

	}

	@Override
	public void onMapReady(GoogleMap googleMap) {

		mPresenter.getMarkers();

		this.mGoogleMap = googleMap;

		mGoogleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
			@Override
			public boolean onMarkerClick(com.google.android.gms.maps.model.Marker marker) {

				mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(marker.getPosition(), 16));

				marker.showInfoWindow();

				return true;
			}
		});

		centerMapOnUser(googleMap);

		this.mGoogleMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
			@Override
			public void onMapLongClick(LatLng latLng) {
				onLongClickMap(latLng);
			}
		});

	}

	@Override
	public void showMap() {

		if (mMapFragment == null && getActivity() != null && getChildFragmentManager() != null) {
			mMapFragment = SupportMapFragment.newInstance();
			getChildFragmentManager().beginTransaction().replace(R.id.frameMainMap, mMapFragment).commit();
			if (mMapFragment != null) {
				mMapFragment.getMapAsync(this);
			}
		} else {
			mPresenter.getMarkers();
		}

	}

	@Override
	public void showAdapter(List<Marker> markerList) {

		MarkersAdapter mMarkersAdapter = new MarkersAdapter(this.mContext, markerList, this);
		gridmarkers.setLayoutManager(new LinearLayoutManager(this.mContext));
		gridmarkers.setAdapter(mMarkersAdapter);
		gridmarkers.addItemDecoration(new SimpleDividerItemDecoration(mContext));

	}

	@Override
	public void showMarkersOnMap(List<Marker> markerList) {

		if (this.mGoogleMap != null) {

			this.mGoogleMap.clear();

			this.mapMarkersList.clear();

			for (Marker marker : markerList) {

				com.google.android.gms.maps.model.Marker mapMarker = this.mGoogleMap.addMarker(new MarkerOptions()
						.position(new LatLng(marker.getLat(), marker.getLng()))
						.title(marker.getTitle())
						.snippet(marker.getDescription())
						.icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_marker)));

				this.mapMarkersList.add(mapMarker);

			}

		}

	}

	@Override
	public void emptyAdapter() {

	}

	@Override
	public void centerMapOnUser(GoogleMap googleMap) {
		if (ActivityCompat.checkSelfPermission(this.mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

			ActivityCompat.requestPermissions(((Activity) this.mContext), new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, RequestCodeConstants.REQUEST_PERMISSION_LOCATION);

		} else {

			googleMap.setMyLocationEnabled(true);

		}
	}

	@Override
	public void onClickMarkerAdapter(Marker marker) {

		if (this.mGoogleMap != null) {

			for (com.google.android.gms.maps.model.Marker mapMarker : this.mapMarkersList) {
				if (mapMarker.getPosition().equals(new LatLng(marker.getLat(), marker.getLng()))) {
					mapMarker.showInfoWindow();
				} else {
					mapMarker.hideInfoWindow();
				}
			}

			this.mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(marker.getLat(), marker.getLng()), 16));

		}

	}

	@Override
	public void onClickMarkerMap() {

	}

	@Override
	public void onLongClickMap(LatLng latLng) {

		com.google.android.gms.maps.model.Marker marker = this.mGoogleMap.addMarker(new MarkerOptions()
				.position(latLng)
				.title("Localización escogida")
				.icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_marker)));

		startActivityForResult(AddMarkerDialogActivity.getAddMarkerDialogActivity(mContext, marker.getPosition()), RequestCodeConstants.REQUEST_CODE_ADD_MARKER_DIALOG);

	}

	@Override
	public void showSpinnerDiscapacidades() {

		List<String> discapacidadesList = new ArrayList<>();
		discapacidadesList.add("PRIORIZAR EVENTOS SEGÚN DISCAPACIDAD");
		discapacidadesList.add("MOVILIDAD REDUCIDA");
		discapacidadesList.add("VISIBILIDAD REDUCIDA");
		discapacidadesList.add("AUDICIÓN REDUCIDA");

		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_dropdown_item, discapacidadesList);
		spinnerDiscapacidades.setAdapter(arrayAdapter);

	}

	@OnClick(R.id.fabRoute)
	@Override
	public void onClickRouteBtn() {

		Location location = this.mGoogleMap.getMyLocation();

		if(location != null) {

			startActivityForResult(RouteActivity.getRouteActivityIntent(mContext, location.getLatitude(), location.getLongitude()), RequestCodeConstants.REQUEST_CODE_ROUTE);

		}else{

			AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
			alert.setTitle("Error de localización");
			alert.setMessage("No se ha podido detectar su ubicación, por favor, inténtelo de nuevo más tarde.");
			alert.setCancelable(false);
			alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialogInterface, int i) {
					dialogInterface.dismiss();
				}
			});
			alert.show();

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

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {

		switch (requestCode) {

			case RequestCodeConstants.REQUEST_CODE_ADD_MARKER_DIALOG: {
				mPresenter.getMarkers();
				break;
			}

			default: {
				super.onActivityResult(requestCode, resultCode, data);
			}

		}

	}
}
