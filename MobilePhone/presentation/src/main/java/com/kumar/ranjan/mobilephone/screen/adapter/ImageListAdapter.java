package com.kumar.ranjan.mobilephone.screen.adapter;

import com.google.common.collect.Lists;

import com.bumptech.glide.Glide;
import com.kumar.ranjan.mobilephone.R;
import com.kumar.ranjan.mobilephone.model.ImageDataModel;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.ImageViewHolder> {

    private Context context;

    private List<ImageDataModel> imageList;
    private final LayoutInflater layoutInflater;

    @Inject
    public ImageListAdapter(Context context) {
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageList = Lists.newArrayList();
    }

    @Override
    public int getItemCount() {
        return imageList != null ? imageList.size() : 0;
    }

    @Override
    public ImageListAdapter.ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = layoutInflater.inflate(R.layout.phone_details_image_item, parent, false);
        return new ImageListAdapter.ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ImageListAdapter.ImageViewHolder holder, final int position) {
        final ImageDataModel imageDataModel = imageList.get(position);

        Glide.with(context).load(imageDataModel.getUrl()).into(holder.imageView);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setImageDataModelList(List<ImageDataModel> imageList) {
        if (imageList != null) {
            this.imageList.clear();
            this.imageList.addAll(imageList);
            notifyDataSetChanged();
        }
    }

    static class ImageViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_view_phone_details)
        ImageView imageView;

        ImageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
