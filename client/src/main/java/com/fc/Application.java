package com.fc;

import com.fc.client.websocket.FCWebSocketClient;
import com.fc.common.socket.bean.FCMsg;
import com.fc.common.socket.msg.ProtoID;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @program: nettyprotobuf
 * @description:
 * @author: fangqing.fan#hotmail.com
 * @create: 2020-02-11 18:10
 **/

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages={"com.fc.*"})
public class Application {


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        FCWebSocketClient mFCWebSocketClient = new FCWebSocketClient();
        mFCWebSocketClient.connect("ws://localhost:9902/hall");
    }

//        public static void main(String[] args) {
//
//        //ApplicationContext context = SpringApplication.run(Application.class, args);
//
//        SpringApplication.run(Application.class, args);
//
//        EventLoopGroup group = new NioEventLoopGroup();
//        try {
//            Bootstrap b = new Bootstrap();
//            b.group(group)
//                    .channel(NioSocketChannel.class)
//                    .handler(new FCSocketInitializer());
//
//            // Start the connection attempt.
//            Channel ch = b.connect("localhost", 9876).sync().channel();
//
//            for(int i=0;i<100;i++){
//                //构造body
//                KeepAlive.C2S.Builder c2s = KeepAlive.C2S.newBuilder().setTimestamp(System.currentTimeMillis());
//                KeepAlive.Ping ping = KeepAlive.Ping.newBuilder().setC2S(c2s).build();
//
//                //构造MSG
//                int nProtoID = ProtoID.MsgType.PING_VALUE;
//                byte[] content = ping.toByteArray();
//                FCMsg fcMsg = new FCMsg(nProtoID,content);
//
//                //发送MSG
//                ch.writeAndFlush(fcMsg);
//
//                Thread.sleep(5000);
//            }
//
//
//            ch.close();
//
//        } catch (Exception ex){}finally {
//            group.shutdownGracefully();
//        }
//    }
}
