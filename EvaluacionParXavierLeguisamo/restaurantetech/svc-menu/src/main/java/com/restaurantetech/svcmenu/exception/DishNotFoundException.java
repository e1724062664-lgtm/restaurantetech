package com.restaurantetech.svcmenu.exception;

//Excepción lanzada cuando un plato no se encuentra por su ID.

public class DishNotFoundException extends RuntimeException {

    public DishNotFoundException(Long id) {
        super("El plato con id " + id + " no existe en el menú.");
    }
}
