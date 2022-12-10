package com.orkun.shorturl.services;

import com.orkun.shorturl.enums.ActionEnum;
import com.orkun.shorturl.strategies.ShortenerContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ShortenerService {

    private final ShortenerContext shortenerContext;

    public String getOriginalUrl(String shortUrl) {
        return shortenerContext.shortToLong(shortUrl, ActionEnum.URL);
    }

    public String createShortUrl(String originalUrl) throws Exception {
        return shortenerContext.longToShort(originalUrl, ActionEnum.URL);
    }

    public String getOriginalQr(String shortUrl) {
        return shortenerContext.shortToLong(shortUrl, ActionEnum.QRCODE);
    }

    public String createShortQr(String originalUrl) throws Exception{
        return shortenerContext.longToShort(originalUrl, ActionEnum.QRCODE);
    }

    public String getOriginalBarCode(String shortUrl) {
        return shortenerContext.shortToLong(shortUrl, ActionEnum.BARCODE);
    }

    public String createShortBarCode(String originalUrl) throws Exception{
        return shortenerContext.longToShort(originalUrl, ActionEnum.BARCODE);
    }
}
