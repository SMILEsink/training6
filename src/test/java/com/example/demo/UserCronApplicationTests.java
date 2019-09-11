package com.example.demo;

import com.example.demo.dao.user_info;
import com.example.demo.mapper.user_infoMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserCronApplicationTests {

    @Autowired
    private user_infoMapper user_infoMapper;
    @Test
    public void userTest(){
        List<user_info> user_infoList = user_infoMapper.selectAllUser();
        System.out.println(user_infoList);
    }

}



