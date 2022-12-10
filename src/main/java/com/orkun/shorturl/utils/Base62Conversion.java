package com.orkun.shorturl.utils;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class Base62Conversion {
    private static final String baseString
            = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private final char[] baseChars = baseString.toCharArray();
    private final int base = baseChars.length;

    public long decode(String input){
        var chars = input.toCharArray();
        var length = chars.length;
        var decoded = 0;
        // counter is used to avoid reversing input string
        var counter = 1;
        for (int i = 0; i < length; i++) {
            decoded += baseString.indexOf(chars[i]) * Math.pow(base, length - counter);
            counter++;
        }
        return decoded;
    }

    public String encode(long input){
        var encodedString = new StringBuilder();
        if(input == 0)
            return String.valueOf(baseChars[0]);
        System.out.println("value inside encode functionn is=="+input);
        while(input>0){
            encodedString.append(baseChars[(int)(input % base)]);
            input = input / base;
        }
        return encodedString.reverse().toString();
    }
}
