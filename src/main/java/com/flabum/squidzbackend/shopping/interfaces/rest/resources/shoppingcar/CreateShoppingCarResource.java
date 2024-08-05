package com.flabum.squidzbackend.shopping.interfaces.rest.resources.shoppingcar;

import com.flabum.squidzbackend.shopping.domain.model.entities.SalesOrderItem;

import java.util.List;

public record CreateShoppingCarResource(
        List<SalesOrderItem> items
) {
}