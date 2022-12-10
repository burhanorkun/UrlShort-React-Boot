package com.orkun.shorturl.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
@Table(name = "short_url")
@SequenceGenerator(name = "short_url_sequence", sequenceName = "short_url_sequence",  initialValue = 10000, allocationSize = 1)
@NamedQuery(name = "ShortUrl.findByLongUrl", query = "SELECT u FROM ShortUrl u WHERE LOWER(u.longUrl) = LOWER(?1)")
public class ShortUrl {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "short_url_sequence")
    private long id;

    @Column(nullable = false)
    private int randNum;

    @NotBlank(message = "Full URL {javax.validation.constraints.NotBlank.message}")
    @Size(min = 9, max = 2048, message = "Field [url] length should be more than 8 and less or equal to 2048")
    @Column(nullable = false)
    private String longUrl;

    @Column(name = "created_at")
    private LocalDateTime createdDate;

    //private String slug;
}
