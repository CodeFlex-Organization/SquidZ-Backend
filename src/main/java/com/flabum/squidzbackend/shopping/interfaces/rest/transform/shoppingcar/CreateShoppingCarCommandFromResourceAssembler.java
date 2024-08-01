package com.flabum.squidzbackend.shopping.interfaces.rest.transform.shoppingcar;

import com.flabum.squidzbackend.shopping.domain.model.commands.shoppingcar.CreateShoppingCarCommand;
import com.flabum.squidzbackend.shopping.interfaces.rest.resources.shoppingcar.CreateShoppingCarResource;

public class CreateShoppingCarCommandFromResourceAssembler {
    public static CreateShoppingCarCommand toCommandFromResource(CreateShoppingCarResource resource) {
        return new CreateShoppingCarCommand(
                resource.items()
        );
    }
}
