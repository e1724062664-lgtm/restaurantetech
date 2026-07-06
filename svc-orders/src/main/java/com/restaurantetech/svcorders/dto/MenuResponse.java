package com.restaurantetech.svcorders.dto;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuResponse {

    private Long id;
    private String name;
    private String description;
    private String category;
    private Double price;
    private Boolean available;
}
