package com.ljq;

/**
 * Hello world!000
 *
 */
public class App 
{
    public static void main( String[] args ) {
       RpcProxyServer rpcProxyServer=new RpcProxyServer(new HelloServiceImpl());
       rpcProxyServer.publisher(8080);
    }
}
