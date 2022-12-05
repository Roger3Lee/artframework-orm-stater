package com.artframework.mybatisplus.extension.byteB;

import java.lang.reflect.Modifier;
import java.util.Map;

import com.artframework.mybatisplus.extension.model.dataobject.BaseDO;
import com.artframework.mybatisplus.extension.model.dataobject.BaseDoInterceptor;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import org.springframework.cglib.core.TypeUtils;

/**
 * @author ajun
 */
public class DynamicClassUtils {

    public static Class<?> createBeanClass(final String className,
                                           Class<?> superClass, final Map<String, Class<?>> properties) {

        if (BaseDO.class.isAssignableFrom(superClass)) {
            TableName tableName = superClass.getAnnotation(TableName.class);
            DynamicType.Builder builder = new ByteBuddy()
                    .subclass(superClass)
                    .name(className);
            for (Map.Entry<String, Class<?>> entry : properties.entrySet()) {
                //设置GET方法
                builder = builder.defineField(entry.getKey(), entry.getValue(), Modifier.PRIVATE)
                        .annotateField(AnnotationDescription.Builder.ofType(TableField.class)
                                .define("value", entry.getKey()).build());

                builder = builder.defineMethod(getMethodName(entry.getKey()), entry.getValue(), Modifier.PUBLIC)
                        .intercept(MethodDelegation.to(BaseDoInterceptor.class));

                //设置SET方法
                builder = builder.defineMethod(setMethodName(entry.getKey()), Void.TYPE, Modifier.PUBLIC)
                        .withParameter(entry.getValue(), "a1")
                        .intercept(MethodDelegation.to(BaseDoInterceptor.class));
            }
            builder = builder.annotateType(tableName);
            Class<?> clazz= builder.make()
                    .load(superClass.getClassLoader(),ClassLoadingStrategy.Default.INJECTION)
                    .getLoaded();

            return clazz;
        }
        return superClass;
    }

    private static String getMethodName(String name) {
        return "get" + TypeUtils.upperFirst(name);
    }

    private static String setMethodName(String name) {
        return "set" + TypeUtils.upperFirst(name);
    }
}