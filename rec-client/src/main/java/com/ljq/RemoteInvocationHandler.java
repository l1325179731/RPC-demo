package com.ljq;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class RemoteInvocationHandler implements InvocationHandler {
    private String host;
    private int port;

    public RemoteInvocationHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 代理了之后就可以拿到调用方法的所有参数
        RpcRequest rpcRequest=new RpcRequest();
        rpcRequest.setClassName(method.getDeclaringClass().getName());
        rpcRequest.setMethodName(method.getName());
        rpcRequest.setParameters(args);
        RpcNetTransport rpcNetTransport=new RpcNetTransport(host,port);
        Object object=rpcNetTransport.send(rpcRequest);
        return object;
    }
}
