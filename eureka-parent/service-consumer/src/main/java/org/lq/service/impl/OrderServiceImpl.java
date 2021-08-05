package org.lq.service.impl;

import org.lq.pojo.Order;
import org.lq.pojo.Product;
import org.lq.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/*
    订单服务
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;
    private LoadBalancerClient loadBalancerClient; //Ribbon 负载均衡器
    public Order selectOrderById(Integer id) {
        return new Order(id,"order-001","中国",319949D,selectProductListByDiscoveryClient());
    }
    /*private List<Product> selectProductListByDiscoveryClient(){
        StringBuffer sb = null;
        //获取服务列表
        List<String> serviceIds  = discoveryClient.getServices();
        if(CollectionUtils.isEmpty(serviceIds))
            return null;
        //根据服务名称获取服务
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("service-provider ");
        if(CollectionUtils.isEmpty(serviceInstances))
            return null;
        ServiceInstance si = serviceInstances.get(0);
        sb = new StringBuffer();
        sb.append("http://" + si.getHost() + ":" + si.getPort() + "/product/list");
        //ResponseEntity:封装了返回数据
        ResponseEntity<List<Product>> response = restTemplate.exchange(
                sb.toString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Product>>() {});
        return response.getBody();
    }
    //通过loaderbalance负载均衡器调用
    private List<Product> selectProductListByLoaderBalancerClient(){
        StringBuffer sb = null;
        //根据服务名称获取服务
        ServiceInstance si = loadBalancerClient.choose("service-provider");
        if(si == null){
            return  null;
        }
        sb = new StringBuffer();
        sb.append("http://" + si.getHost() + ":" + si.getPort()+"/product/list");
        ResponseEntity<List<Product>> response = restTemplate.exchange(
                sb.toString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Product>>() {});
        return  response.getBody();
    }*/
    private List<Product> selectProductListByDiscoveryClient(){
        //ResponseEntity 封装了返回数据
        ResponseEntity<List<Product>> response = restTemplate.exchange(
                "http://SERVICE-PROVIDER/product/list",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Product>>() {});
                return  response.getBody();
    }

}
