package com.lzx2005.dao;

import com.lzx2005.entity.Image;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * Created by Administrator on 2016/9/2.
 */
public interface ImageDao {

    public int addImage(@Param("name") String name,
                        @Param("relativePath") String relativePath,
                        @Param("absolutePath") String absolutePath,
                        @Param("size") long size,
                        @Param("uploadTime") Date uploadTime);

    public int addImageReturnId(Image image);

    public Image queryById(long imageId);

}
