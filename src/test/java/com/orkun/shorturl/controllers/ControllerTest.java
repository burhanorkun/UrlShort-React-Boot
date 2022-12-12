package com.orkun.shorturl.controllers;

import com.orkun.shorturl.dtos.DataRecords;
import com.orkun.shorturl.models.DataRecord;
import com.orkun.shorturl.models.ShortUrl;
import com.orkun.shorturl.services.ShortenerService;
import com.orkun.shorturl.services.StatisticService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class ControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private UrlShortenerController controller;

    @Mock
    private ShortenerService urlService;

    @Mock
    private StatisticService statisticService;


    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void getAllShortenUrl() throws Exception{

        List<DataRecord> list = Arrays.asList(
        ShortUrl.builder().key("key1").longUrl("www.google.com").build(),
        ShortUrl.builder().key("key2").longUrl("www.test.com").build() );

        DataRecords records = new DataRecords();
        records.setAllUrlRecords(list);

        when(urlService.getAllUrlRecords()).thenReturn(records);

        mockMvc.perform(get("/api/v1/url/all"))
                .andExpect(status().isOk());

    }

    @Test
    public void GIVEN_url_too_short_WHEN_calling_create_THEN_return_BAD_REQUEST() {

    }
}

