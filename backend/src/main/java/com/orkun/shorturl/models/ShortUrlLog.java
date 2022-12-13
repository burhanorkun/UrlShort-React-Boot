package com.orkun.shorturl.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "short_url_log")
public class ShortUrlLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Key should not be blank")
    private String key;

    @NotBlank(message = "Full URL cannot be blank")
    @URL
    @Column(nullable = false)
    private String longUrl;

    @Column(name = "client_ip")
    private String clientIp;

    @Column(name = "created_at")
    private LocalDateTime createdDate;
}
