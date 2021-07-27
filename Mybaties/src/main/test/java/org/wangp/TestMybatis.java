package org.wangp;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.wangp.mapper.UserMapper;
import org.wangp.pojo.User;

import javax.annotation.Resource;
import java.util.List;

/**
 * 2021/7/27
 *
 * @author PingW
 */
@SpringBootTest(classes = Application.class)
public class TestMybatis {

    @Resource
    private UserMapper userMapper;
    @Test
    public void testCreate(){
        User user = new User();
        user.setName("肖宫");
        user.setEmail("822@qqq.com");
        user.setAge(30);
        userMapper.insert(user);
    }

    @Test
    public void testEx1(){

        Page<User> userPage = new Page<>(2,4);
        userMapper.selectPage(userPage, null);
        List<User> records = userPage.getRecords();
        for (User record : records) {
            System.out.println("record = " + record);
        }

    }
    @Test
    public void testEx2(){
        Page<User> objectPage = new Page<>(1, 3);
        userMapper.selectByPage(objectPage,30);
        List<User> records = objectPage.getRecords();
        for (User record : records) {
            System.out.println("record = " + record);
        }


    }






}
