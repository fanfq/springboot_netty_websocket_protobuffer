package com.fc.server.socks5.s5;

import com.fc.server.socks5.FCSocks5Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.socksx.SocksVersion;
import io.netty.handler.codec.socksx.v5.DefaultSocks5InitialRequest;
import io.netty.handler.codec.socksx.v5.DefaultSocks5InitialResponse;
import io.netty.handler.codec.socksx.v5.Socks5AuthMethod;
import io.netty.handler.codec.socksx.v5.Socks5InitialResponse;

public class Socks5InitialRequestHandler extends SimpleChannelInboundHandler<DefaultSocks5InitialRequest> {

    private static final Logger logger = LoggerFactory.getLogger(Socks5InitialRequestHandler.class);

    private FCSocks5Server proxyServer;

    public Socks5InitialRequestHandler(FCSocks5Server proxyServer) {
        this.proxyServer = proxyServer;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DefaultSocks5InitialRequest msg) throws Exception {
        logger.info("初始化ss5连接 : " + msg);
        if(msg.decoderResult().isFailure()) {
            logger.info("不是ss5协议");
            ctx.fireChannelRead(msg);
        } else {
            if(msg.version().equals(SocksVersion.SOCKS5)) {
                if(proxyServer.isAuth) {
                    Socks5InitialResponse initialResponse = new DefaultSocks5InitialResponse(Socks5AuthMethod.PASSWORD);
                    ctx.writeAndFlush(initialResponse);
                } else {
                    Socks5InitialResponse initialResponse = new DefaultSocks5InitialResponse(Socks5AuthMethod.NO_AUTH);
                    ctx.writeAndFlush(initialResponse);
                }
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error("zzz");
    }
}
