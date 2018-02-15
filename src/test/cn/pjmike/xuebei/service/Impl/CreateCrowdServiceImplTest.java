package cn.pjmike.xuebei.service.Impl;

import cn.pjmike.xuebei.domain.UserDto;
import cn.pjmike.xuebei.service.CreateCrowdService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/*.xml")
public class CreateCrowdServiceImplTest {
    @Autowired
    private CreateCrowdService createCrowdService;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void saveAndReturnUserDto() throws Exception {
        UserDto dto = new UserDto();
        dto.setLocation(new double[]{-73.99071, 40.538457});
        dto.setNumber(1234);
        dto.setUsername("pjme");
        dto.setIcon("https:/sdf/sdfsf");
        dto.setCreate_time(new Date());
        List<UserDto> userDtos = createCrowdService.saveAndReturnUserDto(dto);
        System.out.println(userDtos);
    }

    @Test
    public void save() {
        UserDto dto = new UserDto();
        dto.setLocation(new double[]{-73.99171, 40.516488});
        dto.setNumber(1234);
        dto.setUsername("pj23");
        dto.setIcon("https:/sdf/sdsfdfsdfsdfsdffsf");
        dto.setCreate_time(new Date());
        mongoTemplate.insert(dto, "userdto");
    }

    @Test
    public void findUserDTOByPwd() throws Exception {
    }

    @Test
    public void findUserDTOByPwdLimit() throws Exception {
    }

    @Test
    public void findUserDto() throws Exception {
    }

}