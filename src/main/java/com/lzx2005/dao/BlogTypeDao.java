package com.lzx2005.dao;

import com.lzx2005.entity.BlogType;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.mapping.ResultMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Lizhengxian on 2017/2/17.
 */
public interface BlogTypeDao {

    public int insert(@Param("blogTypeName") String blogTypeName);

    public int delete(@Param("blogTypeId") long blogTypeId);

    public BlogType queryById(long blogTypeId);

    public List<BlogType> findAll();

    public List<BlogType> findAllTypeGroupByBlogs();

    public int update(BlogType blogType);
}
