package com.flabum.squidzbackend.shopping.interfaces.rest.transform.salesorder;

import com.flabum.squidzbackend.shopping.domain.model.aggregates.SalesOrder;
import com.flabum.squidzbackend.shopping.interfaces.rest.resources.salesorder.SalesOrderResource;

public class SalesOrderResourceFromEntityAssembler {
        public static SalesOrderResource toResourceFromEntity(SalesOrder entity) {
            return new SalesOrderResource(
                    entity.getId(),
                    entity.getShoppingCar()
            );
        }
}
