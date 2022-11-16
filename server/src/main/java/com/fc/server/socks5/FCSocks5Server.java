package com.fc.server.socks5;

import com.fc.server.socks5.auth.PasswordAuth;
import com.fc.server.socks5.log.ProxyFlowLog4j;
import com.fc.server.socks5.s5.Socks5CommandRequestHandler;
import com.fc.server.socks5.s5.Socks5InitialRequestHandler;
import com.fc.server.socks5.s5.Socks5PasswordAuthRequestHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.socksx.v5.Socks5CommandRequestDecoder;
import io.netty.handler.codec.socksx.v5.Socks5InitialRequestDecoder;
import io.netty.handler.codec.socksx.v5.Socks5PasswordAuthRequestDecoder;
import io.netty.handler.codec.socksx.v5.Socks5ServerEncoder;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Slf4j
@Component
public class FCSocks5Server {

    @Value("${socks5.port}")
    private int port; // 设置服务端端口
    @Value("${socks5.isAuth}")
    public boolean isAuth; // 是否需要验证
    @Value("${socks5.username}")
    private String username; // 用户名
    @Value("${socks5.password}")
    private String password; // 密码

    private static EventLoopGroup boss = new NioEventLoopGroup(); // boss 线程组用于处理连接工作
    private static EventLoopGroup work = new NioEventLoopGroup(); // work 线程组用于数据处理
    private static ServerBootstrap bootstrap = new ServerBootstrap();

//    @Autowired
//    private FCSocks5Initializer mFCSocks5Initializer;


    @PostConstruct
    public void start(){
        try{

            log.info("Socks5服务端启动ing...");

            bootstrap.group(boss, work);
            bootstrap.channel(NioServerSocketChannel.class);
            bootstrap.option(ChannelOption.SO_BACKLOG, 1024);
            bootstrap.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 1000);

            //bootstrap.childHandler(mFCSocks5Initializer);

            bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                                       @Override
                                       protected void initChannel(SocketChannel ch) throws Exception {
                                           //流量统计
                                           ch.pipeline().addLast(
                                                   ProxyChannelTrafficShapingHandler.PROXY_TRAFFIC,
                                                   new ProxyChannelTrafficShapingHandler(3000, new ProxyFlowLog4j(), new ChannelListener() {
                                                       @Override
                                                       public void inActive(ChannelHandlerContext ctx) {
                                                           log.info("cid:{},{}",ctx.channel().id(),"inActive");
                                                       }

                                                       @Override
                                                       public void active(ChannelHandlerContext ctx) {
                                                           log.info("cid:{},{}",ctx.channel().id(),"active");
                                                       }
                                                   })
                                           );
                                           //channel超时处理
                                           ch.pipeline().addLast(new IdleStateHandler(3, 30, 0));
                                           ch.pipeline().addLast(new ProxyIdleHandler());
                                           //netty日志
                                           //if(logging) {
                                               ch.pipeline().addLast(new LoggingHandler());
                                           //}
                                           //Socks5MessagByteBuf
                                           ch.pipeline().addLast(Socks5ServerEncoder.DEFAULT);
                                           //sock5 init
                                           ch.pipeline().addLast(new Socks5InitialRequestDecoder());
                                           //sock5 init
                                           ch.pipeline().addLast(new Socks5InitialRequestHandler(FCSocks5Server.this));
                                           if(isAuth) {
                                               //socks auth
                                               ch.pipeline().addLast(new Socks5PasswordAuthRequestDecoder());
                                               //socks auth
                                               ch.pipeline().addLast(new Socks5PasswordAuthRequestHandler(new PasswordAuth() {
                                                   @Override
                                                   public boolean auth(String user, String pwd) {
                                                       log.info("user:{},pwd:{}",user,pwd);
                                                       if(user.equals(username) && password.equals(password)){
                                                           return true;
                                                       }
                                                       return false;
                                                   }
                                               }));
                                           }
                                           //socks connection
                                           ch.pipeline().addLast(new Socks5CommandRequestDecoder());
                                           //Socks connection
                                           ch.pipeline().addLast(new Socks5CommandRequestHandler(boss));
                                       }
                                   }

            );


            // 服务器绑定端口监听
            ChannelFuture future = bootstrap.bind(port).sync();
            // 监听服务器关闭监听
            //future.channel().closeFuture().sync();
            if(future.isSuccess()){
                log.info("socks5 服务端启动成功,url: {}://{}:{}:{}:{}","socks5","127.0.0.1",port,username,password );
            }
        }catch (InterruptedException e){
            log.error("eeee"+e.getMessage());
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void destroy() throws InterruptedException{
        work.shutdownGracefully().sync();
        boss.shutdownGracefully().sync();
        log.info("socks5 服务端启动成功,url: {}://{}:{}:{}:{}","socks5","127.0.0.1",port,username,password );
    }
}
