package com.orkun.shorturl.services;

import com.orkun.shorturl.models.ShortUrl;
import com.orkun.shorturl.repositories.ShortUrlRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StatisticService {

    private final ShortUrlRepository shortUrlRepository;

    public List<ShortUrl> getAllShortUrlByOrder(){
        return shortUrlRepository.findAllByView();
    }
}
