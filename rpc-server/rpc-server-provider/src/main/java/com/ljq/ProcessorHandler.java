package com.ljq;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

public class ProcessorHandler implements Runnable{
    private Socket socket;
    private IHelloService service;

    public ProcessorHandler(Socket socket, IHelloService service) {
        this.socket = socket;
        this.service = service;
    }

    @Override
    public void run() {
        ObjectInputStream objectInputStream=null;
        ObjectOutputStream objectOutputStream=null;
        try {
            objectInputStream=new ObjectInputStream(socket.getInputStream());
            /**
             * 输入流中有
             * 1. 请求哪个类？
             * 2. 请求方法名称？
             * 3. 请求参数？
             * 定义一个对象来包装
             */
            RpcRequest rpcRequest= (RpcRequest) objectInputStream.readObject();
            Object result=invoke(rpcRequest);
            objectOutputStream=new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(result);
            objectOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(objectInputStream!=null) {
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(objectOutputStream!=null){
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public Object invoke(RpcRequest request){
        Object[] args=request.getParameters();
        Class<?>[] types=new Class[args.length];// 请求参数的类型
        for (int i = 0; i < args.length; i++) {
            types[i]=args[i].getClass();
        }
        try {
            Class clazz=Class.forName(request.getClassName());
            Method method=clazz.getMethod(request.getMethodName(),types); //根据方法的名称和参数的类型找到对应的方法
            Object result=method.invoke(service,args);
            return result;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
