package com.orkun.shorturl.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Builder
public class ShortUrlRequest {
    private String shortUrl;
    private String originalUrl;
}
