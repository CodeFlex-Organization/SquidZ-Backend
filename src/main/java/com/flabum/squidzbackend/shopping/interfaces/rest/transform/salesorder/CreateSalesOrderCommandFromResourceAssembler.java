package com.flabum.squidzbackend.shopping.interfaces.rest.transform.salesorder;

import com.flabum.squidzbackend.shopping.domain.model.commands.salesorder.CreateSalesOrderCommand;
import com.flabum.squidzbackend.shopping.interfaces.rest.resources.salesorder.CreateSalesOrderResource;

public class CreateSalesOrderCommandFromResourceAssembler {
    public static CreateSalesOrderCommand toCommandFromResource(CreateSalesOrderResource resource) {
        return new CreateSalesOrderCommand(
                resource.shoppingCar()
        );
    }
}
