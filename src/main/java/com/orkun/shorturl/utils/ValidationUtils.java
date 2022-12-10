package com.orkun.shorturl.utils;

import org.springframework.stereotype.Component;

import java.net.URL;

@Component
public class ValidationUtils {

    public boolean isValidUrl(String url){
        try{
            if(url.length() > 355){
                System.out.println("URL length not acceptable. url=" + url);
                return false;
            }else{
                new URL(url).toURI();
                return true;
            }
        } catch (Exception e) {
            System.out.println("Not a valid URL = " + url);
            System.out.println("Exception: " + e.getMessage());
            return false;
        }
    }
}
