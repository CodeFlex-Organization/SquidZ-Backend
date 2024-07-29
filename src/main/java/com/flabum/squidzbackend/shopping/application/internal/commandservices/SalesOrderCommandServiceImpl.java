package com.flabum.squidzbackend.shopping.application.internal.commandservices;

import com.flabum.squidzbackend.shopping.domain.model.aggregates.SalesOrder;
import com.flabum.squidzbackend.shopping.domain.model.commands.CreateSalesOrderCommand;
import com.flabum.squidzbackend.shopping.domain.model.commands.DeleteSalesOrderCommand;
import com.flabum.squidzbackend.shopping.domain.services.SalesOrderCommandService;
import com.flabum.squidzbackend.shopping.infrastructure.persistence.jpa.repositories.SalesOrderRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SalesOrderCommandServiceImpl implements SalesOrderCommandService {
    private final SalesOrderRepository salesOrderRepository;

    public SalesOrderCommandServiceImpl(SalesOrderRepository salesOrderRepository) {
        this.salesOrderRepository = salesOrderRepository;
    }

    @Override
    public Optional<SalesOrder> handle(CreateSalesOrderCommand command) {
        var salesOrder = new SalesOrder(command.shoppingCar());
        salesOrderRepository.save(salesOrder);
        return Optional.of(salesOrder);
    }

    @Override
    public void handle(DeleteSalesOrderCommand command) {
        if (!salesOrderRepository.existsById(command.id())) {
            throw new IllegalArgumentException("This sales order does not exists.");
        }
        try {
            salesOrderRepository.deleteById(command.id());
        } catch (Exception e){
            throw new IllegalArgumentException("Error while deleting sales order: " + e.getMessage() + ".");
        }
    }
}
