package com.fintech.tech_clone.repository;

import com.fintech.tech_clone.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface NewsRepository extends JpaRepository<News,Integer> {
    @Query(value = "SELECT * FROM tblnews WHERE news_title LIKE %:title%", nativeQuery = true)
    ArrayList<News> findByTitle(@Param("title") String title);
}
