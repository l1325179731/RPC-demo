package com.ljq;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(basePackages = "com.ljq")
@Configuration
public class SpringConfig {
    @Bean(name = "LJQRpcServer")
    public LJQRpcServer LJQRpcServer(){
        return new LJQRpcServer(8080);
    }
}
