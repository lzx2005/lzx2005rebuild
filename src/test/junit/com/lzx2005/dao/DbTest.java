package com.lzx2005.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * Created by Administrator on 2016/11/2 0002.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class DbTest {

    @Autowired
    private Db db;

    @Test
    public void countBlog() throws Exception {
        long count = db.countBlog();
        System.out.println(count);
    }

}