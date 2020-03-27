package com.fc.server.websocket;

import com.fc.common.socket.bean.FCWebsocketDecoder;
import com.fc.common.socket.bean.FCWebsocketEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @program: nettyprotobuf
 * @description:
 * @author: fangqing.fan#hotmail.com
 * @create: 2020-02-11 18:11
 **/

@Component
public class FCWebSocketInitializer extends ChannelInitializer<SocketChannel> {

    @Value("${netty-websocket.path}")
    private String path; // 路径

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();

        /*
        //阿里云证书与此处配置不同，类型为PKCS12
        SSLContext sslContext = SslUtil.createSSLContext("JKS","/root/STAR.***.com_tomcat.jks","***");
        //SSLEngine 此类允许使用ssl安全套接层协议进行安全通信
        SSLEngine engine = sslContext.createSSLEngine();
        engine.setUseClientMode(false);
        //SslHandler应当放在首位
        channel.pipeline().addLast(new SslHandler(engine));
        */

        // 60s 没有收到客户端消息
        pipeline.addLast(new IdleStateHandler(60, 0, 0, TimeUnit.SECONDS));

        //websocket协议本身是基于http协议的，所以这边也要使用http解编码器
        pipeline.addLast(new HttpServerCodec());
        //以块的方式来写的处理器
        pipeline.addLast(new ChunkedWriteHandler());
        //netty是基于分段请求的，HttpObjectAggregator的作用是将请求分段再聚合,参数是聚合字节的最大长度
        pipeline.addLast(new HttpObjectAggregator(1024*1024*1024));

        //ws://server:port/context_path
        //ws://localhost:9999/ws
        //参数指的是contex_path
        pipeline.addLast(new WebSocketServerProtocolHandler(path,null,true,65535));

        pipeline.addLast(new FCWebsocketEncoder());
        pipeline.addLast(new FCWebsocketDecoder());

        //websocket定义了传递数据的6中frame类型
        pipeline.addLast(new FCWebSocketHandler());

    }
}
