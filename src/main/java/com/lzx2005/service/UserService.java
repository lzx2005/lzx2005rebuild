package com.lzx2005.service;

import com.lzx2005.dto.ServiceResult;
import com.lzx2005.entity.User;

import java.util.List;

/**
 * Created by Administrator on 2016/6/20.
 */
public interface UserService {
    public ServiceResult<User> createUser(String username, String password, short userType);

    public ServiceResult<List<User>> findAll(int page,int pageSize);

    public ServiceResult<User> login(String username,String password);

    public ServiceResult<User> findUserById(long userId);

    public ServiceResult<User> findUserByUsername(String username);

}
