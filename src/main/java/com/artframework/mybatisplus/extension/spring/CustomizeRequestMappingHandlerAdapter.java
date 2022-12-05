package com.artframework.mybatisplus.extension.spring;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.annotation.InitBinderDataBinderFactory;
import org.springframework.web.method.support.InvocableHandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.ServletRequestDataBinderFactory;

import java.util.List;

/**
 * @author li.pengcheng
 * @version V1.0
 * @date 2021/8/7
 **/
public class CustomizeRequestMappingHandlerAdapter extends RequestMappingHandlerAdapter {

    @Override
    protected InitBinderDataBinderFactory createDataBinderFactory(List<InvocableHandlerMethod> binderMethods) throws Exception {
        return new CustomizeServletRequestDataBinderFactory(binderMethods, getWebBindingInitializer());
    }

    private class CustomizeServletRequestDataBinderFactory extends ServletRequestDataBinderFactory {

        /**
         * Create a new instance.
         *
         * @param binderMethods one or more {@code @InitBinder} methods
         * @param initializer   provides global data binder initialization
         */
        public CustomizeServletRequestDataBinderFactory(List<InvocableHandlerMethod> binderMethods, WebBindingInitializer initializer) {
            super(binderMethods, initializer);
        }

        @Override
        protected ServletRequestDataBinder createBinderInstance(Object target, String objectName, NativeWebRequest request) {
            return new CustomizeExtendedServletRequestDataBinder(target, objectName);
        }
    }
}
