package org.wangp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.wangp.mapper.UserMapper;
import org.wangp.pojo.User;
import org.wangp.service.UserService;
import org.springframework.stereotype.Service;
/**
 * 2021/7/26
 *
 * @author PingW
 */

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
}
