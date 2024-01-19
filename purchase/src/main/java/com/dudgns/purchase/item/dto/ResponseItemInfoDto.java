package com.dudgns.purchase.item.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ResponseItemInfoDto {
    private String itemGuid;
    private Integer amount;
    private Long price;
    private String company;
    private String description;
    private Boolean sale;
}
