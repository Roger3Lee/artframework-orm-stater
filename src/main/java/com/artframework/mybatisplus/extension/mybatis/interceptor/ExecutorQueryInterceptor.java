package com.artframework.mybatisplus.extension.mybatis.interceptor;

import com.artframework.mybatisplus.extension.copier.BeanCopierUtil;
import com.artframework.mybatisplus.extension.model.dataobject.BaseDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

/**
 * @author li.pengcheng
 * @version V1.0
 * @date 2022/11/18
 **/
@Intercepts(value = {@Signature(type = Executor.class,  method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
@Slf4j
public class ExecutorQueryInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        if (null != invocation.getArgs() && invocation.getArgs().length > 0) {
            for (int i = 0; i < invocation.getArgs().length; i++) {
                Object argument = invocation.getArgs()[i];
                if (null != argument && BaseDO.class.isAssignableFrom(argument.getClass())) {
                    try {
                        String proxyName = argument.getClass().getName() + "Proxy";
                        Class<?> superClazz = Class.forName(proxyName);
                        Object targetArgument = BeanCopierUtil.copy(argument, superClazz);
                        invocation.getArgs()[i] = targetArgument;
                    } catch (Exception ex) {
                        log.error("error:", ex);
                    }
                }
            }
        }
        return invocation.proceed();
    }
}