package com.orkun.shorturl.strategies;

import com.orkun.shorturl.enums.ActionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShortenerContext {
    @Autowired
    private final List<Shortener> converters = new ArrayList<>();

    public String shortToLong(String url, ActionEnum action){

        return converters.stream()
                .filter(c-> c.getAction() == action)
                .map(c->c.shortToLong(url))
                .toList().get(0);
                //.orElseThrow (Exception::new);
    }

    public String longToShort(String url, ActionEnum action) throws Exception{
        return converters.stream()
                .filter(c -> c.getAction() == action)
                .map(c -> {
                    try {
                        return c.longToShort(url);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .toList().get(0);
                //.orElseThrow (Exception::new);
    }

    /*
    @PostConstruct
    public void init(){
        System.out.println("test");
        System.out.println(converters);
    }

    public ShortenerContext(){
        System.out.println("containers test");
        System.out.println(converters);
    } */
}
