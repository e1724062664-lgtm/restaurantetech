package com.restaurantetech.svcorders.controller;

import com.restaurantetech.svcorders.dto.OrderRequest;
import com.restaurantetech.svcorders.dto.OrderResponse;
import com.restaurantetech.svcorders.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> create(@Valid @RequestBody OrderRequest request) {
        log.info("POST /api/orders - cliente: {} dishId: {}", request.getCustomerName(), request.getDishId());
        OrderResponse response = orderService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //Lista todos los pedidos. HTTP 200.

    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAll() {
        log.info("GET /api/orders");
        return ResponseEntity.ok(orderService.findAll());
    }

    //Retorna el pedido con el ID dado. HTTP 200 si existe, 404 si no.

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> findById(@PathVariable Long id) {
        log.info("GET /api/orders/{}", id);
        return ResponseEntity.ok(orderService.findById(id));
    }
}
