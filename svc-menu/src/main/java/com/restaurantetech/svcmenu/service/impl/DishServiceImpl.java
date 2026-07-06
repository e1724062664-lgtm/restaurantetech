package com.restaurantetech.svcmenu.service.impl;

import com.restaurantetech.svcmenu.dto.DishRequest;
import com.restaurantetech.svcmenu.dto.DishResponse;
import com.restaurantetech.svcmenu.model.Dish;
import com.restaurantetech.svcmenu.exception.DishNotFoundException;
import com.restaurantetech.svcmenu.repository.DishRepository;
import com.restaurantetech.svcmenu.service.DishService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class DishServiceImpl implements DishService {

    private final DishRepository dishRepository;

    @Override
    @Transactional
    public DishResponse create(DishRequest request) {
        log.info("Creando plato: {}", request.getName());
        Dish dish = Dish.builder()
                .name(request.getName())
                .description(request.getDescription())
                .category(request.getCategory())
                .price(request.getPrice())
                .available(request.getAvailable() != null ? request.getAvailable() : true)
                .build();
        Dish saved = dishRepository.save(dish);
        log.info("Plato creado con id: {}", saved.getId());
        return toResponse(saved);
    }

    // Retorna la lista completa de platos del menú.

    @Override
    @Transactional(readOnly = true)
    public List<DishResponse> findAll() {
        log.info("Listando todos los platos");
        return dishRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public DishResponse findById(Long id) {
        log.info("Buscando plato con id: {}", id);
        Dish dish = dishRepository.findById(id)
                .orElseThrow(() -> new DishNotFoundException(id));
        return toResponse(dish);
    }

    //Actualiza todos los campos de un plato existente.

    @Override
    @Transactional
    public DishResponse update(Long id, DishRequest request) {
        log.info("Actualizando plato con id: {}", id);
        Dish dish = dishRepository.findById(id)
                .orElseThrow(() -> new DishNotFoundException(id));
        dish.setName(request.getName());
        dish.setDescription(request.getDescription());
        dish.setCategory(request.getCategory());
        dish.setPrice(request.getPrice());
        if (request.getAvailable() != null) {
            dish.setAvailable(request.getAvailable());
        }
        Dish updated = dishRepository.save(dish);
        log.info("Plato actualizado con id: {}", updated.getId());
        return toResponse(updated);
    }

    //Elimina un plato por ID

    @Override
    @Transactional
    public void delete(Long id) {
        log.info("Eliminando plato con id: {}", id);
        if (!dishRepository.existsById(id)) {
            throw new DishNotFoundException(id);
        }
        dishRepository.deleteById(id);
        log.info("Plato eliminado con id: {}", id);
    }

    // Mapper

    private DishResponse toResponse(Dish dish) {
        return DishResponse.builder()
                .id(dish.getId())
                .name(dish.getName())
                .description(dish.getDescription())
                .category(dish.getCategory())
                .price(dish.getPrice())
                .available(dish.getAvailable())
                .build();
    }
}

