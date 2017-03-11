package com.bitbucket.hackersforgood.citymob.presenters;

import com.bitbucket.hackersforgood.citymob.model.Marker;
import com.bitbucket.hackersforgood.citymob.model.MarkerType;
import com.bitbucket.hackersforgood.citymob.presenters.base.BasePresenter;
import com.bitbucket.hackersforgood.citymob.ui.BaseView;

import java.util.List;

/**
 * Created by alexarraiza on 10/03/17.
 */

public interface AddMarkerPresenter extends BasePresenter {

	void saveMarkerOnline(Marker marker);

	void getMarkerTypes();

	interface View extends BaseView {

		void onClickCancelBtn();

		void onClickOkBtn();

		void onMarkerSaved(Marker marker);

		void onMarkerSaveError();

		void showMarkerTypes(List<MarkerType> markerTypeList);

	}

}
