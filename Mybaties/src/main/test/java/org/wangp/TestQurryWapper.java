package org.wangp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.mysql.cj.QueryBindings;
import net.minidev.json.writer.UpdaterMapper;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
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
@SpringBootTest
public class TestQurryWapper {

    @Resource
    private UserMapper userMapper;

    @Test
    public void test01(){
        QueryWrapper<User> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.like("name","里")
                .eq("age",18)
                .isNotNull("email");


        List<User> list = userMapper.selectList(objectQueryWrapper);
        for (User user : list) {
            System.out.println("user = " + user);
        }
   }

    /**
     * 2、例2：组装排序条件
     * 按年龄降序查询用户，如果年龄相同则按id升序排列
     */
   @Test
    public void test02(){
       QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
       userQueryWrapper.orderByDesc("age").orderByAsc("id");
       List<User> list = userMapper.selectList(userQueryWrapper);
       for (User user : list) {
           System.out.println("user = " + user);
       }
   }

    /**
     * 3、例3：组装删除条件
     * 删除email为空的用户
     */
    @Test
    public void test03() {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.isNull("email");
        List<User> list = userMapper.selectList(userQueryWrapper);
        for (User user : list) {
            System.out.println("user = " + user);
        }

    }

    /**
     * 4、例4：条件的优先级
     * 查询名字中包含n，且（年龄小于18或email为空的用户），
     * 并将这些用户的年龄设置为18
     * ，邮箱设置为 user@atguigu.com
     */
    @Test
    public void test04(){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.like("name","叶")
                .between("age",18,99)
                .isNotNull("email");
        List<User> list = userMapper.selectList(userQueryWrapper);
        for (User user : list) {
            System.out.println("user = " + user);
        }
    }

    /**
     *5、例5：组装select子句
     * 查询所有用户的用户名和年龄
     */

    @Test
    public void test05() {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.select("name","age");
        List<User> list = userMapper.selectList(userQueryWrapper);
        for (User user : list) {
            System.out.println("user = " + user);
        }


    }

    /**
     * 6、例6：实现子查询
     * 查询id不大于3的所有用户的id列表
     *
     */

    @Test
    public void test06(){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.le("id",3).select("id");
//        userQueryWrapper.inSql()
        List<User> list = userMapper.selectList(userQueryWrapper);
        for (User user : list) {
            System.out.println("user = " + user);

        }

   }


    /**
     *查询名字中包含n，且（年龄小于18或email为空的用户），
     * 并将这些用户的年龄设置为18，邮箱设置为 user@atguigu.com
     */

    @Test
    public void test07() {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper
                .set("name","风原万叶")
                .set("age",18)
                .set("email","Wind@GenShyin.com")
                .like("name","叶")
                .lt("age",60)
                .and(i->i.lt("age",18).isNotNull("email"));
        User user = new User();
        int update = userMapper.update(user, updateWrapper);
    }

    /**
     * 例8：动态组装查询条件
     * 查询名字中包含n，
     * 年龄大于10且小于20的用户，
     * 查询条件来源于用户输入，是可选的
     */
    @Test
    public void test08() {
        //定义查询条件，有可能为null（用户未输入）
        String name = null;
        Integer ageBegin = 10;
        Integer ageEnd = 20;

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .like(StringUtils.isNotBlank(name), "name", "n")
                .ge(ageBegin != null, "age", ageBegin)
                .le(ageEnd != null, "age", ageEnd);

        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);


    }
}
