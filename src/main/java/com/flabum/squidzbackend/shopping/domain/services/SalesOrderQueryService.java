package com.flabum.squidzbackend.shopping.domain.services;

import com.flabum.squidzbackend.shopping.domain.model.aggregates.SalesOrder;
import com.flabum.squidzbackend.shopping.domain.model.queries.GetAllSalesOrdersQuery;
import com.flabum.squidzbackend.shopping.domain.model.queries.GetSalesOrderByIdQuery;

import java.util.List;
import java.util.Optional;

public interface SalesOrderQueryService {
    List<SalesOrder> handle(GetAllSalesOrdersQuery query);
    Optional<SalesOrder> handle(GetSalesOrderByIdQuery query);
}
