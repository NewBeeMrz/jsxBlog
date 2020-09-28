package com.ninth.jsx.service;

import com.ninth.jsx.entity.User;
import com.ninth.jsx.utils.Page;

import java.util.List;
import java.util.Map;

public interface UserService {
    public User login(User user);
    public int register(User user);
    public User isExistUser(String user_name);
    public Page<User> getUserList(Integer page, Integer rows);
    public Map<String,Object> getUserInfoById(int u_id);
    public int createUserInfo(int u_id);
}
