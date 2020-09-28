package com.ninth.jsx.service.Impl;

import com.ninth.jsx.dao.UserDao;
import com.ninth.jsx.entity.User;
import com.ninth.jsx.service.UserService;
import com.ninth.jsx.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User login(User user) {
        return this.userDao.login(user);
    }

    @Override
    public int register(User user) {
        return this.userDao.register(user);
    }

    @Override
    public User isExistUser(String user_name) {
        return this.userDao.isExistUser(user_name);
    }

    @Override
    public Page<User> getUserList(Integer page, Integer rows) {
        // 创建用户对象
        User user = new User();

        // 当前页
        user.setStart((page-1) * rows) ;
        // 每页数
        user.setRows(rows);
        // 查询用户列表
        List<User> users = this.userDao.getUserList(user);
        // 查询用户列表总记录数
        Integer count = this.userDao.getUserListCount();
        // 创建Page返回对象
        Page<User> result = new Page<>();
        result.setPage(page);
        result.setRows(users);
        result.setSize(rows);
        result.setTotal(count);
        return result;
    }


    @Override
    public Map<String, Object> getUserInfoById(int u_id) {
        return this.userDao.getUserInfoById(u_id);
    }

    @Override
    public int createUserInfo(int u_id) {
        return this.userDao.createUserInfo(u_id);
    }
}
