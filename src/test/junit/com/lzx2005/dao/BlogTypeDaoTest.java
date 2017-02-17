package com.lzx2005.dao;

import com.lzx2005.entity.BlogType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Lizhengxian on 2017/2/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class BlogTypeDaoTest {

    @Autowired
    BlogTypeDao blogTypeDao;


    @Test
    public void insert() throws Exception {
        int successCount = blogTypeDao.insert("搭建");
        System.out.println(successCount);
    }

    @Test
    public void queryById() throws Exception {
        BlogType blogType = blogTypeDao.queryById(10010L);
        System.out.println(blogType);
    }


    @Test
    public void findAll() throws Exception{
        List<BlogType> all = blogTypeDao.findAll();
        for(BlogType bt : all){
            System.out.println(bt);
        }
    }

}