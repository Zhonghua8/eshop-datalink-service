package com.daxia.eshop.datalink.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description
 * @Author daxia
 * @Date 2019/6/2 13:09
 * @Version 1.0
 */

@FeignClient(value = "eshop-product-service")
public interface EshopProductService {
    
    @RequestMapping(value = "/brand/findById", method = RequestMethod.GET)
    String findBrandById(@RequestParam(value = "id") Long id);
    
    @RequestMapping(value = "/brand/findByIds", method = RequestMethod.GET)
    String findBrandByIds(@RequestParam(value = "ids") String ids);

    @RequestMapping(value = "/category/findById", method = RequestMethod.GET)
    String findCategoryById(@RequestParam(value = "id") Long id);

    @RequestMapping(value = "/productIntro/findById", method = RequestMethod.GET)
    String findProductIntroById(@RequestParam(value = "id") Long id);

    @RequestMapping(value = "/productProperty/findById", method = RequestMethod.GET)
    String findProductPropertyById(@RequestParam(value = "id") Long id);

    @RequestMapping(value = "/productProperty/findByProductId", method = RequestMethod.GET)
    String findProductPropertyByProductId(@RequestParam(value = "productId") Long productId);

    @RequestMapping(value = "/product/findById", method = RequestMethod.GET)
    String findProductById(@RequestParam(value = "id") Long id);

    @RequestMapping(value = "/productSpecification/findById", method = RequestMethod.GET)
    String findProductSpecificationById(@RequestParam(value = "id") Long id);

    @RequestMapping(value = "/productSpecification/findByProductId", method = RequestMethod.GET)
    String findProductSpecificationByProductIdId(@RequestParam(value = "productId") Long productId);
}
