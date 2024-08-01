package com.flabum.squidzbackend.shopping.domain.services.salesorder;

import com.flabum.squidzbackend.shopping.domain.model.aggregates.SalesOrder;
import com.flabum.squidzbackend.shopping.domain.model.commands.salesorder.*;

import java.util.Optional;

public interface SalesOrderCommandService {
    Optional<SalesOrder> handle(CreateSalesOrderCommand command);
    void handle(DeleteSalesOrderCommand command);

    Long handle(ConfirmSalesOrderCommand command);
    Long handle(ShipSalesOrderCommand command);
    Long handle(DeliverSalesOrderCommand command);
    Long handle(CancelSalesOrderCommand command);
}
