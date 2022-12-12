package com.orkun.shorturl.controllers;

import com.orkun.shorturl.dtos.DataRecords;
import com.orkun.shorturl.dtos.LongUrlResponse;
import com.orkun.shorturl.dtos.ShortUrlRequest;
import com.orkun.shorturl.dtos.ShortUrlResponse;
import com.orkun.shorturl.models.DataRecord;
import com.orkun.shorturl.models.ShortUrl;
import com.orkun.shorturl.services.ShortenerService;
import com.orkun.shorturl.services.StatisticService;
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
@CrossOrigin(origins = "http://localhost:3000")
public class UrlShortenerController {
    private final ShortenerService urlService;

    private final StatisticService statisticService;

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
            shortUrlResponse.setLongUrl(request.getUrl());
            return shortUrlResponse;
        } catch (Exception e) {
            shortUrlResponse.setUrl("Not found URL");
            return shortUrlResponse;
        }
    }

    @ApiOperation(value = "Retrieve all URL links")
    @GetMapping("/url/all")
    @ResponseBody
    public DataRecords gelAllUrlLinks(){
        return urlService.getAllUrlRecords();
    }

    @ApiOperation(value = "Retrieve all URL links")
    @DeleteMapping("/url/{id}")
    @ResponseBody
    public void deleteAllUrlLink(@PathVariable Long id){
        urlService.deleteUrlRecord(id);
    }

    @ApiOperation(value = "Retrieve all URL links with Statistic")
    @GetMapping("/url/statistic")
    @ResponseBody
    public List<ShortUrl> gelUrlStatistic(){
        return statisticService.getAllShortUrlByOrder();
    }



}
