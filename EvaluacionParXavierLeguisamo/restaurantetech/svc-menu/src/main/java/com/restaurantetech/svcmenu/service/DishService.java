package com.restaurantetech.svcmenu.service;

import com.restaurantetech.svcmenu.dto.DishRequest;
import com.restaurantetech.svcmenu.dto.DishResponse;

import java.util.List;


public interface DishService {


    DishResponse create(DishRequest request);

    List<DishResponse> findAll();

    DishResponse findById(Long id);

    DishResponse update(Long id, DishRequest request);

    void delete(Long id);
}
