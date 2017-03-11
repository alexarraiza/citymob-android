package com.bitbucket.hackersforgood.citymob.ui.adabter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bitbucket.hackersforgood.citymob.R;
import com.bitbucket.hackersforgood.citymob.model.Marker;
import com.bitbucket.hackersforgood.citymob.presenters.MainMapPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by batch_tal4md5 on 10/03/2017.
 */

public class MarkersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

	Context mContext;
	List<Marker> ListMarker;
	Marker AnteriorMarker = null;

	MainMapPresenter.View mView;

	public MarkersAdapter(Context mContext, List<Marker> listMarker, MainMapPresenter.View mView) {
		this.mContext = mContext;
		ListMarker = listMarker;
		this.mView = mView;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		LayoutInflater inflater = LayoutInflater.from(mContext);

		View view = inflater.inflate(R.layout.item_marker, parent, false);
		RecyclerView.ViewHolder holder = new ViewHolder(view, mContext);

		return holder;
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		if (position >= 0) {
			Marker mMarker = ListMarker.get(position);

			((ViewHolder) holder).setup(mMarker);

		}
	}

	@Override
	public int getItemCount() {
		return ListMarker.size();
	}

	public class ViewHolder extends RecyclerView.ViewHolder {

		Context mContext;

		@BindView(R.id.icon) AppCompatImageView icon;
		@BindView(R.id.title) AppCompatTextView title;
		@BindView(R.id.descripcion) AppCompatTextView descripcion;

		public ViewHolder(View itemView, Context mContext) {
			super(itemView);
			this.mContext = mContext;
			ButterKnife.bind(this, itemView);
		}

		void setup(final Marker mMarker) {

			if (AnteriorMarker != mMarker) {
				descripcion.setVisibility(View.GONE);
				descripcion.setTextColor(ContextCompat.getColor(mContext, R.color.secondary_text));
				title.setTextColor(ContextCompat.getColor(mContext, R.color.secondary_text));
			} else {
				descripcion.setVisibility(View.VISIBLE);
				descripcion.setTextColor(ContextCompat.getColor(mContext, R.color.primary_text));
				title.setTextColor(ContextCompat.getColor(mContext, R.color.primary_text));
			}

			title.setText(mMarker.getTitle());
			descripcion.setText(mMarker.getDescription());
			title.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {

					onClickAdapter(mMarker);

				}
			});

			descripcion.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					onClickAdapter(mMarker);
				}
			});

			icon.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					onClickAdapter(mMarker);
				}
			});

			((ViewGroup) title.getParent()).setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					onClickAdapter(mMarker);
				}
			});

		}

		void onClickAdapter(Marker marker) {
			AnteriorMarker = marker;
			mView.onClickMarkerAdapter(marker);
			notifyDataSetChanged();
		}

	}


}
