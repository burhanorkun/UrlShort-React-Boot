package com.orkun.shorturl.strategies.url;

import com.orkun.shorturl.dtos.DataRecords;
import com.orkun.shorturl.enums.ActionEnum;
import com.orkun.shorturl.models.DataRecord;
import com.orkun.shorturl.models.ShortUrl;
import com.orkun.shorturl.repositories.ShortenerRepository;
import com.orkun.shorturl.strategies.Shortener;
import com.orkun.shorturl.utils.ValidationUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@AllArgsConstructor
public class UrlShortenerStrategy implements Shortener {

    private static final byte[] VALID_CHARS
            = "ABCEDFGHIJKLMNOPQRSTUVWXYZabcedefghijklmnopqrstuvwxyz0123456789-_".getBytes();
    private final ValidationUtil validation;
    private final ShortenerRepository repository;

    @Override
    public ActionEnum getAction() {
        return ActionEnum.URL;
    }

    public String longToShort(String url){
        return putUrl(url);
    }

    @Override
    public String shortToLong(String key) {

        var shortUrl = repository.findByKey(key);
        if (shortUrl == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "URL not found");

        return shortUrl.getLongUrl();
    }

    public String putUrl(String url){
        String longUrl = validation.conventionControl(url);  //http control

        if(!validation.isValidUrl(longUrl)) // url validation
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Url");

        final var found = repository.findByLongUrl(longUrl);
        if(found != null){
            return found.getKey();
        }

        var key = createUniqueKey();
        var shortUrl = ShortUrl.builder()
                .longUrl(longUrl)
                .createdDate(LocalDateTime.now())
                .key(key).build();
        repository.save(shortUrl);

        return key;
    }

    public List<DataRecord> getAllRecords(){
        List<ShortUrl> all = repository.findAll();
        return new ArrayList<>(all);
    }

    private String createUniqueKey(){
        String key;
        do{
            final var newKey = new byte[6];
            final var rand = new Random();
            for (int i = 0; i < 6; i++) {
                newKey[i] = VALID_CHARS[rand.nextInt(VALID_CHARS.length) % VALID_CHARS.length];
            }
            key = new String(newKey, StandardCharsets.UTF_8);
        } while (repository.findByKey(key) != null);
        return key;
    }

}
