package com.shortly.shortly.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.hibernate.validator.constraints.URL;

@Getter
public class UrlRequestDTO {

    @NotEmpty(message = "URL can't be empty")
    @Size(min=8,message="URL size must be at least 8 symbols")
    @URL(message = "Not valid URL")
    private String url;
}
