package com.fc.server.socks5;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProxyIdleHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {

        log.info("userEventTriggered sid:{},ip:{}",ctx.channel().id(),ctx.channel().remoteAddress());

//        if (evt instanceof IdleStateEvent){
//            IdleStateEvent idleStateEvent = (IdleStateEvent) evt ;
//            if (idleStateEvent.state() == IdleState.READER_IDLE){
//                log.info("已经 60 秒没有收到信息！sid:{}",ctx.channel().id());
//                ctx.close();
//            }
//        }
//        super.userEventTriggered(ctx, evt);

        if (evt instanceof IdleStateEvent) {
            ctx.channel().close();
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

}
