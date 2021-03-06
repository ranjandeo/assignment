package com.kumar.ranjan.mobilephone.screen.adapter;

import com.google.common.collect.Lists;

import com.bumptech.glide.Glide;
import com.kumar.ranjan.mobilephone.R;
import com.kumar.ranjan.mobilephone.model.PhoneDataModel;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhoneListAdapter extends RecyclerView.Adapter<PhoneListAdapter.PhoneViewHolder> {

    private Context context;

    public interface OnItemClickListener {
        void onPhoneItemClicked(PhoneDataModel phoneDataModel);
        void onFavoriteButtonClicked(PhoneDataModel phoneDataModel);
    }

    private List<PhoneDataModel> phoneList;
    private final LayoutInflater layoutInflater;

    private OnItemClickListener onItemClickListener;

    @Inject
    public PhoneListAdapter(Context context) {
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        phoneList = Lists.newArrayList();
    }

    @Override
    public int getItemCount() {
        return phoneList != null ? phoneList.size() : 0;
    }

    @Override
    public PhoneViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = layoutInflater.inflate(R.layout.phone_row_view, parent, false);
        return new PhoneViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PhoneViewHolder holder, final int position) {
        final PhoneDataModel phoneDataModel = phoneList.get(position);

        holder.textViewPhoneName.setText(phoneDataModel.getName());
        holder.textViewDescription.setText(phoneDataModel.getDescription());
        holder.textViewPrice.setText(getFormattedPrice(phoneDataModel.getPrice()));
        holder.textViewRating.setText(getFormattedRating(phoneDataModel.getRating()));

        int favImageResource = phoneDataModel.isFavorite() ? R.drawable.ic_favorite_fill : R.drawable.ic_favorite_grey;
        holder.imageViewFavorite.setImageResource(favImageResource);

        Glide.with(context).load(phoneDataModel.getThumbImageURL()).into(holder.imageViewThumbnail);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onPhoneItemClicked(phoneDataModel);
                }
            }
        });

        holder.imageViewFavorite.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                if (onItemClickListener != null) {
                    phoneDataModel.setFavorite(!phoneDataModel.isFavorite());
                    onItemClickListener.onFavoriteButtonClicked(phoneDataModel);
                    notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setPhoneDataModelList(List<PhoneDataModel> phoneList) {
        if (phoneList != null) {
            this.phoneList.clear();
            this.phoneList.addAll(phoneList);
            notifyDataSetChanged();
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    static class PhoneViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.thumbnail)
        ImageView imageViewThumbnail;

        @BindView(R.id.phone_name)
        TextView textViewPhoneName;

        @BindView(R.id.image_fav_view)
        ImageView imageViewFavorite;

        @BindView(R.id.description)
        TextView textViewDescription;

        @BindView(R.id.price)
        TextView textViewPrice;

        @BindView(R.id.rating)
        TextView textViewRating;

        PhoneViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private String getFormattedPrice(double price) {
        String strPrice = String.format(Locale.ENGLISH, "%.2f", price);
        return context.getResources().getString(R.string.price_text, strPrice);
    }

    private String getFormattedRating(double rating) {
        String strrating = String.format(Locale.ENGLISH, "%.1f", rating);
        return context.getResources().getString(R.string.rating_text, strrating);
    }
}
