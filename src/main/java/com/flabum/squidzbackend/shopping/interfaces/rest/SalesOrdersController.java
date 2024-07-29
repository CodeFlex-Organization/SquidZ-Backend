package com.flabum.squidzbackend.shopping.interfaces.rest;

import com.flabum.squidzbackend.shopping.domain.model.commands.salesorder.DeleteSalesOrderCommand;
import com.flabum.squidzbackend.shopping.domain.model.queries.salesorder.GetAllSalesOrdersQuery;
import com.flabum.squidzbackend.shopping.domain.model.queries.salesorder.GetSalesOrderByIdQuery;
import com.flabum.squidzbackend.shopping.domain.services.salesorder.SalesOrderCommandService;
import com.flabum.squidzbackend.shopping.domain.services.salesorder.SalesOrderQueryService;
import com.flabum.squidzbackend.shopping.interfaces.rest.resources.salesorder.CreateSalesOrderResource;
import com.flabum.squidzbackend.shopping.interfaces.rest.resources.salesorder.SalesOrderResource;
import com.flabum.squidzbackend.shopping.interfaces.rest.transform.salesorder.CreateSalesOrderCommandFromResourceAssembler;
import com.flabum.squidzbackend.shopping.interfaces.rest.transform.salesorder.SalesOrderResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/sales_orders", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "SalesOrders", description = "Sales Order Management Endpoint")
public class SalesOrdersController {
    private final SalesOrderCommandService salesOrderCommandService;
    private final SalesOrderQueryService salesOrderQueryService;

    @PostMapping
    public ResponseEntity<SalesOrderResource> createSalesOrder(@RequestBody CreateSalesOrderResource createSalesOrderResource) {
        var createSalesOrderCommand = CreateSalesOrderCommandFromResourceAssembler.toCommandFromResource(createSalesOrderResource);
        var salesOrder = salesOrderCommandService.handle(createSalesOrderCommand);
        if (salesOrder.isEmpty()) { return ResponseEntity.badRequest().build(); }
        var salesOrderResource = SalesOrderResourceFromEntityAssembler.toResourceFromEntity(salesOrder.get());
        return new ResponseEntity<>(salesOrderResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SalesOrderResource>> getAllSalesOrders() {
        var getAllSalesOrdersQuery = new GetAllSalesOrdersQuery();
        var salesOrders = salesOrderQueryService.handle(getAllSalesOrdersQuery);
        var salesOrderResources = salesOrders.stream()
                .map(SalesOrderResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(salesOrderResources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalesOrderResource> getSalesOrderById(@PathVariable Long id) {
        var getSalesOrderById = new GetSalesOrderByIdQuery(id);
        var salesOrder = salesOrderQueryService.handle(getSalesOrderById);
        if (salesOrder.isEmpty()) { return ResponseEntity.notFound().build(); }
        var salesOrderResource = SalesOrderResourceFromEntityAssembler.toResourceFromEntity(salesOrder.get());
        return ResponseEntity.ok(salesOrderResource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSalesOrder(@PathVariable Long id) {
        var deleteSalesOrderCommand = new DeleteSalesOrderCommand(id);
        salesOrderCommandService.handle(deleteSalesOrderCommand);
        return ResponseEntity.ok("Sales order deleted successfully.");
    }
}
