package com.lzx2005.dao;

import com.lzx2005.dto.PageResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2016/11/2 0002.
 */
public interface Db {
    public long countBlog();

    public List paginate(@Param("page") int page,@Param("pageSize") int pageSize,@Param("tableName") String tableName,@Param("params") String params);
}
