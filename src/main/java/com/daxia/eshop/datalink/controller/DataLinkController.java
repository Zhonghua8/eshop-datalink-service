package com.daxia.eshop.datalink.controller;

import com.alibaba.fastjson.JSONObject;
import com.daxia.eshop.datalink.service.EshopProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

/**
 * @Description
 * @Author daxia
 * @Date 2019/6/3 15:27
 * @Version 1.0
 */

@RestController
public class DataLinkController {
    
    @Autowired
    private EshopProductService eshopProductService;
    
    @Autowired
    private JedisPool jedisPool;
    
    @RequestMapping("/product")
    public String getProduct(Long productId){
        //先读本地ehcache
        
        //如果本地没有缓存然后读redis主集群
        Jedis jedis = jedisPool.getResource();
        String dimProductJson = jedis.get("dim_product_" + productId);
        
        if(dimProductJson == null || "".equals(dimProductJson)){
            String productDataJSON = eshopProductService.findProductById(productId);

            if(productDataJSON != null && !"".equals(productDataJSON)) {
                JSONObject productDataJSONObject = JSONObject.parseObject(productDataJSON);

                String productPropertyDataJSON = eshopProductService.findProductPropertyByProductId(productId);
                if(productPropertyDataJSON != null && !"".equals(productPropertyDataJSON)) {
                    productDataJSONObject.put("product_property", JSONObject.parse(productPropertyDataJSON));
                }

                String productSpecificationDataJSON = eshopProductService.findProductSpecificationByProductIdId(productId);
                if(productSpecificationDataJSON != null && !"".equals(productSpecificationDataJSON)) {
                    productDataJSONObject.put("product_specification", JSONObject.parse(productSpecificationDataJSON));
                }

                jedis.set("dim_product_" + productId, productDataJSONObject.toJSONString());
                
                return productDataJSONObject.toJSONString();
            } 
        }
        
        return "";
    }
}
