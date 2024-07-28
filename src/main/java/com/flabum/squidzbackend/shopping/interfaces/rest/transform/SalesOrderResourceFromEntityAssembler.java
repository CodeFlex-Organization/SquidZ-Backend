package com.flabum.squidzbackend.shopping.interfaces.rest.transform;

import com.flabum.squidzbackend.shopping.domain.model.aggregates.SalesOrder;
import com.flabum.squidzbackend.shopping.interfaces.rest.resources.SalesOrderResource;

public class SalesOrderResourceFromEntityAssembler {
        public static SalesOrderResource toResourceFromEntity(SalesOrder entity) {
            return new SalesOrderResource(
                    entity.getId(),
                    entity.getShoppingCar()
            );
        }
}
