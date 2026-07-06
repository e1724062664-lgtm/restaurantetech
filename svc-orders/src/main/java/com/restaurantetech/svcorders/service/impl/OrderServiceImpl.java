package com.restaurantetech.svcorders.service.impl;

import com.restaurantetech.svcorders.client.MenuClient;
import com.restaurantetech.svcorders.dto.MenuResponse;
import com.restaurantetech.svcorders.dto.OrderRequest;
import com.restaurantetech.svcorders.dto.OrderResponse;
import com.restaurantetech.svcorders.exception.DishNotAvailableException;
import com.restaurantetech.svcorders.exception.OrderNotFoundException;
import com.restaurantetech.svcorders.model.Order;
import com.restaurantetech.svcorders.model.OrderStatus;
import com.restaurantetech.svcorders.repository.OrderRepository;
import com.restaurantetech.svcorders.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final MenuClient menuClient;


    @Override
    @Transactional
    public OrderResponse create(OrderRequest request) {
        log.info("Creando pedido para cliente: {} - dishId: {}",
                request.getCustomerName(), request.getDishId());

        // Consultar svc-menu via WebClient

        MenuResponse dish = menuClient.getDishById(request.getDishId());

        // Validar disponibilidad
        if (dish == null || Boolean.FALSE.equals(dish.getAvailable())) {
            String dishName = dish != null ? dish.getName() : "Desconocido";
            log.warn("Plato '{}' no está disponible", dishName);
            throw new DishNotAvailableException(dishName);
        }

        // Calcular total automáticamente
        double total = dish.getPrice() * request.getQuantity();
        log.info("Total calculado: {} × {} = {}", dish.getPrice(), request.getQuantity(), total);

        // Construir y guardar el pedido con status PENDING
        Order order = Order.builder()
                .customerName(request.getCustomerName())
                .dishId(request.getDishId())
                .quantity(request.getQuantity())
                .total(total)
                .status(OrderStatus.PENDING)
                .build();

        Order saved = orderRepository.save(order);
        log.info("Pedido creado con id: {} - status: {}", saved.getId(), saved.getStatus());

        return toResponse(saved);
    }

    //Retorna la lista completa de pedidos.

    @Override
    @Transactional(readOnly = true)
    public List<OrderResponse> findAll() {
        log.info("Listando todos los pedidos");
        return orderRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public OrderResponse findById(Long id) {
        log.info("Buscando pedido con id: {}", id);
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
        return toResponse(order);
    }

    // Mapper
    private OrderResponse toResponse(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .customerName(order.getCustomerName())
                .dishId(order.getDishId())
                .quantity(order.getQuantity())
                .total(order.getTotal())
                .status(order.getStatus())
                .createdAt(order.getCreatedAt())
                .build();
    }
}
