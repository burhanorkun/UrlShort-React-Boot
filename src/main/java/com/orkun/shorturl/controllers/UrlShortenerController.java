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

    @GetMapping("/{shortUrl}")
    public String getOriginalUrl(@PathVariable String shortUrl){
        // todo: retrieve from service
        return urlService.getOriginalUrl(shortUrl);
    }

    @ApiOperation(value="Create shorten URL", notes ="Method converts long url to short url")
    @PostMapping("/url")
    //@ResponseBody
    public void createShortUrl(@RequestBody ShortUrlRequest request){
        //log.info("Original URL is {}", request.getOriginalUrl());
        // todo: add service to create shorturl
        urlService.createShortUrl(request.getOriginalUrl());
    }
}
