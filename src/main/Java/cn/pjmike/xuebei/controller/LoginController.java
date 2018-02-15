package cn.pjmike.xuebei.controller;

import cn.pjmike.xuebei.Jwt.JwtToken;
import cn.pjmike.xuebei.domain.User;
import cn.pjmike.xuebei.exception.NullException;
import cn.pjmike.xuebei.exception.UserException;
import cn.pjmike.xuebei.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * 注册登录逻辑
 *
 * @author pjmike
 * @cr@eate 2018-01-29 22:37
 **/
@Controller
public class LoginController {
    private Map<String, String> map;
    @Autowired
    private UserService userService;

    /**
     * 用户注册
     *
     * @param user
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> signup(@RequestBody User user) throws UnsupportedEncodingException, MessagingException {
        map = new HashMap<String, String>(16);
        //对传入参数作空的判断
        if (user.getEmail() == null || user.getEmail() == null) {
            throw new NullException("邮箱或密码输入为空");
        }
        //刚注册时，用state设置为1表示用户尚未激活
        user.setState(1);
        //注册用户操作
        if (!userService.register(user)) {
            throw new UserException("该邮箱已经被注册了");
        }
        map.put("status", "0");
        map.put("message", "请登录邮箱进行验证");
        return map;
    }

    /**
     * 用户登录
     *
     * @param user
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> signin(@RequestBody User user) throws UnsupportedEncodingException {
        //对传入参数作空的判断
        if (user.getEmail()== null || user.getEmail() == null) {
            throw new NullException("邮箱或密码输入为空");
        }
        //进行验证登录操作
        boolean result = userService.verifyPwdAndFindUser(user);
        //获取用户token
        //设置过期时间3天
        long TTLMills = 1000 * 60 * 60 * 24 * 3;
        String token = JwtToken.createTokenWithTime(user.getEmail(),TTLMills);
        //进行判断，成功返回true,失败返回false
        if (!result) {
            map = new HashMap<String, String>(16);
            map.put("status", "1");
            map.put("message", "该用户不存在或者用户尚未激活");
            return map;
        }
        map = new HashMap<String, String>(16);
        map.put("status", "0");
        map.put("message", "该用户登录成功");
        map.put("token", token);
        return map;
    }

    /**
     * 激活用户操作
     *
     * @param token
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/ActiveUser", method = RequestMethod.GET)
    public String ActiveUser(@RequestParam("token") String token) throws UnsupportedEncodingException {
        if (token == null) {
            return "error";
        }
        //如果验证token成功，则返回成功页面，失败返回失败页面
        if (userService.activeUser(token)) {
            return "successActive";
        } else {
            return "failActive";
        }
    }
}
