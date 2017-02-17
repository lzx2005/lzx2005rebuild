package com.lzx2005.dao;

import com.lzx2005.entity.BlogType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Lizhengxian on 2017/2/17.
 */
public interface BlogTypeDao {

    public int insert(@Param("blogTypeName") String blogTypeName);

    public BlogType queryById(long blogTypeId);

    public List<BlogType> findAll();
}
