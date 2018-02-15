package cn.pjmike.xuebei.controller;

import cn.pjmike.xuebei.domain.UserDto;
import cn.pjmike.xuebei.service.CreateCrowdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 关于面对面建群
 *
 * @author pjmike
 * @create 2018-02-01 15:39
 **/
@Controller
@RequestMapping("/Crowd")
public class CrowdController {
    @Autowired
    CreateCrowdService createCrowdService;

    /**
     * 保存包含位置信息的用户信息
     *
     * @param userDto
     * @return
     */
    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    @ResponseBody
    public List<UserDto> saveUser(@RequestBody UserDto userDto) {
        //先保存用户信息，再找出数据库中输入密码相同且在10范围内的用户信息
        return createCrowdService.saveAndReturnUserDto(userDto);
    }

    /**
     * 面对面建群，返回输入密码相同的进入群的群员信息
     *
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public List<UserDto> createCrowd() {
        List<UserDto> userDtos = createCrowdService.findUserDTOByPwd();
        return userDtos;
    }
}
