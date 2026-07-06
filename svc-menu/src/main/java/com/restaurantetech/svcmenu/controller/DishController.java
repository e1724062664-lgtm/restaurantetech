package com.restaurantetech.svcmenu.controller;

import com.restaurantetech.svcmenu.dto.DishRequest;
import com.restaurantetech.svcmenu.dto.DishResponse;
import com.restaurantetech.svcmenu.service.DishService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/api/menu/dishes")
@RequiredArgsConstructor
public class DishController {

    private final DishService dishService;


    //Crea un nuevo plato

    @PostMapping
    public ResponseEntity<DishResponse> create(@Valid @RequestBody DishRequest request) {
        log.info("POST /api/menu/dishes - name: {}", request.getName());
        DishResponse response = dishService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    //Retorna la lista completa de platos. HTTP 200.

    @GetMapping
    public ResponseEntity<List<DishResponse>> findAll() {
        log.info("GET /api/menu/dishes");
        return ResponseEntity.ok(dishService.findAll());
    }

    //Retorna el plato con el ID dado. HTTP 200 si existe, 404 si no.

    @GetMapping("/{id}")
    public ResponseEntity<DishResponse> findById(@PathVariable Long id) {
        log.info("GET /api/menu/dishes/{}", id);
        return ResponseEntity.ok(dishService.findById(id));
    }

    //Actualiza un plato existente. HTTP 200 con el plato actualizado.

    @PutMapping("/{id}")
    public ResponseEntity<DishResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody DishRequest request) {
        log.info("PUT /api/menu/dishes/{}", id);
        return ResponseEntity.ok(dishService.update(id, request));
    }

    //Elimina un plato. HTTP 204 No Content sin body.

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("DELETE /api/menu/dishes/{}", id);
        dishService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
