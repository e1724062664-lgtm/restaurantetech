package com.restaurantetech.svcmenu.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;


@Entity
@Table(name = "dishes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del plato es obligatorio")
    @Size(max = 100, message = "El nombre no puede superar 100 caracteres")
    @Column(nullable = false, length = 100)
    private String name;


    @Size(max = 255, message = "La descripción no puede superar 255 caracteres")
    @Column(length = 255)
    private String description;


    @NotBlank(message = "La categoría es obligatoria")
    @Column(nullable = false)
    private String category;

    //Precio por unidad. Obligatorio y mayor que cero
    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser mayor que cero")
    @Column(nullable = false)
    private Double price;

    //Indica si el plato está disponible para pedir. Por defecto true
    @Column(nullable = false)
    @Builder.Default
    private Boolean available = true;
}
