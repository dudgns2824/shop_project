package com.dudgns.auth.common.object;

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
}
