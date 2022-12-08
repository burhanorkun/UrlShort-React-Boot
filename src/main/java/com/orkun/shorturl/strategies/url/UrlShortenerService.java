package com.orkun.shorturl.strategies.url;

import com.orkun.shorturl.utils.Base62Conversion;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URL;

@Service
@AllArgsConstructor
public class UrlShortenerService {

    private final Base62Conversion conversion;

    public String longToShort(String request){

        return "";
    }

    public static boolean isValidUrl(String url){
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
