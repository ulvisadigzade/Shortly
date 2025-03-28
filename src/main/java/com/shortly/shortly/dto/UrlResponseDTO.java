package com.shortly.shortly.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class UrlResponseDTO {
    private Long id;
    private String url;
    private String shortUrl;
    private Long accessCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
