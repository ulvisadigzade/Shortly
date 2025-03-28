package com.shortly.shortly.controller;


import com.shortly.shortly.dto.UrlRequestDTO;
import com.shortly.shortly.dto.UrlResponseDTO;
import com.shortly.shortly.service.ShortenerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class ShortenerController {
    private final ShortenerService shortenerService;

    @PostMapping("/shorten")
    public ResponseEntity<UrlResponseDTO> shorten(@RequestBody @Valid UrlRequestDTO urlRequestDTO){
        UrlResponseDTO urlResponseDTO = shortenerService.shorten(urlRequestDTO);
        return ResponseEntity.created(URI.create(urlResponseDTO.getShortUrl())).body(urlResponseDTO);
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<Void> redirectToOriginalUrl(@PathVariable String shortUrl) {
        UrlResponseDTO url = shortenerService.getUrl(shortUrl);
        //moved_permanently - resource permanently moved to new location
        //more restful than redirectView;
        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY)
                .location(URI.create(url.getUrl()))
                .build();
    }

    @PutMapping("/shorten/{shortUrl}")
    public ResponseEntity<UrlResponseDTO> updateUrl(@PathVariable String shortUrl,@RequestBody @Valid UrlRequestDTO urlRequestDTO){
        UrlResponseDTO urlResponseDTO = shortenerService.updateUrl(shortUrl,urlRequestDTO);
        return ResponseEntity.ok(urlResponseDTO);
    }

    @DeleteMapping("/shorten/{shortUrl}")
    public ResponseEntity<Void> deleteUrl(@PathVariable String shortUrl){
        shortenerService.deleteUrl(shortUrl);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/shorten/{shortUrl}/stats")
    public ResponseEntity<Long> getStats(@PathVariable String shortUrl){
        Long stats = shortenerService.getStats(shortUrl);
        return ResponseEntity.ok(stats);
    }
}
