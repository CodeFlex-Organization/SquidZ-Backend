package com.flabum.squidzbackend.shopping.application.internal.commandservices;

import com.flabum.squidzbackend.shopping.domain.model.aggregates.SalesOrder;
import com.flabum.squidzbackend.shopping.domain.model.commands.salesorder.*;
import com.flabum.squidzbackend.shopping.domain.services.salesorder.SalesOrderCommandService;
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

    @Override
    public Long handle(ConfirmSalesOrderCommand command) {
        salesOrderRepository.findById(command.id()).map(so -> {
            so.confirmSalesOrder();
            salesOrderRepository.save(so);
            return command.id();
        }).orElseThrow(() -> new RuntimeException("Sales order not found"));
        return null;
    }

    @Override
    public Long handle(ShipSalesOrderCommand command) {
        salesOrderRepository.findById(command.id()).map(so -> {
            so.shipSalesOrder();
            salesOrderRepository.save(so);
            return command.id();
        }).orElseThrow(() -> new RuntimeException("Sales order not found"));
        return null;
    }

    @Override
    public Long handle(DeliverSalesOrderCommand command) {
        salesOrderRepository.findById(command.id()).map(so -> {
            so.deliverSalesOrder();
            salesOrderRepository.save(so);
            return command.id();
        }).orElseThrow(() -> new RuntimeException("Sales order not found"));
        return null;
    }

    @Override
    public Long handle(CancelSalesOrderCommand command) {
        salesOrderRepository.findById(command.id()).map(so -> {
            so.cancelSalesOrder();
            salesOrderRepository.save(so);
            return command.id();
        }).orElseThrow(() -> new RuntimeException("Sales order not found"));
        return null;
    }
}
