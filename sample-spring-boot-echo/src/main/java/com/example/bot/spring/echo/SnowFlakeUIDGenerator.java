package com.example.bot.spring.echo;

import org.springframework.stereotype.Component;

@Component
public class SnowFlakeUIDGenerator {

    private SnowFlake snowFlake;

    public SnowFlakeUIDGenerator(){
        snowFlake = new SnowFlake(1L, 1L);
    }

    public long get(){
        return snowFlake.nextId();
    }
}
