package com.shortly.shortly.util;

import com.shortly.shortly.repository.ShortenerRepository;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class UrlGenerator {
    private final ShortenerRepository shortenerRepository;
    private final String alphaNumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private final int length = 6;
    private final int alphaNumericLength = alphaNumeric.length();
    Random rand = new Random();

    public UrlGenerator(ShortenerRepository shortenerRepository){
        this.shortenerRepository=shortenerRepository;
    }

    public String generateShortUrl(){
        StringBuffer newUrl = new StringBuffer("AAAAAA");
        do {
            for(int i=0;i<length;i++){
                int randomValue = rand.nextInt(alphaNumericLength);
                newUrl.setCharAt(i,alphaNumeric.charAt(randomValue));
            }
        }while(shortenerRepository.existsByShortUrl(newUrl.toString()));
        return newUrl.toString();
    }
}
