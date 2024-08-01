package com.flabum.squidzbackend.shopping.interfaces.rest.transform.product;

import com.flabum.squidzbackend.shopping.domain.model.commands.product.CreateProductCommand;
import com.flabum.squidzbackend.shopping.interfaces.rest.resources.product.CreateProductResource;

public class CreateProductCommandFromResourceAssembler {
    public static CreateProductCommand toCommandFromResource(CreateProductResource resource) {
        return new CreateProductCommand(
                resource.name(),
                resource.description(),
                resource.price(),
                resource.productCategory()
        );
    }
}
