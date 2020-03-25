package com.fc.server.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @program: nettyprotobuf
 * @description:
 * @author: fangqing.fan#hotmail.com
 * @create: 2020-02-21 13:52
 **/

@Service
@Slf4j
public class GameService {

    long lastUpdate = 0;
    long stepUpdateCounter = 0;


    int gameStatus = 0;//游戏状态


    int joinCount = 0;//游戏人数
    int maxJoinCount = 2;//房间支持最大人数
    long stepTime = 0;//当前step时间戳
    long stepInterval = 100;//没个step的间隔ms




    //setp定时器
    public void setUpdate(){

    }

    //frame定时器
    public void update(){
        long now = System.currentTimeMillis();
        long dt = now - lastUpdate;
        lastUpdate = now;

        if(gameStatus == 1){
            stepUpdateCounter += dt;
        }

    }
}
