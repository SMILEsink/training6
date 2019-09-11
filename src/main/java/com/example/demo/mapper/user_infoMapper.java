package com.example.demo.mapper;

import com.example.demo.dao.user_info;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface user_infoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(user_info user_info);

    int insertSelective(user_info record);

    user_info selectByPrimaryKey(Integer id);

    List<user_info> selectAllUser();

    List<user_info> selectByParams(@Param("param") Map<String, Object> params);

    user_info selectByUsercode(String usercode);

    user_info selectByName(String name);

    int updateByPrimaryKeySelective(user_info record);

    int updateByPrimaryKey(user_info record);
}