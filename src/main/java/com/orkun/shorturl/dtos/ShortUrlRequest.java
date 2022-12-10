package com.orkun.shorturl.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Builder
@Accessors(chain = true)
public class ShortUrlRequest {
    private String salt;
    private String originalUrl;
}
