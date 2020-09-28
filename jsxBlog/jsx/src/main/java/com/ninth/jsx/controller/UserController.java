package com.ninth.jsx.controller;

import com.ninth.jsx.entity.Log;
import com.ninth.jsx.entity.User;
import com.ninth.jsx.service.LogService;
import com.ninth.jsx.service.UserService;
import com.ninth.jsx.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("user")
public class UserController {
    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @Autowired
    @Qualifier("logService")
    private LogService logService;

    private int code;//返回状态码
    private String msg;//状态码说明


    SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置时间戳格式
    Date return_date = new Date(System.currentTimeMillis());//获取本地格式
    Log log = new Log();
    /*******创建log日志记录********/
    public void log(Integer create_by, HttpServletRequest request, String title, String method){
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
        log.setTitle(title);
        log.setCreate_date(formatter.format(return_date));
        log.setMethod(method);
        //获取浏览器信息
        String ua = request.getHeader("User-Agent");
        log.setUser_agent(ua);
        this.logService.createLog(log);
    }


    /*******登录********/
    @RequestMapping("login")
    public Map<String,Object> login(String user_name,String password, HttpServletRequest request){
        User user = new User();
        user.setUser_name(user_name);
        user.setPassword(password);

        User temp = this.userService.login(user);
        Map<String,Object> map = new HashMap<>();
        Map<String,Object> userinfo = new HashMap<>();
        if(temp != null){
            //生成日志文件
            this.log(temp.getU_id(),request,"用户登录","POST");
            userinfo.put("u_id",temp.getU_id());
            userinfo.put("user_name",temp.getUser_name());
            userinfo.put("user_rank",temp.getUser_rank());
            code = 200;
            msg="登录成功";
            map.put("data",userinfo);
            map.put("msg",msg);
            map.put("code",code);
        }else{
            code = -1;
            msg="用户名或密码错误";
            map.put("msg",msg);
            map.put("code",code);
        }
        return map;
    }

    /*******注册********/
    @RequestMapping("register")
    public Map<String,Object> register(User user,String rank,HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        user.setUser_rank(Integer.parseInt(rank));
        if(this.userService.isExistUser(user.getUser_name()) !=null){
            code = -2;
            msg="该用户名已存在";
            map.put("msg",msg);
            map.put("code",code);
            return map;
        }


        user.setCreate_date(formatter.format(return_date));
        user.setStatus("正常");
        user.setBelike(0);

        Integer rows = this.userService.register(user);
        Integer u_id = this.userService.isExistUser(user.getUser_name()).getU_id();
        Integer row = this.userService.createUserInfo(u_id);
        if(rows > 0 && row > 0){
            //生成日志文件
            this.log(request,"用户注册","POST");
            code = 200;
            msg="注册成功";
            map.put("msg",msg);
            map.put("code",code);
        }else{
            code = -1;
            msg="注册失败";
            map.put("msg",msg);
            map.put("code",code);
        }
        return map;
    }

    /*******获取用户信息列表********/
    @RequestMapping("getUserList")
    public Map<String,Object> getUserList(Integer page,@RequestParam(defaultValue = "10") Integer rows,HttpServletRequest request){
        Page<User> userList = this.userService.getUserList(page,rows);
        Map<String,Object> map = new HashMap<>();

        if(userList != null){
            this.log(1,request,"获取用户列表信息","GET");
            code = 200;
            msg="获取用户列表成功";
            map.put("msg",msg);
            map.put("code",code);
            map.put("data",userList);
        }else{
            code = -1;
            msg="获取用户列表失败";
            map.put("msg",msg);
            map.put("code",code);
        }
        return map;
    }

    /*******通过用户id查看个人详细信息********/
    @RequestMapping("getUserInfoById")
    public Map<String,Object> getUserInfoById(int u_id,HttpServletRequest request){
        Map<String,Object> userInfo = this.userService.getUserInfoById(u_id);
        Map<String,Object> map = new HashMap<>();

        if(userInfo != null){
            this.log(u_id,request,"查看个人详细信息","GET");
            code = 200;
            msg="获取用户信息成功";
            map.put("msg",msg);
            map.put("code",code);
            map.put("data",userInfo);
        }else{
            code = -1;
            msg="获取用户信息失败";
            map.put("msg",msg);
            map.put("code",code);
        }
        return map;
    }
}
