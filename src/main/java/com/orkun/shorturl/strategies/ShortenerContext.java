package com.orkun.shorturl.strategies;

import com.orkun.shorturl.enums.ActionEnum;
import com.orkun.shorturl.models.DataRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShortenerContext {
    @Autowired
    private final List<Shortener> converters = new ArrayList<>();

    public String shortToLong(String url, ActionEnum action){
        if(url == null || "".equals(url)) return "";
        return converters.stream()
                .filter(c-> c.getAction() == action)
                .map(c->c.shortToLong(url))
                .toList().get(0);
    }

    public String longToShort(String url, ActionEnum action){
        if(url == null || "".equals(url))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Url");

        return converters.stream()
                .filter(c -> c.getAction().equals(action))
                .map(c -> c.longToShort(url))
                .toList().get(0);
    }

    public List<DataRecord> getAllRecords(ActionEnum action){
        return converters.stream()
                .filter(c -> c.getAction().equals(action))
                .map(Shortener::getAllRecords)
                .toList().get(0);
    }
}
