package com.orkun.shorturl.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.URL;

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
//@SequenceGenerator(name = "short_url_sequence", sequenceName = "short_url_sequence",  initialValue = 10000, allocationSize = 1)
@NamedQuery(name = "ShortUrl.findByLongUrl", query = "SELECT u FROM ShortUrl u WHERE LOWER(u.longUrl) = LOWER(?1)")
public class ShortUrl implements DataRecord{

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO, generator = "short_url_sequence")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Key should not be blank")
    private String key;

    @NotBlank(message = "Full URL cannot be blank")
    @URL
    @Column(nullable = false)
    private String longUrl;

    @Column(name = "created_at")
    private LocalDateTime createdDate;

}
