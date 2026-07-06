package com.restaurantetech.svcmenu.dto;

import jakarta.validation.constraints.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DishRequest {

    @NotBlank(message = "El nombre del plato es obligatorio")
    @Size(max = 100, message = "El nombre no puede superar 100 caracteres")
    private String name;

    @Size(max = 255, message = "La descripción no puede superar 255 caracteres")
    private String description;

    @NotBlank(message = "La categoría es obligatoria")
    private String category;

    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser mayor que cero")
    private Double price;

    /** Si no se envía, se tomará como true por defecto en el servicio. */
    private Boolean available;
}
