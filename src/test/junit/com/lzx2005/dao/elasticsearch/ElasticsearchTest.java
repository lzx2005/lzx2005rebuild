package com.lzx2005.dao.elasticsearch;

import com.lzx2005.dao.elasticsearch.repository.UserRepository;
import com.lzx2005.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Administrator on 2016/10/24 0024.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-data-elasticsearch.xml"})
public class ElasticsearchTest {

    @Autowired
    private UserRepository repository;

    @Test
    public void doSomething(){
        /*long documentId = 123456;
        User user = new User();
        user.setUsername("John");
        user.setPassword("password");*/
        User user = repository.findByUsername("lzx2005");
        System.out.println(user);
    }
}
