package com.practice.config;

import com.practice.intercepter.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
       // registry.addViewController("/").setViewName("tologin");
      //  registry.addViewController("/login.html").setViewName("login");
        //registry.addViewController("/main.html").setViewName("dashboard");
    }
    //将拦截器注册到SpringMVC配置类中
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册拦截器，及拦截的请求和剔除哪些请求
        //我们还需要过滤静态资源，否侧样式显示不出
        //注意

        Object o = Proxy.newProxyInstance(MyMvcConfig.class.getClassLoader(), MyMvcConfig.class.getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                
                method.invoke(proxy,args);

                return  proxy;
            }
        });
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**").
                excludePathPatterns("/tologin","/static/mute/**", "/login", "/","/toregister","/register","/Register.html");

    }
}
