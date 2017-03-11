package com.bitbucket.hackersforgood.citymob.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bitbucket.hackersforgood.citymob.R;

import butterknife.ButterKnife;

/**
 * Created by alexarraiza on 10/03/17.
 */

public class LoginFragment extends Fragment {

	public static Fragment getLoginFragment() {
		return new LoginFragment();
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View v = inflater.inflate(R.layout.fragment_login, container, false);
		ButterKnife.bind(this, v);
		setHasOptionsMenu(true);
		return v;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onResume() {
		super.onResume();
	}

}
