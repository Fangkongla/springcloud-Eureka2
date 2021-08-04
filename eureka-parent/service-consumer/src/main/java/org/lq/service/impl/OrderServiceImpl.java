package org.lq.service.impl;

import org.lq.pojo.Order;
import org.lq.pojo.Product;
import org.lq.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/*
    订单服务
 */
public class OrderServiceImpl implements OrderService {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;
    @Override
    public Order selectOrderById(Integer id) {
        return new Order(id,"order-001","中国",319949D,selectProductListByDiscoveryClient());
    }
    private List<Product> selectProductListByDiscoveryClient(){
        StringBuffer sb = null;
        //获取服务列表
        List<String> serviceIds  = discoveryClient.getServices();
        if(CollectionUtils.isEmpty(serviceIds))
            return null;
        //根据服务名称获取服务
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("service-provide");
        if(CollectionUtils.isEmpty(serviceInstances))
            return null;
        ServiceInstance si = serviceInstances.get(0);
        sb = new StringBuffer();
        return new ArrayList<Product>();
    }
}