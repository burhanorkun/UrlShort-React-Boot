package com.orkun.shorturl.controllers;

import com.orkun.shorturl.dtos.DataRecords;
import com.orkun.shorturl.dtos.LongUrlResponse;
import com.orkun.shorturl.dtos.ShortUrlRequest;
import com.orkun.shorturl.dtos.ShortUrlResponse;
import com.orkun.shorturl.services.ShortenerService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    public LongUrlResponse getOriginalUrl(@RequestParam String link){
        var longUrlResponse = new LongUrlResponse();
        longUrlResponse.setUrl(urlService.getOriginalUrl(link));
        return longUrlResponse;
    }

    @ApiOperation(value="Create shorten URL", notes ="Method converts long url to short url")
    @PostMapping("/url")
    @ResponseBody
    public ShortUrlResponse createShortUrl(@Valid @RequestBody ShortUrlRequest request){
        log.info("Original URL is {}", request.getUrl());
        var shortUrlResponse = new ShortUrlResponse();
        try {
            String shortUrl = urlService.createShortUrl(request.getUrl());
            shortUrlResponse.setUrl(shortUrl);
            return shortUrlResponse;
        } catch (Exception e) {
            shortUrlResponse.setUrl("Not found URL");
            return shortUrlResponse;
        }
    }

    @ApiOperation(value = "")
    @GetMapping("/url/all")
    @ResponseBody
    public DataRecords gelAllLinks(){
        DataRecords allUrlRecords = urlService.getAllUrlRecords();
        return allUrlRecords;
    }
}
