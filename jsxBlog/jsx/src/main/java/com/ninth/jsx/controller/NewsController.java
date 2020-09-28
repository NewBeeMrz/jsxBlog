package com.ninth.jsx.controller;

import com.ninth.jsx.entity.News;
import com.ninth.jsx.service.LogService;
import com.ninth.jsx.service.NewsService;
import com.ninth.jsx.utils.HtmlScript;
import com.ninth.jsx.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("news")
public class NewsController {
    @Autowired
    @Qualifier("newsService")
    private NewsService newsService;

    @Autowired
    @Qualifier("logService")
    private LogService logService;

    private int code;//返回状态码
    private String msg;//状态码说明

    /*******获取文章列表********/
    @RequestMapping("getNewsList")
    public Map<String,Object> getNewsList(Integer page, @RequestParam(defaultValue = "6") Integer rows, HttpServletRequest request){
        Page<News> newsList = this.newsService.getNewsList(page,rows);

        //清空html标签保留字符
        for (int i = 0; i < newsList.getRows().size(); ++i) {
            newsList.getRows().get(i).setContent(HtmlScript.delHTMLTag(newsList.getRows().get(i).getContent()));
        }

        Map<String,Object> map = new HashMap<>();

        if(newsList != null){
            code = 200;
            msg="获取资讯列表成功";
            map.put("msg",msg);
            map.put("code",code);
            map.put("data",newsList);
        }else{
            code = -1;
            msg="获取资讯列表失败";
            map.put("msg",msg);
            map.put("code",code);
        }
        return map;
    }

}
