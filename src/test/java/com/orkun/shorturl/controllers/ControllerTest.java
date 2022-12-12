package com.orkun.shorturl.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orkun.shorturl.ShorturlApplication;
import com.orkun.shorturl.dtos.ShortUrlRequest;
import com.orkun.shorturl.services.ShortenerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

//@RunWith(SpringRunner.class)
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = {ShorturlApplication.class})
//@AutoConfigureMockMvc
//@WebAppConfiguration
public class ControllerTest {
    //@MockBean
    private ShortenerService shortenerService;

    //@Autowired
    private MockMvc mockMvc;

    private static final String input = "www.google.com";
    private static final String output = "drT23f";

    @Before
    public void setup(){

        //when(shortenerService.createShortUrl(input)).thenReturn(output);
        /*
        this.webTestClient = WebTestClient
                .bindToServer()
                .baseUrl("http://localhost:" + randomServerPort)
                .build(); */
    }

    //@Test
    public void GIVEN_valid_url_WHEN_calling_put_THEN_return_ok_and_shortUrl_in_response() throws Exception{
        when(shortenerService.createShortUrl(input)).thenReturn(output);

        ShortUrlRequest request = ShortUrlRequest.builder().url(input).salt("test").build();
        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(post("/api/v1/url")
                        .content(mapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.url", equalTo(output)));
                        //.andExpect(MockMvcResultMatchers.jsonPath("$.longUrl", equalTo(input)));
        /*
        final var shortUrlResponse = new ShortUrlResponse().setLongUrl("http://localhost/").setUrl("key");
        when(mockService.createShortUrl(any())).thenReturn("key");

        webTestClient.post()
                .uri("/api/v1/url")
                .bodyValue(shortUrlResponse)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().exists("Location")
                .expectBody(ShortUrlResponse.class)
                .value(d -> assertNotNull(d.getUrl()))
                .value(d -> assertNotNull(d.getLongUrl()));

        verify(mockService, times(1)).createShortUrl(any());

         */
    }

    //@Test
    void GIVEN_url_too_short_WHEN_calling_create_THEN_return_BAD_REQUEST() {

    }
}

