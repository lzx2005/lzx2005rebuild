package com.lzx2005.dao;

import com.lzx2005.entity.Image;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2016/9/2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class ImageDaoTest {
    @Autowired
    ImageDao imageDao;

    @Test
    public void addImage() throws Exception {
        int result = imageDao.addImage("1","/resource/image/sssss.jpg","/tomcat/webapps/ROOT/resource/image/sssss.jpg",20000,new Date());
        System.out.println(result);

        Image image = new Image();
        image.setAbsolutePath("/tomcat/webapps/ROOT/resource/image/sssss.jpg");
        image.setName("2");
        image.setRelativePath("/resource/image/sssss.jpg");
        image.setSize(5000);
        image.setUploadTime(new Date());
        long id = imageDao.addImageReturnId(image);
        System.out.println(image.getImageId());
    }

    @Test
    public void queryById() throws Exception {
        Image image = imageDao.queryById(10001);
        System.out.println(image);
    }

}