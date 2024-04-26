package com.dudgns.purchase.goods.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ResponseGoodsInfoDto {
    private String goodsGuid;
    private Integer amount;
    private Integer price;
    private String company;
    private String description;
    private Boolean sale;
}
