package com.ninth.jsx.dao;

import com.ninth.jsx.entity.News;
import com.ninth.jsx.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserDao {
    public void save(User user);
    public User login(User user);
    public int register(User user);
    public User isExistUser(@Param(value="user_name")String user_name);
    public List<User> getUserList(User user);
    public Map<String,Object> getUserInfoById(@Param(value="u_id")int u_id);
    public int getUserListCount();
    public int createUserInfo(@Param(value="u_id")int u_id);

}
