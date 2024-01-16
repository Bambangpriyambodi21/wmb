package com.enigma.wmb.service;

import com.enigma.wmb.entity.Menu;
import com.enigma.wmb.entity.Order;
import com.enigma.wmb.model.OrderRequest;
import com.enigma.wmb.model.OrderResponse;
import com.enigma.wmb.model.SearchMenuRequest;
import com.enigma.wmb.model.SearchOrderRequest;
import org.springframework.data.domain.Page;

public interface OrderService {
    OrderResponse create(OrderRequest orderRequest);
    Page<Order> getAll(SearchOrderRequest request);
    OrderResponse getById(String id);
}
