package com.kumar.ranjan.mobilephone.mapper;

import com.google.common.collect.Lists;

import com.kumar.ranjan.mobilephone.di.PerActivity;
import com.kumar.ranjan.mobilephone.domain.Image;
import com.kumar.ranjan.mobilephone.model.ImageDataModel;

import java.util.List;

import javax.inject.Inject;

@PerActivity
public class ImageDataModelMapper {

    @Inject
    public ImageDataModelMapper() { }

    /**
     * Transform Image object to ImageDataModel object
     * @param image
     * @return
     */
    public ImageDataModel transform(Image image) {
        ImageDataModel imageDataModel = null;

        if (image != null) {
            imageDataModel = new ImageDataModel();
            imageDataModel.setId(image.getId());
            imageDataModel.setMobileId(image.getMobileId());
            imageDataModel.setUrl(image.getUrl());
        }

        return imageDataModel;
    }

    /**
     * Transform the list of Image objects to list of ImageDataModel objects
     * @param imageList
     * @return
     */
    public List<ImageDataModel> transform(List<Image> imageList) {
        List<ImageDataModel> imageDataModelList = Lists.newArrayList();

        if (imageList != null && !imageList.isEmpty()) {
            for (Image image : imageList) {
                ImageDataModel imageDataModel = transform(image);
                if (imageDataModel != null) {
                    imageDataModelList.add(imageDataModel);
                }
            }
        }

        return imageDataModelList;
    }
}
