package com.fc.common.socket.bean;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @program: nettyprotobuf
 * @description:
 * @author: fangqing.fan#hotmail.com
 * @create: 2020-02-11 16:17
 **/

public class FCEncoder extends MessageToByteEncoder<FCMsg> {

    @Override
    protected void encode(ChannelHandlerContext ctx, FCMsg fcMsg, ByteBuf out) throws Exception {

        // 将Message转换成二进制数据
        FCHeader header = fcMsg.getFcHeader();

        // 这里写入的顺序就是协议的顺序.

        // 写入Header信息
        out.writeBytes(header.getNHeaderFlag().getBytes());
        out.writeInt(header.getNProtoID());
        out.writeInt(header.getNProtoFmtType());
        out.writeInt(header.getNProtoVer());

        out.writeLong(header.getNSerialNo());
        out.writeInt(fcMsg.getContent().length);

        out.writeBytes(header.getArrBodySHA1().getBytes());
        out.writeBytes(header.getArrReserved().getBytes());

        // 写入消息主体信息
        out.writeBytes(fcMsg.getContent());


    }
}

