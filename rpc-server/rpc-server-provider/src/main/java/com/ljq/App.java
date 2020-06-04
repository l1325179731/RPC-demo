package com.ljq;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!000
 *
 */
public class App 
{
    public static void main( String[] args ) {
      /* RpcProxyServer rpcProxyServer=new RpcProxyServer(new HelloServiceImpl());
       rpcProxyServer.publisher(8080);*/
        ApplicationContext context=new AnnotationConfigApplicationContext(SpringConfig.class);
        System.out.println( context.getBeansWithAnnotation(RpcService.class));
        //( (AnnotationConfigApplicationContext)context).start();
    }
}
