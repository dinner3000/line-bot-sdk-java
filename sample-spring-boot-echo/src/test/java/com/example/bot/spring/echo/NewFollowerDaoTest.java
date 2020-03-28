package com.example.bot.spring.echo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class NewFollowerDaoTest {

    @Autowired
    private NewFollowerTemporaryStorage newFollowerDao;

    @Autowired
    private SnowFlakeUIDGenerator snowFlakeUIDGenerator;

    @Test
    public void Test(){

        NewFollowerEntity newFollowerEntity = new NewFollowerEntity(
            snowFlakeUIDGenerator.get(),
            "1101",
            "UID21536464756"
        );

        newFollowerDao.put(1L, newFollowerEntity);

        NewFollowerEntity newFollowerEntity1 = newFollowerDao.get(newFollowerEntity.getUid());

        assertEquals(newFollowerEntity, newFollowerEntity1);

    }
}
