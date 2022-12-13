package com.orkun.shorturl.repositories;

import com.orkun.shorturl.models.ShortUrlLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortUrlLogRepository extends JpaRepository<ShortUrlLog, Long> {
}
