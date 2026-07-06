package com.restaurantetech.svcmenu.dto;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DishResponse {

    private Long id;
    private String name;
    private String description;
    private String category;
    private Double price;
    private Boolean available;
}
