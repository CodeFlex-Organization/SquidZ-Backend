package com.flabum.squidzbackend.shopping.domain.services.shoppingcar;

import com.flabum.squidzbackend.shopping.domain.model.commands.shoppingcar.CreateShoppingCarCommand;
import com.flabum.squidzbackend.shopping.domain.model.commands.shoppingcar.DeleteShoppingCarCommand;
import com.flabum.squidzbackend.shopping.domain.model.commands.shoppingcar.UpdateShoppingCarCommand;
import com.flabum.squidzbackend.shopping.domain.model.entities.ShoppingCar;

import java.util.Optional;

public interface ShoppingCarCommandService {
    Optional<ShoppingCar> handle(CreateShoppingCarCommand command);
    Optional<ShoppingCar> handle(UpdateShoppingCarCommand command);
    void handle(DeleteShoppingCarCommand command);
}
