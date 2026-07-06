package com.restaurantetech.svcmenu.repository;

import com.restaurantetech.svcmenu.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {
}
