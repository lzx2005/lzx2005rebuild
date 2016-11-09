package com.lzx2005.service.impl;

import com.lzx2005.dao.UserDao;
import com.lzx2005.dto.ServiceResult;
import com.lzx2005.entity.User;
import com.lzx2005.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/6/20.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public ServiceResult<User> createUser(String username, String password, short userType) {
        int count = userDao.insertUser(username, password, userType);

        ServiceResult<User> result = null;
        if(count==1){
            //插入用户成功
            User user = null;
            result = new ServiceResult<User>(true,user);
        }else{
            //插入用户失败
            result = new ServiceResult<User>(false,"insert failed");
        }
        return result;
    }

    public ServiceResult<List<User>> findAll(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        int limit = pageSize;
        List<User> users = userDao.findAll(offset, limit);
        ServiceResult<List<User>> result = new ServiceResult<List<User>>(true,users);
        return result;
    }

    public ServiceResult<User> findUserById(long userId) {
        User user = userDao.queryById(userId);
        ServiceResult<User> result = null;
        if(user==null){
            result = new ServiceResult<User>(false, "can't find user");
        }else{
            result = new ServiceResult<User>(true,user);
        }
        return result;
    }

    public ServiceResult<User> login(String username, String password) {
        User user = userDao.login(username, password);
        ServiceResult<User> result = null;
        if(user==null){
            //登陆不成功
            result = new ServiceResult<User>(false,"wrong username or password");
        }else{
            //登陆成功
            user.setPassword("-1");
            result = new ServiceResult<User>(true,user);
        }

        return result;
    }

    public ServiceResult<User> findUserByUsername(String username) {
        User user = userDao.findByUsername(username);
        ServiceResult<User> result = null;
        if(user==null){
            //登陆不成功
            result = new ServiceResult<User>(false,"wrong username or password");
        }else{
            //登陆成功
            result = new ServiceResult<User>(true,user);
        }
        return result;
    }
}
