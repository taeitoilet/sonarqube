package com.fintech.tech_clone.controller;

import com.fintech.tech_clone.dto.request.news.NewsCreateRequest;
import com.fintech.tech_clone.dto.request.news.NewsUpdateRequest;
import com.fintech.tech_clone.dto.response.ApiResponse;
import com.fintech.tech_clone.dto.response.NewsFindReponse;
import com.fintech.tech_clone.entity.News;
import com.fintech.tech_clone.service.NewsService;
import com.fintech.tech_clone.validGroup.CreateGroup;
import com.fintech.tech_clone.validGroup.UpdateGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsController {
    @Autowired
    private NewsService newsService;

    @PreAuthorize("hasAuthority('post_data')")
    @PostMapping("/create")
    public ApiResponse<News> createNews( @RequestBody @Validated(CreateGroup.class)  NewsCreateRequest request){
        ApiResponse<News> apiResponse = new ApiResponse<>();
        apiResponse.setResult(newsService.create(request));
        return apiResponse;
    }
//    @PreAuthorize("hasRole('Admin')")
    @GetMapping
    public ApiResponse<Page<News>> getAllNews(@RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "10") int size){
        ApiResponse<Page<News>> apiResponse = new ApiResponse<>();
        Pageable pageable = PageRequest.of(page, size);
        apiResponse.setResult(newsService.getNews(pageable));
        return apiResponse;
    }
    @PreAuthorize("hasAuthority('update_data')")
    @PutMapping("/update/{news_id}")
    public ApiResponse<News> updateNews(@PathVariable int news_id, @RequestBody @Validated(UpdateGroup.class) NewsUpdateRequest request){
        ApiResponse<News> apiResponse = new ApiResponse<>();
        apiResponse.setResult(newsService.updateNews(news_id,request));
        return apiResponse;
    }
    @PreAuthorize("hasAuthority('delete_data')")
    @DeleteMapping("/delete/{news_id}")
    public ApiResponse deleteNews(@PathVariable int news_id){
        newsService.deleteNews(news_id);
        ApiResponse apiResponse = new ApiResponse();

        return apiResponse;
    }


    @GetMapping("/findnews")
//    public ApiResponse<ArrayList<NewsFindReponse>> findNewByTitle(@RequestParam String title){
//        ApiResponse<ArrayList<NewsFindReponse>> apiResponse = new ApiResponse<>();
//        if(newsService.findNewsByTitle(title) != null && !newsService.findNewsByTitle(title).isEmpty()){
//            apiResponse.setResult(newsService.findNewsByTitle(title));
//
//        }else {
//            apiResponse.setMessage("Khong tim thay ket qua");
//        }
//        return apiResponse;
//    }
    public ApiResponse<ArrayList<News>> findNewByTitle(@RequestParam String title){
        ApiResponse<ArrayList<News>> apiResponse = new ApiResponse<>();
        if(newsService.findNewsByTitle(title) != null && !newsService.findNewsByTitle(title).isEmpty()){
            apiResponse.setResult(newsService.findNewsByTitleV2(title));
        }else{
            apiResponse.setCode(999);
            apiResponse.setMessage("Khong tim thay ket qua");
        }
        return apiResponse;
    }
}
