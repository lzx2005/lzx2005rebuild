package com.lzx2005.service;

import com.lzx2005.dto.ServiceResult;
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
@ContextConfiguration({"classpath:spring/spring-*.xml"})
public class ImageServiceTest {

    @Autowired
    private ImageService imageService;

    @Test
    public void addImage() throws Exception {
        ServiceResult<Image> imageServiceResult = imageService.addImage("1", 20000, "/resource/image/sssss.jpg", "/tomcat/webapps/ROOT/resource/image/sssss.jpg", new Date());
        System.err.println(imageServiceResult);

        ServiceResult<Image> imageServiceResult1 = imageService.queryById(imageServiceResult.getData().getImageId());
        System.err.println(imageServiceResult1);

    }

    @Test
    public void queryById() throws Exception {
        ServiceResult<Image> imageServiceResult = imageService.queryById(4);
        System.err.println(imageServiceResult);
    }

}