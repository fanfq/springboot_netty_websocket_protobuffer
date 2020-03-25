package com.fc.client.websocket;

import com.fc.common.socket.bean.FCMsg;
import com.fc.common.socket.msg.KeepAliveMsg;
import com.fc.common.socket.msg.ProtoID;
import com.google.protobuf.InvalidProtocolBufferException;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.nio.ByteBuffer;

/**
 * @program: nettyprotobuf
 * @description:
 * @author: fangqing.fan#hotmail.com
 * @create: 2020-02-12 19:28
 **/

@Slf4j
public class FCWebSocketClient {

    private WebSocketClient webSocketClient = null;
    private static long aauserId=1001;

    public void connect(String url) {
        try {

            webSocketClient = new WebSocketClient(new URI(url)) {
                @Override
                public void onOpen(ServerHandshake handshakedata) {
                    log.info("握手成功");

                    Thread t = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            while (true){
                                ping();//碰撞
                                try{
                                    Thread.sleep(5000);
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                    t.start();
                }

                @Override
                public void onMessage(String message) {
                    log.info("消息1");
                }

                @Override
                public void onMessage(ByteBuffer bytes) {
                    //log.info("消息2");
                    super.onMessage(bytes);


                    FCMsg fcMsg = new FCMsg(bytes);
                    int nProtoID = fcMsg.getFcHeader().getNProtoID();
                    //log.info(JSONObject.toJSONString(fcMsg));
                    //log.info(JSONObject.toJSONString(fcMsg.getFcHeader()));
                    try {
                        switch (nProtoID){
                            case ProtoID.MsgType.PONG_VALUE:
                                KeepAliveMsg.S2CMsg pongMsg = KeepAliveMsg.S2CMsg.parseFrom(fcMsg.getContent());
                                //log.info("code:{},msg:{}",pongMsg.getCode(),pongMsg.getMsg());
                                log.info("recv nProtoID:{}, ts:{}",nProtoID,pongMsg.getS2C().getTimestamp());
                                break;
                            default:
                                log.info("uncatch event:{}",fcMsg.getFcHeader().getNProtoID());
                                break;
                        }
                    }catch (InvalidProtocolBufferException e){
                        e.printStackTrace();
                    }
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    log.info("关闭");
                }

                @Override
                public void onError(Exception ex) {
                    log.info("错误");
                }
            };

            webSocketClient.connect();

        } catch (Exception ex) {

            ex.printStackTrace();
        }
    }



    //启动线程
    private void ping(){
        long ttl = System.currentTimeMillis();
        KeepAliveMsg.C2S c2s = KeepAliveMsg.C2S.newBuilder().setTimestamp(ttl).build();
        KeepAliveMsg.C2SMsg c2sMsg = KeepAliveMsg.C2SMsg.newBuilder().setC2S(c2s).build();

        //构造MSG
        int nProtoID = ProtoID.MsgType.PING_VALUE;
        byte[] content = c2sMsg.toByteArray();
        FCMsg fcMsg = new FCMsg(nProtoID,content);

        log.info("send nProtoID:{}, ts:{}",nProtoID,ttl);

        webSocketClient.send(fcMsg.toByteBuffer());
    }


    public static void main(String[] args){
        long ttl = System.currentTimeMillis();
        KeepAliveMsg.C2S c2s = KeepAliveMsg.C2S.newBuilder().setTimestamp(ttl).build();
        KeepAliveMsg.C2SMsg c2sMsg = KeepAliveMsg.C2SMsg.newBuilder().setC2S(c2s).build();

        //构造MSG
        int nProtoID = ProtoID.MsgType.PING_VALUE;
        byte[] content = c2sMsg.toByteArray();//这里是原始的body的字节数组，而proto仅仅是在数据封装以及效率上的提升并没有加密的特性
        //FCMsg fcMsg = new FCMsg(nProtoID,content);

        log.info("send nProtoID:{}, ts:{}",nProtoID,ttl);

        // AES
        // 开始将body进行AES加密
        //AES加密要求源数据长度必须是16的整数倍, 故需补‘0’对齐后再加密，记录mod_len为源数据长度与16取模值
        int mod_len = content.length%16;
        if(mod_len != 0){
            byte[] CDRIVES =
                    new byte[16-mod_len];
            for(int i=0;i<16-mod_len;i++){
                CDRIVES[i] = 0;
            }

            byte[] byte_3 = new byte[content.length+CDRIVES.length];
            System.arraycopy(content, 0, byte_3, 0, content.length);
            System.arraycopy(CDRIVES, 0, byte_3, content.length, CDRIVES.length);
        }



        //因加密前有可能对源数据作修改， 故需在加密后的数据尾再增加一个16字节的填充数据块，其最后一个字节赋值mod_len, 其余字节赋值‘0’， 将加密数据和额外的填充数据块拼接作为最终要发送协议的body数据


        String AES_KEY = "TEST123";


    }

}
