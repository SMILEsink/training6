package com.example.demo.mapper;

import com.example.demo.dao.user_department;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface user_departmentMapper {
    int deleteByPrimaryKey(Integer id);
    int insert(user_department record);

    int insertSelective(user_department record);
    //自动生成 不能为空


    user_department selectByPrimaryKey(Integer id);

    user_department selectByDepartmentCode(String department_code);

    int updateByPrimaryKeySelective(user_department record);

    int updateByPrimaryKey(user_department record);
}