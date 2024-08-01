package com.flabum.squidzbackend.shopping.domain.services.shoppingcar;

import com.flabum.squidzbackend.shopping.domain.model.entities.ShoppingCar;
import com.flabum.squidzbackend.shopping.domain.model.queries.shoppingcar.GetShoppingCarByIdQuery;

import java.util.Optional;

public interface ShoppingCarQueryService {
    Optional<ShoppingCar> handle(GetShoppingCarByIdQuery query);
}
