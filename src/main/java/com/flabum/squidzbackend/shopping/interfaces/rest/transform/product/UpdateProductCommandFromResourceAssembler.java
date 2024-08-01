package com.flabum.squidzbackend.shopping.interfaces.rest.transform.product;

import com.flabum.squidzbackend.shopping.domain.model.commands.product.UpdateProductCommand;
import com.flabum.squidzbackend.shopping.interfaces.rest.resources.product.UpdateProductResource;

public class UpdateProductCommandFromResourceAssembler {
    public static UpdateProductCommand toCommandFromResource(Long id, UpdateProductResource resource) {
        return new UpdateProductCommand(
                id,
                resource.name(),
                resource.description(),
                resource.price(),
                resource.productCategory()
        );
    }
}
