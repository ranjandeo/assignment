package com.kumar.ranjan.mobilephone.mapper;

import com.google.common.collect.Lists;

import com.kumar.ranjan.mobilephone.domain.Image;
import com.kumar.ranjan.mobilephone.model.ImageDataModel;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ImageDataModelMapperTest {

    private ImageDataModelMapper imageDataModelMapper;

    @Before
    public void setUp() {
        imageDataModelMapper = new ImageDataModelMapper();
    }

    @Test
    public void transformObject() {
        // Arrange
        Image image = getMockImage(1, 1, "url");

        // Act
        ImageDataModel imageDataModel = imageDataModelMapper.transform(image);

        // Assert
        assertThat(imageDataModel, is(notNullValue()));
        assertThat(imageDataModel.getId(), is(1));
        assertThat(imageDataModel.getMobileId(), is(1));
        assertThat(imageDataModel.getUrl(), is("url"));
    }

    @Test
    public void transformList() {
        // Arrange
        List<Image> imageList = Lists.newArrayList();
        imageList.add(getMockImage(1, 1, "url"));
        imageList.add(getMockImage(2, 1, "url1"));

        // Act
        List<ImageDataModel> imageDataModels = imageDataModelMapper.transform(imageList);

        // Assert
        assertThat(imageDataModels, is(notNullValue()));
        assertThat(imageDataModels.isEmpty(), is(false));
        assertThat(imageDataModels.size(), is(2));
        assertThat(imageDataModels.get(0), is(CoreMatchers.<ImageDataModel>instanceOf(ImageDataModel.class)));

    }

    private Image getMockImage(int id, int mobileId, String url) {
        Image image = new Image();

        image.setId(id);
        image.setMobileId(mobileId);
        image.setUrl(url);

        return image;
    }
}