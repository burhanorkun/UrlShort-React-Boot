package com.orkun.shorturl.controllers;

import com.orkun.shorturl.dtos.ShortUrlRequest;
import com.orkun.shorturl.services.ShortenerService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/api/v1")
public class UrlShortenerController {

    // todo: create url service for logic
    private final ShortenerService urlService;


    @GetMapping("/")
    public String baseMessage(){
        return "This is URL shortener service";
    }

    @ApiOperation(value="get shorten URL from database", notes ="Method retrieves long url from url database")
    @GetMapping("/url")
    @ResponseBody
    public String getOriginalUrl(@RequestParam String url){
        return urlService.getOriginalUrl(url);
    }

    @ApiOperation(value="Create shorten URL", notes ="Method converts long url to short url")
    @PostMapping("/url")
    @ResponseBody
    public String createShortUrl(@RequestBody ShortUrlRequest request){  //@RequestBody ShortUrlRequest request
        log.info("Original URL is {}", request.getOriginalUrl());
        try {
            return urlService.createShortUrl(request.getOriginalUrl());
        } catch (Exception e) {
            return "Not found URL";
        }
    }
}
