package com.kumar.ranjan.mobilephone.data.entity.mapper;

import com.google.common.collect.Lists;

import com.kumar.ranjan.mobilephone.data.entity.ImageEntity;
import com.kumar.ranjan.mobilephone.domain.Image;

import java.util.List;

import javax.inject.Inject;

/**
 * Mapper class to transform ImageEntity into Image
 */
public class ImageEntityMapper {

    @Inject
    public ImageEntityMapper() {

    }

    /**
     * Trasform an object of ImageEnity into Image
     * @param imageEntity
     * @return Image
     */
    public Image transform(ImageEntity imageEntity) {
        Image image = null;

        if (imageEntity != null) {
            image = new Image();
            image.setId(imageEntity.getId());
            image.setMobileId(imageEntity.getMobileId());
            image.setUrl(imageEntity.getUrl());
        }
        return image;
    }

    /**
     * Transform the list of ImageEntity into list of Image
     * @param imageEntities
     * @return List of Image objects
     */
    public List<Image> transform(List<ImageEntity> imageEntities) {
        List<Image> imageList = Lists.newArrayList();

        if (imageEntities != null && !imageEntities.isEmpty()) {
            for (ImageEntity imageEntity : imageEntities) {
                Image image = transform(imageEntity);
                if (image != null) {
                    imageList.add(image);
                }
            }
        }

        return imageList;
    }
}
