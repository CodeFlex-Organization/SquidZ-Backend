package com.flabum.squidzbackend.shopping.domain.model.commands.salesorder;

import com.flabum.squidzbackend.shopping.domain.model.entities.ShoppingCar;

public record CreateSalesOrderCommand(ShoppingCar shoppingCar) {
}
