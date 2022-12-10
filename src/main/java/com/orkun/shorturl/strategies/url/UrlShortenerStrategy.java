package com.orkun.shorturl.strategies.url;

import com.orkun.shorturl.enums.ActionEnum;
import com.orkun.shorturl.models.ShortUrl;
import com.orkun.shorturl.repositories.ShortenerRepository;
import com.orkun.shorturl.strategies.Shortener;
import com.orkun.shorturl.utils.Base62Conversion;
import com.orkun.shorturl.utils.ValidationUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.Random;

@Component
@AllArgsConstructor
public class UrlShortenerStrategy implements Shortener {

    private final Base62Conversion conversion;
    private final ValidationUtils validation;
    private final ShortenerRepository repository;

    public String longToShort(String url){
        if(validation.isValidUrl(url)){
            var urlEntity = ShortUrl.builder()
                    .longUrl(url).createdDate(LocalDateTime.now())
                    .build();
            // repository
            var entityControl = repository.findByLongUrl(url);
            System.out.println("Value of entity for duplicity check is = " + entityControl);
            if(entityControl == null){
                System.out.println("just before save");
                System.out.println("id is this ==>" + urlEntity.getId());
                Random random=new Random();
                int randnum =  10 + random.nextInt(90);
                urlEntity.setRandNum(randnum);

                var entity = repository.save(urlEntity);
                long entityId = entity.getId();
                System.out.println("value of entity.getid is==" + entityId);
                System.out.println("value of random number is==" + randnum);

                long eid = entity.getId();
                System.out.println("value of entity.getid is=="+eid);

                long result =  Long.parseLong(String.valueOf(randnum) + eid);
                System.out.println("value of final number to be enncoded =="+result);
                var convertedUrl = conversion.encode(result);
                System.out.println("Inside Encoding method - Converted Short URL is ===>"+convertedUrl);
                return convertedUrl;
            }else{
                System.out.println("inside URL already present");
                int randNum = entityControl.getRandNum();
                long eid = entityControl.getId();
                long result =  Long.parseLong(String.valueOf(randNum) + eid);
                System.out.println("value of final number to be enncoded =="+result);
                var convertedUrl = conversion.encode(result);
                System.out.println("Inside Encoding method - Converted Short URL already present is ===>"+convertedUrl);
                return convertedUrl;
            }
        }else{
            return "Error: Not a valid URL";
        }
    }

    @Override
    public String shortToLong(String input) {
        long id = conversion.decode(input);
        System.out.println("Full number after decoding ="+id);
        long new_id = Long.parseLong(Long.toString(id).substring(2));
        System.out.println("After remove random number after decoding ="+new_id);
        System.out.println("Inside Decoding method - Short URL to be converted is ===>"+input);

        var entity = repository.findById(new_id)
                .orElseThrow(() -> new EntityNotFoundException("There is no entity with short URL" + input));

        //var analysisPOJO = new ShortenerAnalysisPOJO();
        //analysisPOJO.setLurl(new_id);
        //analysisPOJO.setViewedDate(new Date());

        //shortenerAnalysisRepository.save(analysisPOJO);
        System.out.println("Inside Decoding method - Long URL post conversion is ===>"+entity.getLongUrl());
        return entity.getLongUrl();
    }

    @Override
    public ActionEnum getAction() {
        return ActionEnum.URL;
    }

}
