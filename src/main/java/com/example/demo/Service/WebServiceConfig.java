package com.example.demo.Service;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.xml.ws.Endpoint;

@Configuration
public class WebServiceConfig {

    @Bean
    public ServletRegistrationBean restServlet() {
        //注解扫描上下文
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        //项目包名
        applicationContext.scan("com.example.demo");
        DispatcherServlet rest_dispatcherServlet = new DispatcherServlet(applicationContext);
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(rest_dispatcherServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/*");
        return registrationBean;
    }

    @Bean
    public ServletRegistrationBean dispatcherServlet(){
        //发布服务名称
        return new ServletRegistrationBean(new CXFServlet(),"/service/*");
    }

    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus()
    {
        return  new SpringBus();
    }

    @Bean
    public UserService userService()
    {
        return  new UserServiceImpl();
    }

    @Bean
    public Endpoint endpoint() {
        //绑定要发布的服务
        EndpointImpl endpoint=new EndpointImpl(springBus(), userService());
        //显示要发布的名称
        endpoint.publish("/user");
        return endpoint;
    }
}
