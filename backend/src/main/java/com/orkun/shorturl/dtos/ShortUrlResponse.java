package com.orkun.shorturl.dtos;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class ShortUrlResponse {
    private String url;
    private String longUrl;
}
