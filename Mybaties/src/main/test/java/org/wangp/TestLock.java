package org.wangp;

import org.apache.ibatis.annotations.Update;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.wangp.mapper.ProductMapper;
import org.wangp.mapper.UserMapper;
import org.wangp.pojo.Product;
import org.wangp.pojo.User;

import javax.annotation.Resource;

/**
 * 2021/7/27
 *
 * @author PingW
 */
@SpringBootTest
public class TestLock {

    @Resource
    private ProductMapper productMapper;

    @Test
    public void TestLock01(){

        // 测试乐观锁
        // 商品原本价格为100
        Product product = productMapper.selectById(1);
        Product product2 = productMapper.selectById(1);

        // 条件他们同时取出100
        // 老板通知A将增加商品价格50
        System.out.println("priceA = " + product.getPrice());
        product.setPrice(product.getPrice()+50);

        productMapper.updateById(product);
        System.out.println("productA = " + product);

        // 老板通知B将商品价格减少30
        System.out.println("priceB = " + product2.getPrice());
        product2.setPrice(product2.getPrice()-30);

        if (productMapper.updateById(product2)==0){
            System.out.println("B 修改失败");
            Product product3 = productMapper.selectById(1);
            product3.setPrice(product3.getPrice()-30);
            productMapper.updateById(product3);
            System.out.println("productB = " + product3);
        }

        //

    }

}
