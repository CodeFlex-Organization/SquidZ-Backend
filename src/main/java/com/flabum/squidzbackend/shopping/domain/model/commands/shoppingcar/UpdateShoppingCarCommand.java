package com.flabum.squidzbackend.shopping.domain.model.commands.shoppingcar;

import com.flabum.squidzbackend.shopping.domain.model.entities.SalesOrderItem;

import java.util.List;

public record UpdateShoppingCarCommand(Long id, List<SalesOrderItem> items) {
}
