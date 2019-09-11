package com.example.demo.mapper;

import com.example.demo.dao.user_station;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface user_stationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(user_station record);

    int insertSelective(user_station record);

    user_station selectByPrimaryKey(Integer id);

    user_station selectByStationCode(String station_code);

    int updateByPrimaryKeySelective(user_station record);

    int updateByPrimaryKey(user_station record);
}