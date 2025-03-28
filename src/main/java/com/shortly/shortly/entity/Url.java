package com.shortly.shortly.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "url")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    @Column(unique = true)
    private String shortUrl;

    private Long accessCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
