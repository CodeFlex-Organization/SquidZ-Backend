package com.flabum.squidzbackend.shopping.interfaces.rest.resources;

import com.flabum.squidzbackend.shopping.domain.model.entities.ShoppingCar;

public record CreateSalesOrderResource(
    ShoppingCar shoppingCar
) {
}
