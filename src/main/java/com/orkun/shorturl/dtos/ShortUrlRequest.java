package com.orkun.shorturl.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@Accessors(chain = true)
public class ShortUrlRequest {
    private String salt;

    private String url;
}
