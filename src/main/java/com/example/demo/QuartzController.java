package com.example.demo;

import com.example.demo.dao.user_info;
import com.example.demo.mapper.user_departmentMapper;
import com.example.demo.mapper.user_infoMapper;
import com.example.demo.mapper.user_stationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/quartz")
public class QuartzController{

    @Autowired
    public user_infoMapper user_infoMapper;

    @Autowired
    public user_departmentMapper user_departmentMapper;

    @Autowired
    public user_stationMapper user_stationMapper;


    //前台页面加载

    @RequestMapping(value = "/index")
    public String index(){
        return "index";
    }

    //人员信息查询
    @RequestMapping(value = "/userselect",method = RequestMethod.POST)
    @ResponseBody
    public user_info userselect(String usercode){
        user_info user_infos = new user_info();
        if(user_infoMapper.selectByUsercode(usercode)!=null){
            user_info user_info = user_infoMapper.selectByUsercode(usercode);
            user_info.setDpartmentCode(user_departmentMapper.selectByDepartmentCode(user_info.getDpartmentCode()).getDepartmentName());
            user_info.setStationCode(user_stationMapper.selectByStationCode(user_info.getStationCode()).getStationName());
            return user_info;
        }
        else {
            return user_infos;
        }
    }

    //人员信息根据时间查询
    @RequestMapping(value = "/selectByTime",method = RequestMethod.POST)

    public List<user_info> selectByTime(String begintTime,String endTime){

        // 参数map
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("begintTime ", begintTime );
        params.put("endTime ", endTime );
        // 根据所有pararms查询期间内xxx表数据
        List<user_info> user_infoList = user_infoMapper.selectByParams(params);
        return  user_infoList;
    }

//    <!--  查找所有-->
//  <select id="selectAllUser"  resultMap="BaseResultMap">
//    select * from user_info
//  </select>
//  <!--  //根据姓名查找-->
//  <select id="selectByName" parameterType="String" resultMap="BaseResultMap">
//    select
//            <include refid="Base_Column_List" />
//            from user_info
//    where username = #{username,jdbcType=INTEGER}
//  </select>
//<!--  //根据员工编码查找-->
//  <select id="selectByUsercode" parameterType="user_info" resultMap="BaseResultMap">
//    select
//            <include refid="Base_Column_List" />
//            from user_info
//    where usercode = #{usercode,jdbcType=INTEGER}
//  </select>
//<!--  //mybatis的xxxMapper.xml的Sql语句，CREATE_DATE是xxx表里面记录创建时间的字段-->
//  <select id="selectByParams" parameterType="java.util.Map" resultMap="BaseResultMap">
//    select *
//    from user_info
//    <where>
//      <if test="param.begintTime!=null and param.begintTime!=''">
//        <![CDATA[  and DATE_FORMAT(CREATE_DATE, '%Y-%m-%d %T:%i:%s') >= DATE_FORMAT(#{param.begintTime}, '%Y-%m-%d %T:%i:%s')   ]]>
//      </if>
//      <if test="param.endTime!=null and param.endTime!=''">
//        <![CDATA[  and DATE_FORMAT(CREATE_DATE, '%Y-%m-%d %T:%i:%s') <= DATE_FORMAT(#{param.endTime}, '%Y-%m-%d %T:%i:%s')   ]]>
//      </if>
//    </where>
//  </select>


}
