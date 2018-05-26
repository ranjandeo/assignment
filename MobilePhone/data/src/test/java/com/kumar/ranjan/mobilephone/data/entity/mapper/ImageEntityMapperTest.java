package com.kumar.ranjan.mobilephone.data.entity.mapper;

import com.kumar.ranjan.mobilephone.data.entity.ImageEntity;
import com.kumar.ranjan.mobilephone.domain.Image;

import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class ImageEntityMapperTest {

    ImageEntityMapper imageEntityMapper;

    @Before
    public void setUp() {
        imageEntityMapper = new ImageEntityMapper();
    }

    @Test
    public void testTransformObject() {
        // Arrange
        ImageEntity imageEntity = mock(ImageEntity.class);
        when(imageEntity.getId()).thenReturn(1);
        when(imageEntity.getMobileId()).thenReturn(1);
        when(imageEntity.getUrl()).thenReturn("http://dummyurl.com");

        // Act
        Image image = imageEntityMapper.transform(imageEntity);

        // Assert
        assertThat(image, is(notNullValue()));
        assertThat(image.getId(), is(1));
        assertThat(image.getMobileId(), is(1));
        assertThat(image.getUrl(), is(notNullValue()));
        assertThat(image.getUrl(), is("http://dummyurl.com"));
    }

    @Test
    public void testTransformList() {
        // Arrange
        ImageEntity imageEntity1 = mock(ImageEntity.class);
        when(imageEntity1.getId()).thenReturn(1);
        when(imageEntity1.getMobileId()).thenReturn(1);
        when(imageEntity1.getUrl()).thenReturn("http://dummyurl_1.com");

        ImageEntity imageEntity2 = mock(ImageEntity.class);
        when(imageEntity2.getId()).thenReturn(2);
        when(imageEntity2.getMobileId()).thenReturn(1);
        when(imageEntity2.getUrl()).thenReturn("http://dummyurl_2.com");

        List<ImageEntity> imageEntityList = Lists.newArrayList();
        imageEntityList.add(imageEntity1);
        imageEntityList.add(imageEntity2);

        // Act
        List<Image> imageList = imageEntityMapper.transform(imageEntityList);

        // Assert
        assertThat(imageList, is(notNullValue()));
        assertThat(imageList.isEmpty(), is(false));
        assertThat(imageList.size(), is(2));

        assertThat(imageList.get(0), is(instanceOf(Image.class)));
        assertThat(imageList.get(0).getId(), is(1));
        assertThat(imageList.get(0).getMobileId(), is(1));
        assertThat(imageList.get(0).getUrl(), is(notNullValue()));
        assertThat(imageList.get(0).getUrl(), is("http://dummyurl_1.com"));

        assertThat(imageList.get(1), is(instanceOf(Image.class)));
        assertThat(imageList.get(1).getId(), is(2));
        assertThat(imageList.get(1).getMobileId(), is(1));
        assertThat(imageList.get(1).getUrl(), is(notNullValue()));
        assertThat(imageList.get(1).getUrl(), is("http://dummyurl_2.com"));
    }

}