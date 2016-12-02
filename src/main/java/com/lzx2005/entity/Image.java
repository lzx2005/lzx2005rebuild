package com.lzx2005.entity;


import java.util.Date;

/**
 * Created by Administrator on 2016/9/2.
 */
public class Image {

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
        if (uploadTime == null) {
            return null;
        }
        return (Date) uploadTime.clone();
    }

    public void setUploadTime(Date uploadTime) {
        if (uploadTime == null) {
            this.uploadTime = null;
        } else {
            this.uploadTime = (Date) uploadTime.clone();
        }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Image image = (Image) o;

        if (imageId != image.imageId) return false;
        if (size != image.size) return false;
        if (name != null ? !name.equals(image.name) : image.name != null) return false;
        if (relativePath != null ? !relativePath.equals(image.relativePath) : image.relativePath != null) return false;
        if (absolutePath != null ? !absolutePath.equals(image.absolutePath) : image.absolutePath != null) return false;
        return uploadTime != null ? uploadTime.equals(image.uploadTime) : image.uploadTime == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (imageId ^ (imageId >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (int) (size ^ (size >>> 32));
        result = 31 * result + (relativePath != null ? relativePath.hashCode() : 0);
        result = 31 * result + (absolutePath != null ? absolutePath.hashCode() : 0);
        result = 31 * result + (uploadTime != null ? uploadTime.hashCode() : 0);
        return result;
    }
}