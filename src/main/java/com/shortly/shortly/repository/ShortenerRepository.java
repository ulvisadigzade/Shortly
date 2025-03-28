package com.shortly.shortly.repository;

import com.shortly.shortly.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShortenerRepository extends JpaRepository<Url,Long> {
    Optional<Url> findByShortUrl(String shortUrl);
    boolean existsByShortUrl(String shortUrl);
    void deleteByShortUrl(String shortUrl);
    boolean existsByUrl(String Url);
}
