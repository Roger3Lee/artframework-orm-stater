package com.artframework.mybatisplus.extension.spring;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

/**
 * @author li.pengcheng
 * @version V1.0
 * @date 2021/8/7
 **/
public class CustomizeWebMvcRegistrations implements WebMvcRegistrations {

    @Override
    public RequestMappingHandlerAdapter getRequestMappingHandlerAdapter() {
        return new CustomizeRequestMappingHandlerAdapter();
    }
}
