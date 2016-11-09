package com.lzx2005.service.impl;

import com.lzx2005.dao.ImageDao;
import com.lzx2005.dto.ServiceResult;
import com.lzx2005.entity.Image;
import com.lzx2005.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Administrator on 2016/9/2.
 */

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageDao imageDao;

    public ServiceResult<Image> addImage(String name, long size, String relativePath, String absolutePath, Date uploadTime) {
        Image image = new Image();
        image.setUploadTime(uploadTime);
        image.setSize(size);
        image.setRelativePath(relativePath);
        image.setAbsolutePath(absolutePath);
        image.setName(name);
        int success = imageDao.addImageReturnId(image);
        if(success==1){
            //添加成功
            return new ServiceResult<Image>(true, image);
        }else{
            return new ServiceResult<Image>(false, "添加失败");
        }
    }

    public ServiceResult<Image> queryById(long imageId) {
        Image image = imageDao.queryById(imageId);
        if(null==image){
            return new ServiceResult<Image>(false,"找不到该图像");
        }else{
            return new ServiceResult<Image>(true,image);
        }
    }
}
