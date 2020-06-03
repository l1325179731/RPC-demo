package com.ljq;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RpcProxyServer {
    ExecutorService executorService= Executors.newCachedThreadPool();
    IHelloService service;

    public RpcProxyServer(IHelloService service) {
        this.service = service;
    }

    public void publisher(int port){
        ServerSocket serverSocket=null;
        try {
            serverSocket=new ServerSocket(port);
            while (true){// 不断接受请求
                Socket socket=serverSocket.accept();
                //每一个socket交给一个processorHandler处理
                executorService.execute(new ProcessorHandler(socket,service));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
             if(serverSocket!=null){
                 try {
                     serverSocket.close();
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
             }
        }
    }
}
