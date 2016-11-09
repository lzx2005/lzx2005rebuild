package com.lzx2005.service;

import com.lzx2005.dto.ServiceResult;
import com.lzx2005.entity.Image;

import java.util.Date;

/**
 * Created by Administrator on 2016/9/2.
 */
public interface ImageService {

    public ServiceResult<Image> addImage(String name, long size, String relativePath, String absolutePath, Date uploadTime);

    public ServiceResult<Image> queryById(long imageId);
}
