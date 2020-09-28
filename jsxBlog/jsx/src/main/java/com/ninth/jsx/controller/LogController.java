package com.ninth.jsx.controller;

import com.ninth.jsx.entity.Log;
import com.ninth.jsx.entity.User;
import com.ninth.jsx.service.LogService;
import com.ninth.jsx.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("log")
public class LogController {
    @Autowired
    @Qualifier("logService")
    private LogService logService;

    private int code;//返回状态码
    private String msg;//状态码说明

    SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置时间戳格式
    Date return_date = new Date(System.currentTimeMillis());//获取本地格式
    /*******创建log日志记录********/
    public void log(Integer create_by, HttpServletRequest request, String title, String method){
        Log log = new Log();
        log.setTitle(title);
        log.setCreate_by(create_by);
        log.setCreate_date(formatter.format(return_date));
        log.setMethod(method);
        //获取浏览器信息
        String ua = request.getHeader("User-Agent");
        log.setUser_agent(ua);
        this.logService.createLog(log);
    }
    /*******创建log日志记录********/
    public void log(HttpServletRequest request,String title,String method){
        Log log = new Log();
        log.setTitle(title);
        log.setCreate_date(formatter.format(return_date));
        log.setMethod(method);
        //获取浏览器信息
        String ua = request.getHeader("User-Agent");
        log.setUser_agent(ua);
        this.logService.createLog(log);
    }

    /*******删除日志ById********/
    @RequestMapping("deleteLogById")
    public Map<String,Object> deleteLogById(Integer id,HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        int rows = this.logService.deleteLogById(id);

        if(rows > 0){
            code = 200;
            msg="删除成功";
            map.put("msg",msg);
            map.put("code",code);
        }else{
            code = -1;
            msg="删除失败";
            map.put("msg",msg);
            map.put("code",code);
        }

        return map;
    }

    /*******获取日志列表********/
    @RequestMapping("getLogList")
    public Map<String,Object> getUserList(Integer page, @RequestParam(defaultValue = "10") Integer rows, HttpServletRequest request){
        Page<Log> logList = this.logService.getLogList(page,rows);
        Map<String,Object> map = new HashMap<>();

        if(logList != null){
            this.log(1,request,"获取日志列表","GET");
            code = 200;
            msg="获取日志列表成功";
            map.put("msg",msg);
            map.put("code",code);
            map.put("data",logList);
        }else{
            code = -1;
            msg="获取日志列表失败";
            map.put("msg",msg);
            map.put("code",code);
        }
        return map;
    }
}
