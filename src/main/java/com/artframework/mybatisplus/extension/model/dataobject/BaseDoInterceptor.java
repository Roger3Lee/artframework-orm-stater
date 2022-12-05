package com.artframework.mybatisplus.extension.model.dataobject;

import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.Locale;

/**
 * 用戶生成代理對象的数据库属性的代理類攔截器
 *
 * @author li.pengcheng
 * @version V1.0
 * @date 2022/11/18
 **/
@Slf4j
public class BaseDoInterceptor {
    @RuntimeType
    public static Object intercept(@This Object thisObj,
                                   @Origin Method method) {
        String field = method.getName().replace("get", "");
        field = field.substring(0, 1).toLowerCase(Locale.ROOT) + field.substring(1);

        Object value = ((BaseDO) thisObj).getFeatureValue(field);
        log.debug("get field:{},value is:{}", field, value);
        return value;
    }

    @RuntimeType
    public static void intercept(@Argument(0) Object value,
                                 @This Object thisObj,
                                 @Origin Method method) {

        String field = method.getName().replace("set", "");
        field = field.substring(0, 1).toLowerCase(Locale.ROOT) + field.substring(1);
        log.debug("set field {},value is:{}", field, value);
        ((BaseDO) thisObj).setFeatureValue(field, value);
    }
}
