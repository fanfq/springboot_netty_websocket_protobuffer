package com.fc.server.websocket;

import com.alibaba.fastjson.JSONObject;
import com.fc.common.socket.bean.FCHeader;
import com.fc.common.socket.bean.FCMsg;
import com.fc.common.socket.msg.KeepAliveMsg;
import com.fc.common.socket.msg.ProtoID;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @program: nettyprotobuf
 * @description:
 * @author: fangqing.fan#hotmail.com
 * @create: 2020-02-11 18:12
 **/

@Slf4j
@Component
public class FCWebSocketHandler extends SimpleChannelInboundHandler<FCMsg> {

    private static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);


    /**
     * 接收消息
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FCMsg msg) throws Exception {

        // 简单地打印出server接收到的消息
        //System.out.println("###"+ JSONObject.toJSONString(msg.getFcHeader()));
        //System.out.println("@@@"+new String(msg.getContent()));


        FCHeader fcHeader = msg.getFcHeader();
        byte[] body = msg.getContent();

        int nProtoID = fcHeader.getNProtoID();
        byte[] content;
        FCMsg fcMsg;

        log.info("recv nProtoID:{},sid:{},ip:{}",nProtoID,ctx.channel().id(),ctx.channel().remoteAddress());

        switch (nProtoID){
            case ProtoID.MsgType.PING_VALUE:
                //获取数据
                KeepAliveMsg.C2SMsg ping = KeepAliveMsg.C2SMsg.parseFrom(body);
                log.info("from ping ts:{}",ping.getC2S().getTimestamp());

                //构造body
                long ttl = System.currentTimeMillis();
                KeepAliveMsg.S2C.Builder s2c = KeepAliveMsg.S2C.newBuilder().setTimestamp(ttl);
                KeepAliveMsg.S2CMsg pong = KeepAliveMsg.S2CMsg.newBuilder().setCode(1).setMsg("ok").setS2C(s2c).build();

                //构造MSG
                nProtoID = ProtoID.MsgType.PONG_VALUE;
                content = pong.toByteArray();
                fcMsg = new FCMsg(nProtoID,content);

                //发送消息
                sendMsg(ctx.channel(),fcMsg);

                log.info("send nProtoID:{}, ts:{}",nProtoID,ttl);

                break;
            default:
                log.error("uncatch event ProtoID:{}",fcHeader.getNProtoID());
                break;
        }
    }

    /**
     * 绑定用户
     * @param userId
     * @param ctx
     */
    private void bind(long userId,ChannelHandlerContext ctx){
        FCWebSocketHolder.put(userId,ctx);
        channels.add(ctx.channel());
    }

    /**
     * 解绑用户
     * @param ctx
     */
    private void unbind(ChannelHandlerContext ctx){
        FCWebSocketHolder.remove(ctx);
        channels.remove(ctx.channel());
    }

    /**
     * 发送消息
     * @param channel
     * @param fcMsg
     */
    public void sendMsg(Channel channel,FCMsg fcMsg){
        //发送MSG
        if(channel.isActive()){
            channel.writeAndFlush(fcMsg);
        }
    }

    /**
     * 发送消息给指定用户
     * @param userId
     * @param fcMsg
     */
    public void sendMsgTo(long userId,FCMsg fcMsg){
        //获取socket通道
        Channel channel = FCWebSocketHolder.get(userId).channel();
        //发送MSG
        sendMsg(channel,fcMsg);
    }

    /**
     * 广播消息
     * @param fcMsg
     */
    public void broadcast(FCMsg fcMsg){
        if(!channels.isEmpty()) {
            channels.writeAndFlush(fcMsg);
        }
    }


    /**
     * 客户端建立连接成功
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("channelActive sid:{},ip:{}",ctx.channel().id(),ctx.channel().remoteAddress());

        Channel session = ctx.channel();
        //String uName = map.get(session.id());
        //log.info("用户:{},uid:{} 建立连接成功",uName,session.id());

        channels.add(ctx.channel());
    }

    /**
     * 客户端离线
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("channelInactive sid:{},ip:{}",ctx.channel().id(),ctx.channel().remoteAddress());

        //解绑用户
        unbind(ctx);

    }

    /**
     * 客户端异常
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        log.info("exceptionCaught sid:{},ip:{}",ctx.channel().id(),ctx.channel().remoteAddress());

        //Channel session = ctx.channel();
        //String uName = map.get(session.id());
        //log.info("用户:{},uid:{} 异常",uName,session.id());
        cause.printStackTrace();
        if(!ctx.isRemoved()){
            ctx.close();
        }
    }

    /**
     * 心跳检测 60 秒没有收到客户端的消息则自动断开连接
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        log.info("userEventTriggered sid:{},ip:{}",ctx.channel().id(),ctx.channel().remoteAddress());

        if (evt instanceof IdleStateEvent){
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt ;
            if (idleStateEvent.state() == IdleState.READER_IDLE){
                log.info("已经 60 秒没有收到信息！sid:{}",ctx.channel().id());
                ctx.close();
            }
        }
        super.userEventTriggered(ctx, evt);
    }



}
