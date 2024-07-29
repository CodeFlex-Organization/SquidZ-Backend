package com.flabum.squidzbackend.shopping.application.internal.queryservices;

import com.flabum.squidzbackend.shopping.domain.model.aggregates.SalesOrder;
import com.flabum.squidzbackend.shopping.domain.model.queries.GetAllSalesOrdersQuery;
import com.flabum.squidzbackend.shopping.domain.model.queries.GetSalesOrderByIdQuery;
import com.flabum.squidzbackend.shopping.domain.services.SalesOrderQueryService;
import com.flabum.squidzbackend.shopping.infrastructure.persistence.jpa.repositories.SalesOrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalesOrderQueryServiceImpl implements SalesOrderQueryService {
    private final SalesOrderRepository salesOrderRepository;

    public SalesOrderQueryServiceImpl(SalesOrderRepository salesOrderRepository) {
        this.salesOrderRepository = salesOrderRepository;
    }

    @Override
    public List<SalesOrder> handle(GetAllSalesOrdersQuery query) {
        return salesOrderRepository.findAll();
    }

    @Override
    public Optional<SalesOrder> handle(GetSalesOrderByIdQuery query) {
        return salesOrderRepository.findById(query.id());
    }
}
