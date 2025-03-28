package com.shortly.shortly.mapper;


import com.shortly.shortly.dto.UrlRequestDTO;
import com.shortly.shortly.dto.UrlResponseDTO;
import com.shortly.shortly.entity.Url;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UrlMapper {

    public Url toEntity(UrlRequestDTO urlRequestDTO){
        return Url.builder()
                .url(urlRequestDTO.getUrl())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .accessCount(0L)
                .build();
    }

    public UrlResponseDTO toResponse(Url url){
        return new UrlResponseDTO(
                url.getId(),
                url.getUrl(),
                url.getShortUrl(),
                url.getAccessCount(),
                url.getCreatedAt(),
                url.getUpdatedAt()
        );
    }
}
