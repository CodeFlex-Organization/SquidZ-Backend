package com.flabum.squidzbackend.shopping.interfaces.acl;

import com.flabum.squidzbackend.shopping.domain.model.commands.salesorder.CreateSalesOrderCommand;
import com.flabum.squidzbackend.shopping.domain.model.entities.ShoppingCar;
import com.flabum.squidzbackend.shopping.domain.model.queries.salesorder.GetSalesOrderByIdQuery;
import com.flabum.squidzbackend.shopping.domain.services.salesorder.SalesOrderCommandService;
import com.flabum.squidzbackend.shopping.domain.services.salesorder.SalesOrderQueryService;

public class SalesOrderContextFacade {
    private final SalesOrderCommandService salesOrderCommandService;
    private final SalesOrderQueryService salesOrderQueryService;

    public SalesOrderContextFacade(SalesOrderCommandService salesOrderCommandService, SalesOrderQueryService salesOrderQueryService) {
        this.salesOrderCommandService = salesOrderCommandService;
        this.salesOrderQueryService = salesOrderQueryService;
    }

    public Long createSalesOrder(ShoppingCar shoppingCar) {
        var createSalesOrderCommand = new CreateSalesOrderCommand(shoppingCar);
        var salesOrder = salesOrderCommandService.handle(createSalesOrderCommand);
        if (salesOrder.isEmpty()) return null;
        return salesOrder.get().getId();
    }

    public Long fetchSalesOrderById(Long id) {
        var getSalesOrderByIdQuery = new GetSalesOrderByIdQuery(id);
        var salesOrder = salesOrderQueryService.handle(getSalesOrderByIdQuery);
        if (salesOrder.isEmpty()) return null;
        return salesOrder.get().getId();
    }
}
