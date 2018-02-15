package cn.pjmike.xuebei.controller;

import cn.pjmike.xuebei.exception.NoPermissionException;
import cn.pjmike.xuebei.exception.NullException;
import cn.pjmike.xuebei.exception.UserException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * 异常处理类
 *
 * @author pjmike
 * @create 2018-01-29 22:50
 **/
@RestControllerAdvice
public class OwnerExceptionController {
    @ExceptionHandler(NullException.class)
    public Map<String, String> nullExceptionHandler(RuntimeException ex) {
        Map<String, String> map = new HashMap<String, String>(16);
        map.put("status", "1");
        map.put("message", ex.getMessage());
        return map;
    }

    @ExceptionHandler(Exception.class)
    public Map<String, String> ExceptionHandler(Exception e) {
        Map<String, String> map = new HashMap<String, String>(16);
        map.put("status", "1");
        map.put("message",e.getMessage());
        return map;
    }
    @ExceptionHandler(UnsupportedEncodingException.class)
    public Map<String, String> EncodingExceptionHandler(Exception e) {
        Map<String, String> map = new HashMap<String, String>(16);
        map.put("status", "1");
        map.put("message", "系统编码错误");
        return map;
    }
    @ExceptionHandler(IOException.class)
    public Map<String, String> IOExceptionHandler(Exception e) {
        Map<String, String> map = new HashMap<String, String>(16);
        map.put("status", "1");
        map.put("message", "编码错误");
        return map;
    }
    @ExceptionHandler(NoPermissionException.class)
    public Map<String, String> IOExceptionHandler(RuntimeException e) {
        Map<String, String> map = new HashMap<String, String>(16);
        map.put("status", "1");
        map.put("message", e.getMessage());
        return map;
    }
    @ExceptionHandler(UserException.class)
    public Map<String, String> UserExceptionHandler(RuntimeException e) {
        Map<String, String> map = new HashMap<String, String>(16);
        map.put("status", "1");
        map.put("message", e.getMessage());
        return map;
    }
}
