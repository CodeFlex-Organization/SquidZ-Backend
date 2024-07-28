package com.flabum.squidzbackend.shopping.interfaces.rest;

import com.flabum.squidzbackend.shopping.domain.model.queries.GetAllProductsQuery;
import com.flabum.squidzbackend.shopping.domain.services.ProductQueryService;
import com.flabum.squidzbackend.shopping.interfaces.rest.resources.ProductResource;
import com.flabum.squidzbackend.shopping.interfaces.rest.resources.SalesOrderResource;
import com.flabum.squidzbackend.shopping.interfaces.rest.transform.ProductResourceFromEntityAssembler;
import com.flabum.squidzbackend.shopping.interfaces.rest.transform.SalesOrderResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/products", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Products", description = "Product Management Endpoint")
public class ProductsController {
    private final ProductQueryService productQueryService;

    @GetMapping
    public ResponseEntity<List<ProductResource>> getAllProducts() {
        var getAllProductsQuery = new GetAllProductsQuery();
        var products = productQueryService.handle(getAllProductsQuery);
        var productResources = products.stream()
                .map(ProductResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(productResources);
    }
}
