package com.ninth.jsx.service.Impl;

import com.ninth.jsx.dao.NewsDao;
import com.ninth.jsx.entity.News;
import com.ninth.jsx.service.NewsService;
import com.ninth.jsx.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("newsService")
@Transactional
public class NewsServiceimpl implements NewsService {
    @Autowired
    NewsDao newsDao;

    @Override
    public Page<News> getNewsList(Integer page, Integer rows) {
        // 创建用户对象
        News news = new News();

        // 当前页
        news.setStart((page-1) * rows) ;
        // 每页数
        news.setRows(rows);
        // 查询资讯列表
        List<News> newsList = this.newsDao.getNewsList(news);
        // 查询资讯列表总记录数
        Integer count = this.newsDao.getNewsListCount();
        // 创建Page返回对象
        Page<News> result = new Page<>();
        result.setPage(page);
        result.setRows(newsList);
        result.setSize(rows);
        result.setTotal(count);
        return result;
    }

}
