package org.wangp.mapper;

/**
 * 2021/7/26
 *
 * @author PingW
 */

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.wangp.pojo.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {


}
