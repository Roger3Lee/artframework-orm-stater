package com.artframework.mybatisplus.extension.annotations;

import com.artframework.mybatisplus.extension.config.ArtMyBatisPlusAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author li.pengcheng
 * @version V1.0
 * @date 2022/11/21
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(value = ArtMyBatisPlusAutoConfiguration.class)
public @interface EnableORMExtension {
}
