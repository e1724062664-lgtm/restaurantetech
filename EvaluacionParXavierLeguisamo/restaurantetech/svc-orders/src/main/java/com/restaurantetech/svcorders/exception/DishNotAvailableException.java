package com.restaurantetech.svcorders.exception;


public class DishNotAvailableException extends RuntimeException {

    public DishNotAvailableException(String dishName) {
        super("El plato \"" + dishName + "\" no está disponible actualmente.");
    }
}
