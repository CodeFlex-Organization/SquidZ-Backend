package com.flabum.squidzbackend.shopping.interfaces.rest.resources.shoppingcar;

import com.flabum.squidzbackend.shopping.domain.model.entities.SalesOrderItem;
import com.flabum.squidzbackend.shopping.domain.model.valueobjects.ProductCategories;

import java.util.List;

public record UpdateShoppingCarResource(List<SalesOrderItem> items) {}
