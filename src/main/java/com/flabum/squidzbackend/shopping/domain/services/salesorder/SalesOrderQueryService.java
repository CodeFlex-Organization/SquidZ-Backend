package com.flabum.squidzbackend.shopping.domain.services.salesorder;

import com.flabum.squidzbackend.shopping.domain.model.aggregates.SalesOrder;
import com.flabum.squidzbackend.shopping.domain.model.queries.salesorder.GetAllSalesOrdersQuery;
import com.flabum.squidzbackend.shopping.domain.model.queries.salesorder.GetSalesOrderByIdQuery;

import java.util.List;
import java.util.Optional;

public interface SalesOrderQueryService {
    List<SalesOrder> handle(GetAllSalesOrdersQuery query);
    Optional<SalesOrder> handle(GetSalesOrderByIdQuery query);
}
