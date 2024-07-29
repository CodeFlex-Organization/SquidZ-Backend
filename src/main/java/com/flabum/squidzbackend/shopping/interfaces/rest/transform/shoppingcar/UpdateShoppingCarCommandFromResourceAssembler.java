package com.flabum.squidzbackend.shopping.interfaces.rest.transform.shoppingcar;

import com.flabum.squidzbackend.shopping.domain.model.commands.shoppingcar.UpdateShoppingCarCommand;
import com.flabum.squidzbackend.shopping.interfaces.rest.resources.shoppingcar.UpdateShoppingCarResource;

public class UpdateShoppingCarCommandFromResourceAssembler {
    public static UpdateShoppingCarCommand toCommandFromResource(Long id, UpdateShoppingCarResource resource) {
        return new UpdateShoppingCarCommand(
                id,
                resource.items()
        );
    }
}
