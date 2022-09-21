package com.mybookstore.mybookstorebackend.serviceimpl;

import com.mybookstore.mybookstorebackend.constant.Constant;
import com.mybookstore.mybookstorebackend.service.TimeService;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

@Service
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TimeServiceImpl implements TimeService {

    private StopWatch stopWatch = null;

    @Override
    public Integer start() {
        stopWatch = null;
        stopWatch = new StopWatch();
        stopWatch.start();
        return Constant.SUCCESS;
    }

    @Override
    public Long stop() {
        try{
            stopWatch.stop();
            return stopWatch.getTotalTimeMillis();
        } catch (Exception e){
            e.printStackTrace();
            return (long) -1;
        }
    }
}
