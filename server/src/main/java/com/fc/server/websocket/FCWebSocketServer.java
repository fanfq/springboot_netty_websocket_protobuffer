package com.fc.server.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @program: nettyprotobuf
 * @description:
 * @author: fangqing.fan#hotmail.com
 * @create: 2020-02-11 17:59
 **/
@Slf4j
@Component
public class FCWebSocketServer {

    @Value("${netty-websocket.protocol}")
    private String protocol; // 设置服务端端口
    @Value("${netty-websocket.port}")
    private int port; // 设置服务端端口
    @Value("${netty-websocket.host}")
    private String host; // 设置服务端端口
    @Value("${netty-websocket.path}")
    private String path; // 路径

    private static EventLoopGroup boss = new NioEventLoopGroup(); // boss 线程组用于处理连接工作
    private static EventLoopGroup work = new NioEventLoopGroup(); // work 线程组用于数据处理
    private static ServerBootstrap bootstrap = new ServerBootstrap();

    @Autowired
    private FCWebSocketInitializer mFCWebSocketInitializer;

    @PostConstruct
    public void start(){
        try {

            log.info("WebSocket服务端启动ing...");

            bootstrap.group(boss, work);
            bootstrap.channel(NioServerSocketChannel.class);
            bootstrap.localAddress(host,port);//端口绑定
            //bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);//长连接 设置TCP长连接,一般如果两个小时内没有数据的通信时,TCP会自动发送一个活动探测数据报文
            bootstrap.option(ChannelOption.SO_BACKLOG, 1024);//服务端可连接队列数,对应TCP/IP协议listen函数中backlog参数
            bootstrap.childOption(ChannelOption.TCP_NODELAY, true);//将小的数据包包装成更大的帧进行传送，提高网络的负载,即TCP延迟传输
            bootstrap.handler(new LoggingHandler(LogLevel.INFO));//日志
            //bootstrap.childHandler(mFCSocketInitializer); // 设置过滤器
            bootstrap.childHandler(mFCWebSocketInitializer); // 设置过滤器 websocket
            // 服务器绑定端口监听
            ChannelFuture future = bootstrap.bind().sync();
            // 监听服务器关闭监听
            //future.channel().closeFuture().sync();
            if(future.isSuccess()){
                log.info("WebSocket服务端启动成功,url: {}://{}:{}{}",protocol,host,port,path );
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void destroy() throws InterruptedException{
        work.shutdownGracefully().sync();
        boss.shutdownGracefully().sync();
        log.info("WebSocket服务端关闭,url: {}://{}:{}{}",protocol,host,port,path );
    }
}
