package com.restaurantetech.svcorders.exception;


public class DishNotFoundException extends RuntimeException {

    public DishNotFoundException(Long dishId) {
        super("El plato con id " + dishId + " no existe en el menú.");
    }
}
