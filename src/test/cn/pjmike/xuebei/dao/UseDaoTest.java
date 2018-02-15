package cn.pjmike.xuebei.dao;

import cn.pjmike.xuebei.domain.Venue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext-mongodb.xml")
public class UseDaoTest {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void UserDaoTest() {
        Point point = new Point(1, 2);
        List<Venue> venues = mongoTemplate.find(Query.query(Criteria.where("location").near(point).maxDistance(5)), Venue.class, "location");
        for (Venue venue : venues) {
            System.out.println(venue);
        }
   /*     Venue venue = new Venue("name",new double[]{1,2});
        mongoTemplate.insert(venue);*/
    }

}