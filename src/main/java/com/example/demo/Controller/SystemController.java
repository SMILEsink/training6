package com.example.demo.Controller;

import com.example.demo.Service.UserService;
import com.example.demo.dao.user_info;
import com.example.demo.mapper.user_departmentMapper;
import com.example.demo.mapper.user_infoMapper;
import com.example.demo.mapper.user_stationMapper;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class SystemController {

    @Autowired
    private user_infoMapper user_infoMapper;

    @Autowired
    private user_departmentMapper user_departmentMapper;
    //前面是map过来的，后面是自己实例化，注解作用
    @Autowired
    private user_stationMapper user_stationMapper;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public String login(HttpServletRequest request){
        String msg="登录失败";
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("username"+username+"password"+password);
        if(user_infoMapper.selectByName(username)!=null&user_infoMapper.selectByName(username).getPassword().equals(password)){
            msg = "success";
        }
        return msg;
    }

    @RequestMapping(value = "/userInfo",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin

    public List<user_info> userInfo(){
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean=new JaxWsProxyFactoryBean();
        jaxWsProxyFactoryBean.setAddress("http://localhost:8080/service/user?wsdl");
        jaxWsProxyFactoryBean.setServiceClass(UserService.class);

        UserService userService=(UserService)jaxWsProxyFactoryBean.create();
        List<user_info> users=userService.getAllUser();

        for (int i = 0; i <users.size() ; i++) {
            users.get(i).setStationCode(user_stationMapper.selectByStationCode(users.get(i).getStationCode()).getStationName());
            users.get(i).setDpartmentCode(user_departmentMapper.selectByDepartmentCode(users.get(i).getDpartmentCode()).getDepartmentName());
        }
        System.out.println(users);
        return users;
    }

}
