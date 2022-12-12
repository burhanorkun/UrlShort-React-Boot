package com.orkun.shorturl.repositories;

import com.orkun.shorturl.models.ShortUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortenerRepository extends JpaRepository<ShortUrl, Long> {
    ShortUrl findByLongUrl(String url);
    ShortUrl findByKey(String key);
}
