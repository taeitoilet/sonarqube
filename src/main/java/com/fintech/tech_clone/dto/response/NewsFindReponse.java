package com.fintech.tech_clone.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class NewsFindReponse {
    private String news_title;
    private String news_author;
    private String news_img;
}
