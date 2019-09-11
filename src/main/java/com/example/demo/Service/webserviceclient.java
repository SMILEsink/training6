package com.example.demo.Service;

import com.example.demo.dao.user_info;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import java.util.List;


public class webserviceclient {

//    //动态调用
//    public static void main(String[] args) throws Exception {
//        JaxWsDynamicClientFactory dcflient=JaxWsDynamicClientFactory.newInstance();
//
//        Client client=dcflient.createClient("http://localhost:8080/service/user?wsdl");
//
//        Object[] objects=client.invoke("getUser","YN20190360");
//        System.out.println("*******"+objects[0].toString());
//
//        Object[] objectall=client.invoke("getAllUser");
//        System.out.println("*******"+objectall[0].toString());
//
//        main3(args);
//    }
//
//
    //调用方式二，通过接口协议获取数据类型
    public static void main(String[] args) throws Exception {
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean=new JaxWsProxyFactoryBean();
        jaxWsProxyFactoryBean.setAddress("http://localhost:8080/service/user?wsdl");
        jaxWsProxyFactoryBean.setServiceClass(UserService.class);

        UserService userService=(UserService)jaxWsProxyFactoryBean.create();
        user_info userResult= userService.getUser("YN20190360");
        System.out.println("===========================================");
        System.out.println("UserName:"+userResult.getUsername());
        System.out.println("===========================================");
        List<user_info> users=userService.getAllUser();
        System.out.println(users);

    }


//    //调用方式三，通过接口协议获取数据类型,设置链接超时和响应时间
//    public static void main(String[] args) throws Exception {
//        JaxWsProxyFactoryBean jaxWsProxyFactoryBean=new JaxWsProxyFactoryBean();
//        jaxWsProxyFactoryBean.setAddress("http://localhost:8080/service/user?wsdl");
//        jaxWsProxyFactoryBean.setServiceClass(UserService.class);
//
//        UserService userService = (UserService) jaxWsProxyFactoryBean.create(); // 创建客户端对象
//        Client proxy= ClientProxy.getClient(userService);
//        HTTPConduit conduit=(HTTPConduit)proxy.getConduit();
//        HTTPClientPolicy policy=new HTTPClientPolicy();
//        policy.setConnectionTimeout(1000);
//        policy.setReceiveTimeout(1000);
//        conduit.setClient(policy);
//
//        user_info userResult= userService.getUser("YN20190360");
//        System.out.println("UserName:"+userResult.getUsername());
//        List<user_info> users=userService.getAllUser();
//
//    }
}
