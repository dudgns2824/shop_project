package com.dudgns.purchase.common.object;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class SendEmailObjectDto {
    private String from;
    private String to;
    private String code;
}
