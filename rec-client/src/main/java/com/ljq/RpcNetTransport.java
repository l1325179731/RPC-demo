package com.ljq;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RpcNetTransport {
    private String host;
    private int port;

    public RpcNetTransport(String host, int port) {
        this.host = host;
        this.port = port;
    }
    public Object send(RpcRequest rpcRequest){
        Socket socket=null;
        Object result=null;
        ObjectOutputStream objectOutputStream=null;
        ObjectInputStream objectInputStream=null;
        try {
            socket=new Socket(host,port); //建立连接
            objectOutputStream=new ObjectOutputStream(socket.getOutputStream()); //网络流
            objectOutputStream.writeObject(rpcRequest);
            objectOutputStream.flush();
            objectInputStream =new ObjectInputStream(socket.getInputStream());
            result=objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            if(objectOutputStream!=null) {
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(objectInputStream!=null) {
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
