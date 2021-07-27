package org.wangp.mapper;

/**
 * 2021/7/26
 *
 * @author PingW
 */

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.wangp.pojo.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    Page<User> selectByPage(Page<?> page, Integer age);
}