package com.lzx2005.entity;

import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by Administrator on 2016/9/2.
 */
public class Image {

    @Id
    private long imageId;
    private String name;
    private long size;
    private String relativePath;
    private String absolutePath;
    private Date uploadTime;

    public long getImageId() {
        return imageId;
    }

    public void setImageId(long imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getRelativePath() {
        return relativePath;
    }

    public void setRelativePath(String relativePath) {
        this.relativePath = relativePath;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    @Override
    public String toString() {
        return "Image{" +
                "imageId=" + imageId +
                ", name='" + name + '\'' +
                ", size=" + size +
                ", relativePath='" + relativePath + '\'' +
                ", absolutePath='" + absolutePath + '\'' +
                ", uploadTime=" + uploadTime +
                '}';
    }
}