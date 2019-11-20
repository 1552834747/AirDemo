package com.shardingjdbc.dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface TestDao {


    @Select("select * from user")
    List<Map<String,Object>> list();

}
