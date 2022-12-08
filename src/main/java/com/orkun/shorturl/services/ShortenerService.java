package com.orkun.shorturl.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ShortenerService {

    // todo: repository
    public String getOriginalUrl(String shortUrl) {
        // todo: retrieve original url from repository
        return null;
    }

    public void createShortUrl(String originalUrl) {
        // todo: create short url
    }
}
