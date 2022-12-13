package com.orkun.shorturl.utils;

import org.springframework.stereotype.Component;

import java.net.URL;

@Component
public class ValidationUtil {

    public boolean isValidUrl(String url){
        try{
            new URL(url).toURI();
            return true;
        } catch (Exception e) {
            System.out.println("Not a valid URL = " + url);
            System.out.println("Exception: " + e.getMessage());
            return false;
        }
    }

    public String conventionControl(String url){
        if(url.trim().startsWith("http://") || url.trim().startsWith("https://")) return url;
        return "http://" + url.trim();
    }
}
