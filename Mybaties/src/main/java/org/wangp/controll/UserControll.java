package org.wangp.controll;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wangp.mapper.UserMapper;
import org.wangp.pojo.User;

import javax.annotation.Resource;

/**
 * 2021/7/27
 *
 * @author PingW
 */
@CrossOrigin
@RestController
@RequestMapping(value ="user")
public class UserControll {
    @Resource
    private UserMapper userMapper;

    @GetMapping(value = "/list")
    public String testUser(){

        User user = userMapper.selectById(6);
        String s = user.toString();
        return s;

    }

}
