package com.ljq;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@RpcService(IHelloService.class)
public class HelloServiceImpl implements IHelloService {
   /* static {
        System.out.println("aaaaaaa");
    }*/

    @Override
    public String sayHello(String content) {
        System.out.println("request in sayHello:" + content);
        return "sayHello" + content;
    }

    @Override
    public String saveUser(User user) {
        System.out.println("request in saveUser:" + user);
        return "SUCCESS";
    }
}
