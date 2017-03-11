package com.bitbucket.hackersforgood.citymob.ui.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;

import com.bitbucket.hackersforgood.citymob.R;
import com.bitbucket.hackersforgood.citymob.executor.impl.ThreadExecutor;
import com.bitbucket.hackersforgood.citymob.model.Marker;
import com.bitbucket.hackersforgood.citymob.model.MarkerType;
import com.bitbucket.hackersforgood.citymob.presenters.AddMarkerPresenter;
import com.bitbucket.hackersforgood.citymob.presenters.impl.AddMarkerPresenterImpl;
import com.bitbucket.hackersforgood.citymob.threading.MainThreadImpl;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.StreetViewPanoramaFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.SupportStreetViewPanoramaFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by alexarraiza on 10/03/17.
 */

public class AddMarkerFragment extends Fragment implements OnMapReadyCallback, AddMarkerPresenter.View,
		OnStreetViewPanoramaReadyCallback {

	public static final String ARG_LAT = "ARG_LAT";
	public static final String ARG_LNG = "ARG_LNG";

	SupportMapFragment mMapFragment;
	SupportStreetViewPanoramaFragment mStreetViewFragment;
	@BindView(R.id.frameMap) FrameLayout frameMap;
	@BindView(R.id.editTitle) EditText editTitle;
	@BindView(R.id.editDesc) EditText editDesc;
	@BindView(R.id.btnOk) Button btnOk;
	@BindView(R.id.spnMarkerTypes) Spinner spnMarkerTypes;
	@BindView(R.id.btnCancel) Button btnCancel;

	double lat;
	double lng;

	AddMarkerPresenter mPresenter;

	Context mContext;

	public static Fragment getAddMarkerFragment(double lat, double lng) {
		Bundle args = new Bundle();
		args.putDouble(ARG_LAT, lat);
		args.putDouble(ARG_LNG, lng);

		Fragment fragment = new AddMarkerFragment();
		fragment.setArguments(args);

		return fragment;
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View v = inflater.inflate(R.layout.fragment_add_marker, container, false);
		ButterKnife.bind(this, v);
		setHasOptionsMenu(true);

		this.lat = getArguments().getDouble(ARG_LAT, 0);
		this.lng = getArguments().getDouble(ARG_LNG, 0);

		return v;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		mPresenter = new AddMarkerPresenterImpl(
				ThreadExecutor.getInstance(),
				MainThreadImpl.getInstance(),
				this
		);

		mPresenter.getMarkerTypes();

		if (mMapFragment == null && getActivity() != null && getChildFragmentManager() != null) {
			mMapFragment = SupportMapFragment.newInstance();
			getChildFragmentManager().beginTransaction().replace(R.id.frameMap, mMapFragment).commit();
			if (mMapFragment != null) {
				mMapFragment.getMapAsync(this);
			}
		} else {
			// TODO: 10/03/17 recargar markers
		}

		if (mStreetViewFragment == null && getActivity() != null && getChildFragmentManager() != null) {
			mStreetViewFragment = SupportStreetViewPanoramaFragment.newInstance();
			getChildFragmentManager().beginTransaction().replace(R.id.streetviewpanorama, mStreetViewFragment).commit();
			if (mStreetViewFragment != null) {
				mStreetViewFragment.getStreetViewPanoramaAsync(this);
			}
		} else {
			// TODO: 10/03/17 recargar markers
		}
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		this.mContext = context;
	}

	@Override
	public void onMapReady(GoogleMap googleMap) {

		com.google.android.gms.maps.model.Marker marker = googleMap.addMarker(new MarkerOptions()
				.position(new LatLng(lat, lng))
				.title("Localización escogida")
				.icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_marker)));

		googleMap.getUiSettings().setAllGesturesEnabled(false);

		googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), 16));

	}

	@OnClick(R.id.btnCancel)
	@Override
	public void onClickCancelBtn() {

		((Activity) mContext).setResult(Activity.RESULT_CANCELED);
		((Activity) mContext).finish();

	}

	@OnClick(R.id.btnOk)
	@Override
	public void onClickOkBtn() {

		Marker marker = new Marker();
		marker.setLat(this.lat);
		marker.setLng(this.lng);
		marker.setTitle(editTitle.getText().toString());
		marker.setDescription(editDesc.getText().toString());
		marker.setActive(true);
		marker.setOnDate(new Date());

		if (spnMarkerTypes.getAdapter() != null) {
			marker.setIdMarkerType(((MarkerType) spnMarkerTypes.getSelectedItem()).getIdAuto());
		}

		mPresenter.saveMarkerOnline(marker);

	}

	@Override
	public void onMarkerSaved(Marker marker) {
		AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
		alert.setTitle("Guardado");
		alert.setMessage("Guardado realizado con éxito");
		alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialogInterface, int i) {
				dialogInterface.dismiss();
				((Activity) mContext).setResult(Activity.RESULT_OK);
				((Activity) mContext).finish();
			}
		});
		alert.setCancelable(false);
		alert.show();
	}

	@Override
	public void onMarkerSaveError() {
		AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
		alert.setTitle("Error de guardado");
		alert.setMessage("Se ha producido un error al guardar, por favor, inténtelo de nuevo.");
		alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialogInterface, int i) {
				dialogInterface.dismiss();
				((Activity) mContext).setResult(Activity.RESULT_CANCELED);
				((Activity) mContext).finish();
			}
		});
		alert.setCancelable(false);
		alert.show();
	}

	@Override
	public void showMarkerTypes(List<MarkerType> markerTypeList) {

		ArrayAdapter<MarkerType> markerTypeArrayAdapter = new ArrayAdapter<>(mContext, android.R.layout.simple_spinner_dropdown_item, markerTypeList);
		spnMarkerTypes.setAdapter(markerTypeArrayAdapter);

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
	public void onStreetViewPanoramaReady(StreetViewPanorama streetViewPanorama) {

		streetViewPanorama.setUserNavigationEnabled(false);

		streetViewPanorama.setPosition(new LatLng(lat, lng));


	}
}
