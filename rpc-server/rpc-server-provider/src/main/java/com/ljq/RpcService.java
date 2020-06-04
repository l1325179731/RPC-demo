package com.ljq;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE) //修饰类或接口
@Retention(RetentionPolicy.RUNTIME)
@Component //被spring进行扫描
public @interface RpcService {
    Class<?> value();
}
