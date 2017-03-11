package com.bitbucket.hackersforgood.citymob.ui.activities;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.bitbucket.hackersforgood.citymob.R;
import com.bitbucket.hackersforgood.citymob.RequestCodeConstants;
import com.bitbucket.hackersforgood.citymob.executor.impl.ThreadExecutor;
import com.bitbucket.hackersforgood.citymob.model.Marker;
import com.bitbucket.hackersforgood.citymob.model.MarkerType;
import com.bitbucket.hackersforgood.citymob.presenters.MainActivityPresenter;
import com.bitbucket.hackersforgood.citymob.presenters.impl.MainActivityPresenterImpl;
import com.bitbucket.hackersforgood.citymob.threading.MainThreadImpl;
import com.bitbucket.hackersforgood.citymob.ui.fragments.MainMapFragment;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by alexarraiza on 10/03/17.
 */

public class MainActivity extends AppCompatActivity implements MainActivityPresenter.View {

	MainActivityPresenter mPresenter;

	ProgressDialog mProgressDialog;

	boolean isFuckingPaused = false;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);

		mPresenter = new MainActivityPresenterImpl(
				ThreadExecutor.getInstance(),
				MainThreadImpl.getInstance(),
				this
		);

		checkLocationPermission();

	}

	@Override
	protected void onResume() {
		super.onResume();
		isFuckingPaused = false;
	}

	@Override
	protected void onPause() {
		super.onPause();
		isFuckingPaused = true;
	}

	@Override
	public void showProgress() {

		if (mProgressDialog == null) {
			mProgressDialog = new ProgressDialog(this);
			mProgressDialog.setTitle("Cargando");
			mProgressDialog.setMessage("Espere mientras se cargan los datos");
			mProgressDialog.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_info_outline_black_24dp));
			mProgressDialog.setIndeterminate(true);
			mProgressDialog.setCancelable(false);
		}

		if (!(this).isDestroyed() && !isFuckingPaused && !mProgressDialog.isShowing() && getSupportFragmentManager().getFragments() != null) {
			mProgressDialog.show();
		}

	}

	@Override
	public void hideProgress() {

		if (!(this.isDestroyed()) && mProgressDialog != null && mProgressDialog.isShowing()) {
			mProgressDialog.dismiss();
		}

	}

	@Override
	public void showError(String message) {

		hideProgress();

		showMainMapFragment();

	}

	@Override
	public void checkLocationPermission() {
		if (
				ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
						&& ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
						|| ActivityCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED
				) {

			ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.INTERNET}, RequestCodeConstants.REQUEST_PERMISSION_LOCATION);

		} else {

			mPresenter.getMarkersOnline();


		}
	}

	@Override
	public void onMarkersDownloaded(List<Marker> markerList) {

		mPresenter.getMarkerTypeOnline();

	}

	@Override
	public void onMarkerTypeDownloaded(List<MarkerType> markerTypeList) {
		hideProgress();

		showMainMapFragment();
	}

	@Override
	public void showMainMapFragment() {
		android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
		fragmentTransaction.replace(R.id.mainFrame, MainMapFragment.getMainMapFragment());
		fragmentTransaction.commit();
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

		switch (requestCode) {

			case RequestCodeConstants.REQUEST_PERMISSION_LOCATION: {
				mPresenter.getMarkersOnline();
				break;
			}

			default: {
				super.onRequestPermissionsResult(requestCode, permissions, grantResults);
				break;
			}

		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_mainmapafragment, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {

			case R.id.refresh: {

				mPresenter.getMarkersOnline();

				return true;
			}

			default: {
				return super.onOptionsItemSelected(item);
			}

		}

	}
}
