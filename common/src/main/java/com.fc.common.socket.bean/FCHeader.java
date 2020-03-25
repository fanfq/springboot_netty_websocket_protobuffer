package com.fc.common.socket.bean;


import lombok.Getter;
import lombok.Setter;


/**
 * @program: nettyprotobuf
 * @description:
 * @author: fangqing.fan#hotmail.com
 * @create: 2020-02-11 16:17
 **/

@Getter
@Setter
public class FCHeader {
                                                //bit      byte      消息头的固定长度是86个字节
    private String nHeaderFlag = "FC";          //8*4      2         包头起始标志，固定为“FC”
    private int nProtoID;                       //8*4      4         协议ID
    private int nProtoFmtType = 0;              //8*4      4         协议格式类型，0为Protobuf格式，1为Json格式
    private int nProtoVer = 0;                  //8*4      4         协议版本，用于迭代兼容, 目前填0
    private long nSerialNo;                     //8*8      8         包序列号，用于对应请求包和回包, 要求递增或时间戳
    private int nBodyLen;                       //8*4      4         包体长度
    private String arrBodySHA1;                 //8*40     40        包体原始数据(解密后)的SHA1哈希值
    private String arrReserved;                 //8*20     20        保留20字节扩展

    public FCHeader() {

    }

    public FCHeader(String nHeaderFlag, int nProtoID, int nProtoFmtType, int nProtoVer, long nSerialNo, int nBodyLen, String arrBodySHA1, String arrReserved) {
        this.nHeaderFlag = nHeaderFlag;
        this.nProtoID = nProtoID;
        this.nProtoFmtType = nProtoFmtType;
        this.nProtoVer = nProtoVer;
        this.nSerialNo = nSerialNo;
        this.nBodyLen = nBodyLen;
        this.arrBodySHA1 = arrBodySHA1;
        this.arrReserved = arrReserved;
    }

}
