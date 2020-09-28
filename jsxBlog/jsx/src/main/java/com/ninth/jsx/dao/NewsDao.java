package com.ninth.jsx.dao;

import com.ninth.jsx.entity.News;

import java.util.List;

public interface NewsDao {
    public List<News> getNewsList(News news);
    public Integer getNewsListCount();
}
