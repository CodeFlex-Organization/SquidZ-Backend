package com.flabum.squidzbackend.shopping.interfaces.rest.transform;

import com.flabum.squidzbackend.shopping.domain.model.commands.CreateSalesOrderCommand;
import com.flabum.squidzbackend.shopping.interfaces.rest.resources.CreateSalesOrderResource;

public class CreateSalesOrderCommandFromResourceAssembler {
    public static CreateSalesOrderCommand toCommandFromResource(CreateSalesOrderResource resource) {
        return new CreateSalesOrderCommand(
                resource.shoppingCar()
        );
    }
}
