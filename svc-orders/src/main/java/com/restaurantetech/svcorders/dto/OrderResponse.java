package com.restaurantetech.svcorders.dto;

import com.restaurantetech.svcorders.model.OrderStatus;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {

    private Long id;
    private String customerName;
    private Long dishId;
    private Integer quantity;
    private Double total;


    private OrderStatus status;

    private LocalDateTime createdAt;
}
