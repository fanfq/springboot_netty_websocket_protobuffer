package com.fc.server.websocket;

import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: nettyprotobuf
 * @description:
 * @author: fangqing.fan#hotmail.com
 * @create: 2020-02-12 13:58
 **/

@Slf4j
public class FCWebSocketHolder {

    private static final Map<Long, ChannelHandlerContext> MAP = new ConcurrentHashMap<>(16);
    private static final Map<ChannelHandlerContext, Long> USERS = new ConcurrentHashMap<>(16);

    public static void put(Long userId, ChannelHandlerContext ctx) {
        MAP.put(userId, ctx);
        log.info("sss#"+ctx.channel().id());
        USERS.put(ctx,userId);
    }
    public static ChannelHandlerContext get(Long userId) {
        return MAP.get(userId);
    }
    public static long get(ChannelHandlerContext ctx){
        log.info("sss##"+ctx.channel().id());
        long userId = -1;
        try{
            userId = USERS.get(ctx);
        }catch (Exception e){

        }
        return userId;
    }
    public static Map<Long, ChannelHandlerContext> getMAP() {
        return MAP;
    }
    public static void remove(ChannelHandlerContext ctx) {
        USERS.remove(ctx);
        MAP.entrySet().stream().filter(entry -> entry.getValue() == ctx).forEach(entry -> MAP.remove(entry.getKey()));
    }
}
