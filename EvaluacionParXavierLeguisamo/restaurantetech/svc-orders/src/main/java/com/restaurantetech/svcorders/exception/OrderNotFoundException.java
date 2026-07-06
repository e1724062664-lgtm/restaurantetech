package com.restaurantetech.svcorders.exception;


public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(Long id) {
        super("El pedido con id " + id + " no existe.");
    }
}
