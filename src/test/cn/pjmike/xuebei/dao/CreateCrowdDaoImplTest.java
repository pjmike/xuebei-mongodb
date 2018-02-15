package cn.pjmike.xuebei.dao;

import cn.pjmike.xuebei.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/*.xml")
public class CreateCrowdDaoImplTest {
    @Autowired
    MongoTemplate mongoTemplate;
    @Test
    public void findFirstUserDto() throws Exception {
        User user = mongoTemplate.findAll(User.class).get(0);
        System.out.println(user);
    }
}