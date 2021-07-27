package org.wangp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.wangp.pojo.Product;

/**
 * 2021/7/27
 *
 * @author PingW
 */
@Mapper
public interface ProductMapper extends BaseMapper<Product> {

}
