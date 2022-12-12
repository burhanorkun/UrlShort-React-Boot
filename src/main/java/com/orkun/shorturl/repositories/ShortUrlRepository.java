package com.orkun.shorturl.repositories;

import com.orkun.shorturl.models.ShortUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShortUrlRepository extends JpaRepository<ShortUrl, Long> {
    ShortUrl findByLongUrl(String url);
    ShortUrl findByKey(String key);

    @Query("FROM ShortUrl ORDER BY viewed DESC")
    List<ShortUrl> findAllByView();
}
