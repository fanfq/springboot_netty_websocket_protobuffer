# springboot_netty_websocket_protobuffer
for a demo with springboot，netty，websocket,protobuf


协议说明
![图片](https://github.com/fanfq/springboot_netty_websocket_protobuffer/blob/master/dist/msg.png?raw=true)


参数 | 位数 | 字节数 |  说明
:----------- | :-----------: | -----------:| -----------:
nHeaderFlag         | 8*4      |  2        |   包头起始标志，固定为“FC”
nProtoID                     | 8*4     |   4      |     协议ID
nProtoFmtType              | 8*4    |    4       |    协议格式类型，0为Protobuf格式，1为Json格式，目前仅支持 0
nProtoVer                  | 8*4     |   4       |    协议版本，用于迭代兼容, 目前填0
nSerialNo                     | 8*8     |   8        |   包序列号，用于对应请求包和回包, 要求递增
nBodyLen                      | 8*4     |   4         |  包体长度
arrBodySHA1                  | 8*40    |   40      |    包体原始数据(解密后)的SHA1哈希值
arrReserved                | 8*20    |   20       |   保留20字节扩展


分类 | 协议ID| Protobuf文件 | C2S | S2C | 说明 
:----------- | :-----------: | -----------:| -----------:| -----------:| -----------:
 1        |     1001    |   [KeepAliveMsg](https://github.com/fanfq/springboot_netty_websocket_protobuffer/blob/master/common/src/main/java/com/fc/common/socket/protobuf/KeepAliveMsg.proto)   |   ping |   -   |       心跳
 1        |     1901    |   [KeepAliveMsg](https://github.com/fanfq/springboot_netty_websocket_protobuffer/blob/master/common/src/main/java/com/fc/common/socket/protobuf/KeepAliveMsg.proto)   |   -   |       pong     |      心跳
 
author:

![img](https://raw.githubusercontent.com/fanfq/creator_ws_protobuf_client/master/fred_40x40_write.png)
 
follwing me!

my vchat channel
![qrcode](https://raw.githubusercontent.com/fanfq/springboot_netty_websocket_protobuffer/master/dist/vcqrcode.jpg)

[my facebook channel](https://www.facebook.com/FredChannel-100585011578160/)
