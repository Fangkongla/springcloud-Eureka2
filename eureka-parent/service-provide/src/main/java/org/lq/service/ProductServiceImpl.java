package org.lq.service;

import org.lq.pojo.Product;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    @Override
    public List<Product> selectProductList() {
        return Arrays.asList(
                new Product(1,"华为手机",2,5888D),
                new Product(2,"联想笔记本",1,6888D),
                new Product(3,"小米平板",5,2666D)

        );
    }
}
