package com.fc.common.socket.bean;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;

import java.util.List;


/**
 * @program: nettyprotobuf
 * @description:
 * @author: fangqing.fan#hotmail.com
 * @create: 2020-02-11 16:17
 **/

public class FCWebsocketDecoder extends MessageToMessageDecoder<WebSocketFrame> {

    @Override
    protected void decode(ChannelHandlerContext ctx, WebSocketFrame frame, List<Object> out) throws Exception {
        ByteBuf in  = ((BinaryWebSocketFrame) frame).content();

        int size = in.readableBytes();
//        System.out.println("可读长度："+size); //可读长度

//        in.markReaderIndex();//从头开始读取
//        byte[] ableBytes = new byte[size];
//        in.readBytes(ableBytes);
//
//        System.out.println("###");
//        for(byte xx:ableBytes){
//            System.out.print(xx +" "); //可读长度
//
//        }
//        System.out.println("\r\n###");
//        System.out.println("ableBytes："+new String(ableBytes)); //可读长度
//
//        System.out.println("\r\n###");

        in.markReaderIndex();//从头开始读取


        byte[] headFlagBytes = new byte[2];
        in.readBytes(headFlagBytes);
        String nHeaderFlag = new String(headFlagBytes);

        int nProtoID = in.readInt();

        int nProtoFmtType = in.readInt();

        int nProtoVer = in.readInt();

        long nSerialNo = in.readLong();

        int nBodyLen = in.readInt();

        byte[] sha1Bytes = new byte[40];
        in.readBytes(sha1Bytes);
        String arrBodySHA1 = new String(sha1Bytes);

        byte[] reservedBytes = new byte[20];
        in.readBytes(reservedBytes);
        String arrReserved = new String(reservedBytes);

        // 组装协议头
        FCHeader header = new FCHeader(nHeaderFlag, nProtoID,  nProtoFmtType,  nProtoVer,  nSerialNo,  nBodyLen,  arrBodySHA1,  arrReserved);
//        System.out.println("decode:"+ JSONObject.toJSONString(header));


        // 读取消息内容
        byte[] bodyBytes = new byte[nBodyLen];
        in.readBytes(bodyBytes);



        FCMsg message = new FCMsg(header, bodyBytes);
        out.add(message);
    }

}
