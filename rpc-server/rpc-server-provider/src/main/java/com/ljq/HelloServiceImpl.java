package com.ljq;

public class HelloServiceImpl implements IHelloService {

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
