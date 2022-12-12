package com.orkun.shorturl.services;

import com.orkun.shorturl.dtos.DataRecords;
import com.orkun.shorturl.enums.ActionEnum;
import com.orkun.shorturl.models.DataRecord;
import com.orkun.shorturl.strategies.ShortenerContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ShortenerService {

    private final ShortenerContext shortenerContext;

    public String getOriginalUrl(String shortUrl) {
        return shortenerContext.shortToLong(shortUrl, ActionEnum.URL);
    }

    public String createShortUrl(String originalUrl) {
        return shortenerContext.longToShort(originalUrl, ActionEnum.URL);
    }

    public DataRecords getAllUrlRecords(){
        DataRecords urlRecords = new DataRecords();
        List<DataRecord> allRecords = shortenerContext.getAllRecords(ActionEnum.URL);
        urlRecords.setAllUrlRecords(allRecords);

        return urlRecords;
    }

    public String getOriginalQr(String shortUrl) {
        return shortenerContext.shortToLong(shortUrl, ActionEnum.QRCODE);
    }

    public String createShortQr(String originalUrl){
        return shortenerContext.longToShort(originalUrl, ActionEnum.QRCODE);
    }

    public String getOriginalBarCode(String shortUrl) {
        return shortenerContext.shortToLong(shortUrl, ActionEnum.BARCODE);
    }

    public String createShortBarCode(String originalUrl){
        return shortenerContext.longToShort(originalUrl, ActionEnum.BARCODE);
    }
}
