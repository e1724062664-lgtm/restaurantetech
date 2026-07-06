package com.restaurantetech.svcorders.service;

import com.restaurantetech.svcorders.dto.OrderRequest;
import com.restaurantetech.svcorders.dto.OrderResponse;

import java.util.List;


public interface OrderService {

    OrderResponse create(OrderRequest request);

    List<OrderResponse> findAll();

    OrderResponse findById(Long id);
}
