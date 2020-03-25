package com.fc.common.socket.bean;

import lombok.Getter;
import lombok.Setter;

import java.nio.ByteBuffer;

/**
 * @program: nettyprotobuf
 * @description:
 * @author: fangqing.fan#hotmail.com
 * @create: 2020-02-11 16:17
 **/

@Getter
@Setter
public class FCMsg {

    private FCHeader fcHeader;

    private byte[] content;

    public FCMsg(){

    }

    /**
     *
     * @param fcHeader
     * @param content
     */
    public FCMsg(FCHeader fcHeader,byte[] content){
        this.fcHeader = fcHeader;
        this.content = content;
    }

    /**
     *
     * @param nProtoID
     * @param content
     */
    public FCMsg(int nProtoID,byte[] content){
        String nHeaderFlag = "FC";//包头起始标志，固定为“FC”

        int nProtoFmtType = 0; //协议格式类型，0为Protobuf格式，1为Json格式

        int nProtoVer = 0;//协议版本，用于迭代兼容, 目前填0

        long nSerialNo = System.currentTimeMillis();//包序列号，用于对应请求包和回包, 要求递增或时间戳。这里填时间戳

        int nBodyLen = content.length;//包体长度

        String arrBodySHA1 = "2cef026959a7f224bbf001874e2d955a3863a2fe";//包体原始数据(解密后)的SHA1哈希值

        String arrReserved = "a234567890123456789z";//保留20字节扩展

        // 组装协议头
        FCHeader header = new FCHeader(nHeaderFlag, nProtoID,  nProtoFmtType,  nProtoVer,  nSerialNo,  nBodyLen,  arrBodySHA1,  arrReserved);

        this.fcHeader = header;
        this.content = content;
    }

    /**
     *
     * @param buffer
     */
    public FCMsg(ByteBuffer buffer){
        byte[] headFlagBytes = new byte[2];
        buffer.get(headFlagBytes);
        String nHeaderFlag = new String(headFlagBytes);

        int nProtoID = buffer.getInt();

        int nProtoFmtType = buffer.getInt();

        int nProtoVer = buffer.getInt();

        long nSerialNo = buffer.getLong();

        int nBodyLen = buffer.getInt();

        byte[] sha1Bytes = new byte[40];
        buffer.get(sha1Bytes);
        String arrBodySHA1 = new String(sha1Bytes);

        byte[] reservedBytes = new byte[20];
        buffer.get(reservedBytes);
        String arrReserved = new String(reservedBytes);

        // 读取消息内容
        byte[] bodyBytes = new byte[nBodyLen];
        buffer.get(bodyBytes);

        FCHeader header = new FCHeader(nHeaderFlag, nProtoID,  nProtoFmtType,  nProtoVer,  nSerialNo,  nBodyLen,  arrBodySHA1,  arrReserved);

        this.fcHeader = header;
        this.content = bodyBytes;
    }

    /**
     *
     * @return
     */
    public ByteBuffer toByteBuffer(){
        ByteBuffer buffer = ByteBuffer.allocate(86+this.fcHeader.getNBodyLen());
        buffer.put(fcHeader.getNHeaderFlag().getBytes());
        buffer.putInt(fcHeader.getNProtoID());
        buffer.putInt(fcHeader.getNProtoFmtType());
        buffer.putInt(fcHeader.getNProtoVer());

        buffer.putLong(fcHeader.getNSerialNo());
        buffer.putInt(getContent().length);

        buffer.put(fcHeader.getArrBodySHA1().getBytes());
        buffer.put(fcHeader.getArrReserved().getBytes());
        buffer.put(getContent());

        buffer.flip();

        return buffer;
    }
}
