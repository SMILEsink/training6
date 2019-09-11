package com.example.demo.Service;

import com.example.demo.dao.user_info;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface UserService {

    @WebMethod
    user_info getUser(String usercode);

    @WebMethod
    List<user_info> getAllUser();
}
