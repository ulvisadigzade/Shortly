package com.shortly.shortly.service;


import com.shortly.shortly.dto.UrlRequestDTO;
import com.shortly.shortly.dto.UrlResponseDTO;
import com.shortly.shortly.entity.Url;
import com.shortly.shortly.exception.BusinessException;
import com.shortly.shortly.exception.ErrorCode;
import com.shortly.shortly.mapper.UrlMapper;
import com.shortly.shortly.repository.ShortenerRepository;
import com.shortly.shortly.util.UrlGenerator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ShortenerService {
    private final UrlMapper urlMapper;
    private final UrlGenerator urlGenerator;
    private final ShortenerRepository shortenerRepository;


    //Shorten given url
    public UrlResponseDTO shorten(UrlRequestDTO urlRequestDTO){
        if(shortenerRepository.existsByUrl(urlRequestDTO.getUrl())){
            throw new BusinessException(
                    ErrorCode.AlreadyExistsException,
                    "The url you want to shorten already exists in system."
            );
        }
        Url url = urlMapper.toEntity(urlRequestDTO);
        url.setShortUrl(urlGenerator.generateShortUrl());
        Url savedUrl = shortenerRepository.save(url);
        return urlMapper.toResponse(savedUrl);
    }

    //Get url from shortUrl;
    public UrlResponseDTO getUrl(String shortUrl){
        return shortenerRepository.findByShortUrl(shortUrl)
                .map(url -> {
                    url.setAccessCount(url.getAccessCount()+1);
                    shortenerRepository.save(url);
                    return urlMapper.toResponse(url);
                })
                .orElseThrow(()-> new BusinessException(
                        ErrorCode.NotFoundException,
                        "The short url isn't mapped to any url."
                ));
    }

    public UrlResponseDTO updateUrl(String shortUrl,UrlRequestDTO urlRequestDTO){
        return shortenerRepository.findByShortUrl(shortUrl)
                .map(url -> {
                    url.setUrl(urlRequestDTO.getUrl());
                    url.setAccessCount(0L);
                    url.setUpdatedAt(LocalDateTime.now());
                    shortenerRepository.save(url);
                    return urlMapper.toResponse(url);
                })
                .orElseThrow(() -> new BusinessException(
                        ErrorCode.NotFoundException,
                        "Short URL not found."
                ));
    }

    @Transactional
    public void deleteUrl(String shortUrl){
        if(!shortenerRepository.existsByShortUrl(shortUrl)){
            throw new BusinessException(
                    ErrorCode.NotFoundException,
                    "The url you want to delete doesn't exist."
            );
        }
        shortenerRepository.deleteByShortUrl(shortUrl);
    }

    public Long getStats(String shortUrl){
        return shortenerRepository.findByShortUrl(shortUrl)
                .map(url -> url.getAccessCount())
                .orElseThrow(() -> new BusinessException(
                        ErrorCode.NotFoundException,
                        "Url mapped to " + shortUrl + " doesn't exist."
                ));
    }
}
