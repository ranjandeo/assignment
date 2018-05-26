package com.kumar.ranjan.mobilephone.data.entity.mapper;

import com.fernandocejas.arrow.collections.Lists;
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
        Image image = new Image();

        if (imageEntity != null) {
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
                imageList.add(transform(imageEntity));
            }
        }

        return imageList;
    }
}
