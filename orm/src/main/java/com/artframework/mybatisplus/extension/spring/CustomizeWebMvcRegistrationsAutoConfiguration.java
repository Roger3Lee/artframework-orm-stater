package com.artframework.mybatisplus.extension.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author li.pengcheng
 * @version V1.0
 * @date 2021/8/7
 **/
@Configuration
public class CustomizeWebMvcRegistrationsAutoConfiguration {

    @Bean
    public CustomizeWebMvcRegistrations customizeWebMvcRegistrations() {
        return new CustomizeWebMvcRegistrations();
    }

}
