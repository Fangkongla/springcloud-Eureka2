package org.lq.service;

import org.lq.pojo.Order;

public interface OrderService {
    Order selectOrderById(Integer id);
}
