package com.kumar.ranjan.mobilephone.data.entity.mapper;

import com.kumar.ranjan.mobilephone.data.entity.ImageEntity;
import com.kumar.ranjan.mobilephone.data.entity.PhoneEntity;
import com.kumar.ranjan.mobilephone.domain.Phone;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import java.util.List;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class JsonMapperTest {

    private static String IMAGE_LIST_JSON = "[{\"url\":\"https://www.91-img" +
                                            ".com/gallery_images_uploads/f/c/fc3fba717874d64cf15d30e77a16617a1e63cc0b.jpg\",\"id\":1," +
                                            "\"mobile_id\":1},{\"url\":\"https://www.91-img" +
                                            ".com/gallery_images_uploads/b/4/b493185e7767c2a99cfeef712b11377f625766f2.jpg\",\"id\":6," +
                                            "\"mobile_id\":1},{\"url\":\"https://www.91-img" +
                                            ".com/gallery_images_uploads/c/3/c32cff8945621ad06c929f50af9f7c55f978c726.jpg\",\"id\":7," +
                                            "\"mobile_id\":1}]";

    private static String PHONE_LIST_JSON = "[{\"thumbImageURL\":\"https://cdn.mos.cms.futurecdn.net/grwJkAGWQp4EPpWA3ys3YC-650-80.jpg\"," +
                                            "\"name\":\"Moto E4 Plus\",\"brand\":\"Samsung\",\"price\":179.99,\"rating\":4.9,\"id\":1," +
                                            "\"description\":\"First place in our list goes to the excellent Moto E4 Plus. It's a cheap " +
                                            "phone that features phenomenal battery life, a fingerprint scanner and a premium feel " +
                                            "design, plus it's a lot cheaper than the Moto G5 below. It is a little limited with its " +
                                            "power, but it makes up for it by being able to last for a whole two days from a single " +
                                            "charge. If price and battery are the most important features for you, the Moto E4 Plus will " +
                                            "suit you perfectly.\"},{\"thumbImageURL\":\"https://cdn.mos.cms.futurecdn" +
                                            ".net/8LWUERoxMAWavvVAAbxuac-650-80.jpg\",\"name\":\"Nokia 6\",\"brand\":\"Nokia\"," +
                                            "\"price\":199.99,\"rating\":4.6,\"id\":2,\"description\":\"Nokia is back in the mobile phone" +
                                            " game and after a small price drop to the Nokia 6 we've now seen it enter our best cheap " +
                                            "phone list. It comes with a Full HD 5.5-inch display, full metal design and a fingerprint " +
                                            "scanner for added security. The battery isn't incredible on the Nokia 6, but it's not awful " +
                                            "either making this one of our favorite affordable phones on the market right now.\"}," +
                                            "{\"thumbImageURL\":\"https://cdn.mos.cms.futurecdn.net/52JJgbgWiGftNHV5UmMDfS-650-80.jpg\"," +
                                            "\"name\":\"Moto G4 Plus\",\"brand\":\"Motorola\",\"price\":179,\"rating\":4.7,\"id\":3," +
                                            "\"description\":\"The spec for the G4 Plus is much the same as it was on the Moto G4, but it" +
                                            " also comes with a fingerprint scanner and an improved camera. The 16MP rear shooter is " +
                                            "arguably one of the most impressive phone cameras at the sub-£200 mark. There's no NFC so " +
                                            "you won't be able to use Android Pay on the Moto G4 Plus, but a bright display and strong " +
                                            "performance is sure to make up for it.\"},{\"thumbImageURL\":\"https://cdn.mos.cms.futurecdn" +
                                            ".net/DcUtY6YfhoajHnoKUgGFqn-650-80.jpg\",\"name\":\"Moto G5\",\"brand\":\"Motorola\"," +
                                            "\"price\":165,\"rating\":3.8,\"id\":4,\"description\":\"Motorola's Moto G5, a former best " +
                                            "cheap phone in the world, has slipped a few places thanks to better priced alternatives, " +
                                            "plus the introduction of the new G5S. The Moto G5 comes with a metal design, 1080p display " +
                                            "and fingerprint scanner. You won't get the fastest chipset on this list or NFC with the Moto" +
                                            " G5, but as an all-round product the cheap Motorola phone will serve you well.\"}," +
                                            "{\"thumbImageURL\":\"https://cdn.mos.cms.futurecdn.net/7dUFmtHkmH7La9dFzew7Ri-650-80.jpg\"," +
                                            "\"name\":\"Sony Xperia L1\",\"brand\":\"Motorola\",\"price\":171.99,\"rating\":4.3,\"id\":5," +
                                            "\"description\":\"Currently the only Sony handset to take a position in our best cheap phone" +
                                            " list, the Xperia L1 is a low priced handset that does okay but won't blow your socks off. " +
                                            "Other phones in this list are far more impressive as this doesn't have a fingerprint scanner" +
                                            " or stunning camera. That said, the price is low and if you really, really like the design " +
                                            "of Sony handsets everything here could add up to being your best choice for a cheap phone. " +
                                            "We particularly liked the 5.5-inch display - despite its 720p resolution - and the " +
                                            "expandable storage too, which allows you to use microSD cards up to 256GB. \"}]";


    private JsonMapper jsonMapper;

    @Before
    public void setUp() {
        jsonMapper = new JsonMapper();
    }

    @Test
    public void testTransformPhoneEntityList() {
        List<PhoneEntity> phoneEntityList = jsonMapper.transformPhoneEntityList(PHONE_LIST_JSON);

        assertThat(phoneEntityList, is(notNullValue()));
        assertThat(phoneEntityList.isEmpty(), is(false));
        assertThat(phoneEntityList.size(), is(5));
        assertThat(phoneEntityList.get(0), is(instanceOf(PhoneEntity.class)));
    }

    @Test
    public void testTransformImageEntityList() {
        List<ImageEntity> imageEntityList = jsonMapper.transformImageEntityList(IMAGE_LIST_JSON);

        assertThat(imageEntityList, is(notNullValue()));
        assertThat(imageEntityList.isEmpty(), is(false));
        assertThat(imageEntityList.size(), is(3));
        assertThat(imageEntityList.get(0), is(instanceOf(ImageEntity.class)));
    }

    @Test
    public void testTransformPhoneList() {
        List<Phone> phoneList = jsonMapper.transformPhoneList(PHONE_LIST_JSON);

        assertThat(phoneList, is(notNullValue()));
        assertThat(phoneList.isEmpty(), is(false));
        assertThat(phoneList.size(), is(5));
        assertThat(phoneList.get(0), is(instanceOf(Phone.class)));
    }

    @Test
    public void testTransformPhoneListToJson() {
        String expectedJson = "[{\"id\":1,\"name\":\"Moto E4 Plus\",\"brand\":\"Samsung\",\"description\":\"First place in our list goes " +
                              "to the excellent Moto E4 Plus. It\\u0027s a cheap phone that features phenomenal battery life, a " +
                              "fingerprint scanner and a premium feel design, plus it\\u0027s a lot cheaper than the Moto G5 below. It is" +
                              " a little limited with its power, but it makes up for it by being able to last for a whole two days from a" +
                              " single charge. If price and battery are the most important features for you, the Moto E4 Plus will suit " +
                              "you perfectly.\",\"thumbImageURL\":\"https://cdn.mos.cms.futurecdn" +
                              ".net/grwJkAGWQp4EPpWA3ys3YC-650-80.jpg\",\"price\":179.99,\"rating\":4.9,\"isFavorite\":false},{\"id\":2," +
                              "\"name\":\"Nokia 6\",\"brand\":\"Nokia\",\"description\":\"Nokia is back in the mobile phone game and " +
                              "after a small price drop to the Nokia 6 we\\u0027ve now seen it enter our best cheap phone list. It comes " +
                              "with a Full HD 5.5-inch display, full metal design and a fingerprint scanner for added security. The " +
                              "battery isn\\u0027t incredible on the Nokia 6, but it\\u0027s not awful either making this one of our " +
                              "favorite affordable phones on the market right now.\",\"thumbImageURL\":\"https://cdn.mos.cms.futurecdn" +
                              ".net/8LWUERoxMAWavvVAAbxuac-650-80.jpg\",\"price\":199.99,\"rating\":4.6,\"isFavorite\":false},{\"id\":3," +
                              "\"name\":\"Moto G4 Plus\",\"brand\":\"Motorola\",\"description\":\"The spec for the G4 Plus is much the " +
                              "same as it was on the Moto G4, but it also comes with a fingerprint scanner and an improved camera. The " +
                              "16MP rear shooter is arguably one of the most impressive phone cameras at the sub-£200 mark. There\\u0027s" +
                              " no NFC so you won\\u0027t be able to use Android Pay on the Moto G4 Plus, but a bright display and strong" +
                              " performance is sure to make up for it.\",\"thumbImageURL\":\"https://cdn.mos.cms.futurecdn" +
                              ".net/52JJgbgWiGftNHV5UmMDfS-650-80.jpg\",\"price\":179.0,\"rating\":4.7,\"isFavorite\":false},{\"id\":4," +
                              "\"name\":\"Moto G5\",\"brand\":\"Motorola\",\"description\":\"Motorola\\u0027s Moto G5, a former best " +
                              "cheap phone in the world, has slipped a few places thanks to better priced alternatives, plus the " +
                              "introduction of the new G5S. The Moto G5 comes with a metal design, 1080p display and fingerprint scanner." +
                              " You won\\u0027t get the fastest chipset on this list or NFC with the Moto G5, but as an all-round product" +
                              " the cheap Motorola phone will serve you well.\",\"thumbImageURL\":\"https://cdn.mos.cms.futurecdn" +
                              ".net/DcUtY6YfhoajHnoKUgGFqn-650-80.jpg\",\"price\":165.0,\"rating\":3.8,\"isFavorite\":false},{\"id\":5," +
                              "\"name\":\"Sony Xperia L1\",\"brand\":\"Motorola\",\"description\":\"Currently the only Sony handset to " +
                              "take a position in our best cheap phone list, the Xperia L1 is a low priced handset that does okay but " +
                              "won\\u0027t blow your socks off. Other phones in this list are far more impressive as this doesn\\u0027t " +
                              "have a fingerprint scanner or stunning camera. That said, the price is low and if you really, really like " +
                              "the design of Sony handsets everything here could add up to being your best choice for a cheap phone. We " +
                              "particularly liked the 5.5-inch display - despite its 720p resolution - and the expandable storage too, " +
                              "which allows you to use microSD cards up to 256GB. \",\"thumbImageURL\":\"https://cdn.mos.cms.futurecdn" +
                              ".net/7dUFmtHkmH7La9dFzew7Ri-650-80.jpg\",\"price\":171.99,\"rating\":4.3,\"isFavorite\":false}]";

         List<Phone> phoneList = jsonMapper.transformPhoneList(PHONE_LIST_JSON);

        String phoneListJson = jsonMapper.transformPhoneListToJson(phoneList);

        assertThat(phoneListJson, is(notNullValue()));
        assertThat(phoneListJson, is(expectedJson));
    }

}