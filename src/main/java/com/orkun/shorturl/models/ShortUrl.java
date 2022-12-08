package com.orkun.shorturl.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "short_urls")
public class ShortUrl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Full URL {javax.validation.constraints.NotBlank.message}")
    @Size(min = 9, max = 2048, message = "Field [url] length should be more than 8 and less or equal to 2048")
    @Column(name = "long_url")
    private String longUrl;

    @Column(name = "created_at")
    private LocalDateTime createdDate;
}
