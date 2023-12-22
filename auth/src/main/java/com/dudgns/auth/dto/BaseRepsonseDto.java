package com.dudgns.auth.dto;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class BaseRepsonseDto{
    private Integer statusCode;
    private String status;
    private Object data;
}
