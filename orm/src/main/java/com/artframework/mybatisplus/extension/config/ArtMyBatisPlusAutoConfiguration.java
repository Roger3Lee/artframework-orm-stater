package com.artframework.mybatisplus.extension.config;

import com.artframework.mybatisplus.extension.mybatis.injector.ArtSqlInjector;
import com.artframework.mybatisplus.extension.mybatis.interceptor.ExecutorQueryInterceptor;
import com.artframework.mybatisplus.extension.mybatis.interceptor.ExecutorUpdateInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author li.pengcheng
 * @version V1.0
 * @date 2021/8/13
 **/
@Configuration
@ComponentScan(basePackages = "com.artframework.mybatisplus.extension")
public class ArtMyBatisPlusAutoConfiguration {

    @Bean
    public ArtSqlInjector sqlInjector() {
        return new ArtSqlInjector();
    }


    @Bean
    public Interceptor ExecutorQueryInterceptor(){
      return   new ExecutorQueryInterceptor();
    }

    @Bean
    public Interceptor ExecutorUpdateInterceptor(){
        return   new ExecutorUpdateInterceptor();
    }
}
