package com.fc.common.socket.bean;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;

import java.util.List;

/**
 * @program: nettyprotobuf
 * @description:
 * @author: fangqing.fan#hotmail.com
 * @create: 2020-02-11 16:17
 **/

public class FCWebsocketEncoder extends MessageToMessageEncoder<FCMsg> {


    @Override
    protected void encode(ChannelHandlerContext ctx, FCMsg fcMsg, List<Object> out) throws Exception {

        // 将Message转换成二进制数据
        FCHeader header = fcMsg.getFcHeader();

        // 这里写入的顺序就是协议的顺序.

        ByteBuf result = Unpooled.buffer();

        // 写入Header信息
        result.writeBytes(header.getNHeaderFlag().getBytes());
        result.writeInt(header.getNProtoID());
        result.writeInt(header.getNProtoFmtType());
        result.writeInt(header.getNProtoVer());

        result.writeLong(header.getNSerialNo());
        result.writeInt(fcMsg.getContent().length);

        result.writeBytes(header.getArrBodySHA1().getBytes());
        result.writeBytes(header.getArrReserved().getBytes());

        // 写入消息主体信息
        result.writeBytes(fcMsg.getContent());

        // 然后下面再转成websocket二进制流，因为客户端不能直接解析protobuf编码生成的
        WebSocketFrame frame = new BinaryWebSocketFrame(result);
        out.add(frame);
    }
}
