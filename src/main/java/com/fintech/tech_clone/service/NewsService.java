package com.fintech.tech_clone.service;

import com.fintech.tech_clone.dto.request.news.NewsCreateRequest;
import com.fintech.tech_clone.dto.request.news.NewsUpdateRequest;
import com.fintech.tech_clone.dto.response.NewsFindReponse;
import com.fintech.tech_clone.entity.News;
import com.fintech.tech_clone.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class NewsService {

    @Autowired
    private NewsRepository newsRepository;

    public News create(NewsCreateRequest request){
        News news = new News();
        news.setNews_title(request.getNews_title());
        news.setNews_author(request.getNews_author());
        news.setNews_content(request.getNews_content());
        news.setNews_img(request.getNews_img());
        news.setNews_create_date(LocalDate.now());
        return newsRepository.save(news);
    }
    public News createNews(NewsCreateRequest request){
        News news = new News();
        news.setNews_title(request.getNews_title());
        news.setNews_author(request.getNews_author());
        news.setNews_content(request.getNews_content());
        news.setNews_img(request.getNews_img());
        news.setNews_create_date(LocalDate.now());
        return newsRepository.save(news);

    }

    public Page<News> getNews(Pageable pageable){
        return newsRepository.findAll(pageable);
    }
    public News getNewsById(int id){
        return newsRepository.findById(id).orElseThrow(() -> new RuntimeException("New not found"));
    }
    public News updateNews(int id,NewsUpdateRequest request){
        News news = getNewsById(id);
        news.setNews_title(request.getNews_title());
        news.setNews_author(request.getNews_author());
        news.setNews_content(request.getNews_content());
        news.setNews_img(request.getNews_img());
        news.setNews_modified_date(LocalDate.now());
        return newsRepository.save(news);
    }
    public void deleteNews(int id){
        newsRepository.deleteById(id);
    }
    public ArrayList<NewsFindReponse> findNewsByTitle(String name){
        List<News> list = newsRepository.findAll();
        ArrayList<NewsFindReponse> arr1 = new ArrayList<>();
        NewsFindReponse news = new NewsFindReponse();
        Pattern pattern = Pattern.compile(name, Pattern.CASE_INSENSITIVE);

        for (News item : list) {
            if (pattern.matcher(item.getNews_title()).find()) {
                news.setNews_author(item.getNews_author());
                news.setNews_title(item.getNews_title());
                news.setNews_img(item.getNews_img());
                arr1.add(news);
            }
        }
        return arr1;
    }
    public ArrayList<News> findNewsByTitleV2(String title){
       return newsRepository.findByTitle(title);
    }
}
