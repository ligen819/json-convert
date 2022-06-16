package com.lig.convert.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @className: com.lig.convert.vo-> Order
 * @description:
 * @author: lig
 * @createDate: 2022-06-16 20:28
 * @version: 1.0
 * @todo:
 */
@Data
public class Order {

    @JsonProperty(value = "amount")
    private BigDecimal goodsAmount;


    private Address address;

    @Data
    private static class Address {

        @JsonProperty(value = "province")
        private String provinceName;


    }

    @JsonProperty(value = "goods")
    private List<Goods> goodsList;


    @Data
    private static class Goods {

        private String name;

        private Integer qty;

    }



}
