package com.fastcampus.clientserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Req<T> {

    private Header header;
    private T resBody;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Header{
        private String responseCode;
    }
}
