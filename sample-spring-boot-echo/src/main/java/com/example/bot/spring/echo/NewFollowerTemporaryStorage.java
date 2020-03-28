package com.example.bot.spring.echo;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class NewFollowerTemporaryStorage {

    private final TemporaryStorage newFollowerTemporaryStorage;

    public NewFollowerTemporaryStorage(TemporaryStorage newFollowerTemporaryStorage) {
        this.newFollowerTemporaryStorage = newFollowerTemporaryStorage;
    }

    public void put(long l, NewFollowerEntity follower) {
        newFollowerTemporaryStorage.put(follower.getUid(), follower);
    }

    public NewFollowerEntity get(long uid) {
        return (NewFollowerEntity) newFollowerTemporaryStorage.get(uid);
    }
}
