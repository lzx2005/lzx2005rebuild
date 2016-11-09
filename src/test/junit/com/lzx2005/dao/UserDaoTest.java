package com.lzx2005.dao;

import com.lzx2005.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2016/6/19.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class UserDaoTest {

    @Autowired
    UserDao userDao;

    @Test
    public void insertUser() throws Exception {
            int result = userDao.insertUser("lzx2005","e10adc3949ba59abbe56e057f20f883e", (short) 0);
    }

    @Test
    public void queryById() throws Exception {
        User user = userDao.queryById(1081);
        System.out.println(user);
    }

    @Test
    public void findAll() throws Exception {
        List<User> users = userDao.findAll(0,20);
        for (User user : users){
            System.out.println(user);
        }
    }

}