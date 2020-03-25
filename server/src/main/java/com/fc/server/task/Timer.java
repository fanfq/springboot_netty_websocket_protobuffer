package com.fc.server.task;

import com.fc.server.service.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @program: nettyprotobuf
 * @description:
 * @author: fangqing.fan#hotmail.com
 * @create: 2020-02-21 13:49
 **/

@Slf4j
@Component
public class Timer {


    @Autowired
    private GameService gameService;

    @Scheduled(fixedRate = 100) //100ms 同步一次
    public void frameSync(){

    }
}
