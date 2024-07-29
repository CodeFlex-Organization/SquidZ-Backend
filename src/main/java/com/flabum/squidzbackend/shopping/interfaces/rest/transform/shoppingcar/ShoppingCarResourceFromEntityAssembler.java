package com.flabum.squidzbackend.shopping.interfaces.rest.transform.shoppingcar;

import com.flabum.squidzbackend.shopping.domain.model.entities.ShoppingCar;
import com.flabum.squidzbackend.shopping.interfaces.rest.resources.shoppingcar.ShoppingCarResource;

public class ShoppingCarResourceFromEntityAssembler {
    public static ShoppingCarResource toResourceFromEntity(ShoppingCar entity) {
        return new ShoppingCarResource(
                entity.getId(),
                entity.getItems()
        );
    }
}
