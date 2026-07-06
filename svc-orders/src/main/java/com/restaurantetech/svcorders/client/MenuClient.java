package com.restaurantetech.svcorders.client;

import com.restaurantetech.svcorders.dto.MenuResponse;
import com.restaurantetech.svcorders.exception.DishNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;


@Slf4j
@Component
@RequiredArgsConstructor
public class MenuClient {

    private final WebClient webClient;

    public MenuResponse getDishById(Long dishId) {
        log.info("[WebClient] GET /api/menu/dishes/{} → svc-menu", dishId);
        try {
            MenuResponse response = webClient.get()
                    .uri("/api/menu/dishes/{id}", dishId)
                    .retrieve()
                    .bodyToMono(MenuResponse.class)
                    .block(); // bloqueante para integración con capa de servicio no reactiva

            log.info("[WebClient] Plato obtenido: {} - available: {}",
                    response != null ? response.getName() : "null",
                    response != null ? response.getAvailable() : "null");

            return response;

        } catch (WebClientResponseException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                log.warn("[WebClient] Plato con id {} no encontrado en svc-menu", dishId);
                throw new DishNotFoundException(dishId);
            }
            log.error("[WebClient] Error al consultar svc-menu para dishId {}: {}",
                    dishId, ex.getMessage());
            throw ex;
        }
    }
}
