package com.ljq;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    @Bean(name = "rpcProxyClient")
    public RpcProxyClient proxyClient(){
        return new RpcProxyClient();
    }
}
