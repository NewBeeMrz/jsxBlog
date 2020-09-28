package com.ninth.jsx.service;

import com.ninth.jsx.entity.News;
import com.ninth.jsx.utils.Page;

import java.util.List;

public interface NewsService {
    public Page<News> getNewsList(Integer page, Integer rows);
}
