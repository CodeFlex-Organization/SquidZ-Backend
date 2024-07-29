package com.flabum.squidzbackend.shopping.interfaces.rest;

import com.flabum.squidzbackend.shopping.domain.model.commands.product.DeleteProductCommand;
import com.flabum.squidzbackend.shopping.domain.model.commands.salesorder.DeleteSalesOrderCommand;
import com.flabum.squidzbackend.shopping.domain.model.queries.product.GetAllProductsQuery;
import com.flabum.squidzbackend.shopping.domain.model.queries.product.GetProductByIdQuery;
import com.flabum.squidzbackend.shopping.domain.model.queries.salesorder.GetSalesOrderByIdQuery;
import com.flabum.squidzbackend.shopping.domain.services.product.ProductCommandService;
import com.flabum.squidzbackend.shopping.domain.services.product.ProductQueryService;
import com.flabum.squidzbackend.shopping.interfaces.rest.resources.product.CreateProductResource;
import com.flabum.squidzbackend.shopping.interfaces.rest.resources.product.ProductResource;
import com.flabum.squidzbackend.shopping.interfaces.rest.resources.product.UpdateProductResource;
import com.flabum.squidzbackend.shopping.interfaces.rest.resources.salesorder.CreateSalesOrderResource;
import com.flabum.squidzbackend.shopping.interfaces.rest.resources.salesorder.SalesOrderResource;
import com.flabum.squidzbackend.shopping.interfaces.rest.resources.shoppingcar.ShoppingCarResource;
import com.flabum.squidzbackend.shopping.interfaces.rest.resources.shoppingcar.UpdateShoppingCarResource;
import com.flabum.squidzbackend.shopping.interfaces.rest.transform.product.CreateProductCommandFromResourceAssembler;
import com.flabum.squidzbackend.shopping.interfaces.rest.transform.product.ProductResourceFromEntityAssembler;
import com.flabum.squidzbackend.shopping.interfaces.rest.transform.product.UpdateProductCommandFromResourceAssembler;
import com.flabum.squidzbackend.shopping.interfaces.rest.transform.salesorder.CreateSalesOrderCommandFromResourceAssembler;
import com.flabum.squidzbackend.shopping.interfaces.rest.transform.salesorder.SalesOrderResourceFromEntityAssembler;
import com.flabum.squidzbackend.shopping.interfaces.rest.transform.shoppingcar.ShoppingCarResourceFromEntityAssembler;
import com.flabum.squidzbackend.shopping.interfaces.rest.transform.shoppingcar.UpdateShoppingCarCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/products", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Products", description = "Product Management Endpoint")
public class ProductsController {
    private final ProductCommandService productCommandService;
    private final ProductQueryService productQueryService;

    @PostMapping
    public ResponseEntity<ProductResource> createProduct(@RequestBody CreateProductResource createProductResource) {
        var createProductCommand = CreateProductCommandFromResourceAssembler.toCommandFromResource(createProductResource);
        var product = productCommandService.handle(createProductCommand);
        if (product.isEmpty()) { return ResponseEntity.badRequest().build(); }
        var productResource = ProductResourceFromEntityAssembler.toResourceFromEntity(product.get());
        return new ResponseEntity<>(productResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductResource>> getAllProducts() {
        var getAllProductsQuery = new GetAllProductsQuery();
        var products = productQueryService.handle(getAllProductsQuery);
        var productResources = products.stream()
                .map(ProductResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(productResources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResource> getProductById(@PathVariable Long id) {
        var getProductByIdQuery = new GetProductByIdQuery(id);
        var product = productQueryService.handle(getProductByIdQuery);
        if (product.isEmpty()) { return ResponseEntity.notFound().build(); }
        var productResource = ProductResourceFromEntityAssembler.toResourceFromEntity(product.get());
        return ResponseEntity.ok(productResource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        var deleteProductCommand = new DeleteProductCommand(id);
        productCommandService.handle(deleteProductCommand);
        return ResponseEntity.ok("Product deleted successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResource> updateProduct(@PathVariable Long id, @RequestBody UpdateProductResource updateProductResource) {
        var updateProductCommand = UpdateProductCommandFromResourceAssembler.toCommandFromResource(id, updateProductResource);
        var updatedProduct = productCommandService.handle(updateProductCommand);
        if (updatedProduct.isEmpty()) { return ResponseEntity.badRequest().build(); }
        var productResource = ProductResourceFromEntityAssembler.toResourceFromEntity(updatedProduct.get());
        return ResponseEntity.ok(productResource);
    }
}
