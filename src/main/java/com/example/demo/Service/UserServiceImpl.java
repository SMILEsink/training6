package com.example.demo.Service;

import com.example.demo.dao.user_info;
import com.example.demo.mapper.user_infoMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.WebService;
import java.util.List;


@WebService(targetNamespace="http://Service.example.demo.com/",endpointInterface = "com.example.demo.Service.UserService")

public class UserServiceImpl implements UserService {

    @Autowired
    private user_infoMapper user_infoMapper;
    @Override
    public user_info getUser(String usercode) {
        user_info user= user_infoMapper.selectByUsercode(usercode);
        return user;
    }

    @Override
    public List<user_info> getAllUser() {
        List<user_info> user_infoList = user_infoMapper.selectAllUser();
        return user_infoList;
    }
}
