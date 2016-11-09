package com.lzx2005.service;

import com.lzx2005.dto.ServiceResult;
import com.lzx2005.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2016/6/20.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-*.xml"})
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void createUser() throws Exception {
        ServiceResult<User> result = userService.createUser("lzx2005r", "e10adc3949ba59abbe56e057f20f883e", (short) 0);
        System.out.println(result);
    }

    @Test
    public void findAll() throws Exception {
        ServiceResult<List<User>> result = userService.findAll(1, 20);
        System.out.println(result);
    }

    @Test
    public void login() throws Exception {
        ServiceResult<User> result = userService.login("lzx20051", "e10adc3949ba59abbe56e057f20f883e");
        System.out.println(result);
    }

    @Test
    public void findUserById() throws Exception {
        ServiceResult<User> result = userService.findUserById(1000);
        System.out.println(result);
    }

    @Test
    public void findUserByUsername() throws Exception {
        ServiceResult<User> result = userService.findUserByUsername("lzx2005");
        System.out.println(result);
    }

}