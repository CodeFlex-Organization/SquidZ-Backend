package com.flabum.squidzbackend.shopping.domain.services;

import com.flabum.squidzbackend.shopping.domain.model.aggregates.SalesOrder;
import com.flabum.squidzbackend.shopping.domain.model.commands.CreateSalesOrderCommand;
import com.flabum.squidzbackend.shopping.domain.model.commands.DeleteSalesOrderCommand;

import java.util.Optional;

public interface SalesOrderCommandService {
    Optional<SalesOrder> handle(CreateSalesOrderCommand command);
    void handle(DeleteSalesOrderCommand command);
}
