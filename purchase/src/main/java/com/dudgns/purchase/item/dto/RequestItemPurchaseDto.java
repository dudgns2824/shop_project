package com.dudgns.purchase.item.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class RequestItemPurchaseDto {
    private String itemGuid;
    private String userGuid;
}
