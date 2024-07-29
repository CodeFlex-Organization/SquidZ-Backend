package com.flabum.squidzbackend.shopping.domain.model.commands.shoppingcar;

import com.flabum.squidzbackend.shopping.domain.model.entities.SalesOrderItem;

import java.util.List;

public record CreateShoppingCarCommand(List<SalesOrderItem> items) {
}
