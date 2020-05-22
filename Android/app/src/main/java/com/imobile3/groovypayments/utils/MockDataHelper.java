package com.imobile3.groovypayments.utils;

import com.imobile3.groovypayments.data.entities.CartEntity;
import com.imobile3.groovypayments.data.entities.CartProductEntity;
import com.imobile3.groovypayments.data.entities.ProductEntity;
import com.imobile3.groovypayments.data.entities.ProductTaxJunctionEntity;
import com.imobile3.groovypayments.data.entities.TaxEntity;
import com.imobile3.groovypayments.data.entities.UserEntity;
import com.imobile3.groovypayments.data.enums.GroovyColor;
import com.imobile3.groovypayments.data.enums.GroovyIcon;
import com.imobile3.groovypayments.data.utils.CartBuilder;
import com.imobile3.groovypayments.data.utils.CartProductBuilder;
import com.imobile3.groovypayments.data.utils.ProductBuilder;
import com.imobile3.groovypayments.data.utils.TaxBuilder;
import com.imobile3.groovypayments.data.utils.UserBuilder;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MockDataHelper {

    private static MockDataHelper instance = null;

    private MockDataHelper() {
    }

    public static MockDataHelper getInstance() {
        if (instance == null) {
            synchronized (MockDataHelper.class) {
                if (instance == null)
                    instance = new MockDataHelper();

            }
        }
        return instance;
    }

    public List<ProductEntity> getMockProducts() {
        return Arrays.asList(
                ProductBuilder.build(101L,
                        "La Crosse Alarm Clocks",
                        "White Curved Alarm Clock with Mirrored LED Lens Display",
                        750L, 200L,
                        GroovyIcon.AlarmClock, GroovyColor.Red),

                ProductBuilder.build(102L,
                        "Grab Bag",
                        "Our grab bags consist of randomly selected coins from our extensive inventory",
                        2000L, 200L,
                        GroovyIcon.BagCoins, GroovyColor.Green)

        );
    }

    public List<UserEntity> getMockUserEntity() {
        return Arrays.asList(
                UserBuilder.build(
                        1,
                        "Nader",
                        "Ahmed",
                        "nahmed",
                        "naderwashereagain@gmail.com",
                        "Take@#$me2Kansas"),
                UserBuilder.build(
                        2,
                        "Mike",
                        "Tyson",
                        "mtyson",
                        "mike@tyson.com",
                        "Power#hit6"));
    }

    public List<CartProductEntity> getMockCartProductEntity() {
        return Arrays.asList(
                CartProductBuilder.build(1, 1, "Cart110",
                        1, 1),
                CartProductBuilder.build(2, 2, "Cart112",
                        2, 2));
    }

    public List<CartEntity> getMockCartEntity() {
        return Arrays.asList(
                CartBuilder.build(1, new Date()),
                CartBuilder.build(2, new Date(), 10, 1, 11));
    }

    public List<TaxEntity> getMockTaxEntity() {
        return Arrays.asList(
                TaxBuilder.build(101L,
                        "Tax Entity 1",
                        "0.02"),
                TaxBuilder.build(102L,
                        "Tax Entity 2",
                        "0.01"));
    }

    public List<ProductTaxJunctionEntity> getMockProductTaxJunctionEntity() {
        ProductTaxJunctionEntity productTaxJunctionEntity = new ProductTaxJunctionEntity();
        productTaxJunctionEntity.setProductId(101L);
        productTaxJunctionEntity.setTaxId(101L);
        return Arrays.asList(productTaxJunctionEntity);

    }
}
