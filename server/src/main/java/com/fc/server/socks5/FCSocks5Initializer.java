package com.fc.server.socks5;

import com.fc.server.socks5.s5.Socks5CommandRequestHandler;
import com.fc.server.socks5.s5.Socks5InitialRequestHandler;
import com.fc.server.socks5.s5.Socks5PasswordAuthRequestHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.socksx.v5.Socks5CommandRequestDecoder;
import io.netty.handler.codec.socksx.v5.Socks5InitialRequestDecoder;
import io.netty.handler.codec.socksx.v5.Socks5PasswordAuthRequestDecoder;
import io.netty.handler.codec.socksx.v5.Socks5ServerEncoder;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FCSocks5Initializer  extends ChannelInitializer<SocketChannel> {

    @Value("${socks5.port}")
    private int port; // 设置服务端端口
    @Value("${socks5.isAuth}")
    private boolean isAuth; // 是否需要验证
    @Value("socks5.username}")
    private String username; // 用户名
    @Value("${socks5.password}")
    private String password; // 密码

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {

//        ChannelPipeline pipeline = socketChannel.pipeline();
//
//        //流量统计
//        pipeline.addLast(
//                ProxyChannelTrafficShapingHandler.PROXY_TRAFFIC,
//                new ProxyChannelTrafficShapingHandler(3000, proxyFlowLog, channelListener)
//        );
//        //channel超时处理
//        pipeline.addLast(new IdleStateHandler(3, 30, 0));
//        pipeline.addLast(new ProxyIdleHandler());
//        //netty日志
//        //if(logging) {
//            pipeline.addLast(new LoggingHandler());
//        //}
//        //Socks5MessagByteBuf
//        pipeline.addLast(Socks5ServerEncoder.DEFAULT);
//        //sock5 init
//        pipeline.addLast(new Socks5InitialRequestDecoder());
//        //sock5 init
//        pipeline.addLast(new Socks5InitialRequestHandler(ProxyServer.this));
//        if(isAuth) {
//            //socks auth
//            pipeline.addLast(new Socks5PasswordAuthRequestDecoder());
//            //socks auth
//            pipeline.addLast(new Socks5PasswordAuthRequestHandler(getPasswordAuth()));
//        }
//        //socks connection
//        pipeline.addLast(new Socks5CommandRequestDecoder());
//        //Socks connection
//        pipeline.addLast(new Socks5CommandRequestHandler(ProxyServer.this.getBossGroup()));

    }
}
