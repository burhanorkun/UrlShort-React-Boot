package com.orkun.shorturl.services;

import com.orkun.shorturl.models.DataRecord;
import com.orkun.shorturl.models.ShortUrl;
import com.orkun.shorturl.repositories.ShortUrlRepository;
import com.orkun.shorturl.strategies.url.UrlShortenerStrategy;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class UrlServiceTest {

    @Mock
    private ShortUrlRepository shortUrlRepository;

    @InjectMocks
    private UrlShortenerStrategy shortenerService;

    @Before
    public void setup(){
    }

    @Test
    public void getAllRecords(){
        List<ShortUrl> toDoList = new ArrayList<>();
        toDoList.add(ShortUrl.builder().key("key1").longUrl("www.google.com").build());
        toDoList.add(ShortUrl.builder().key("key2").longUrl("www.yahoo.com").build());
        toDoList.add(ShortUrl.builder().key("key3").longUrl("www.tesla.com").build());

        when(shortUrlRepository.findAll()).thenReturn(toDoList);

        List<DataRecord> result = shortenerService.getAllRecords();
        assertEquals(3, result.size());
    }

    @Test
    public void getOriginalUrlTest(){
        ShortUrl rec1 = ShortUrl.builder().key("key1").longUrl("www.tesla.com").viewed(3l).build();
        when(shortUrlRepository.findByKey("key1")).thenReturn(rec1);

        String result = shortenerService.shortToLong("key1");
        assertEquals("www.tesla.com", result);
    }
}
