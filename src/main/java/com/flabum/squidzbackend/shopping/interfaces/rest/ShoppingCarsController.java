package com.flabum.squidzbackend.shopping.interfaces.rest;

import com.flabum.squidzbackend.shopping.domain.model.commands.shoppingcar.DeleteShoppingCarCommand;
import com.flabum.squidzbackend.shopping.domain.model.queries.shoppingcar.GetShoppingCarByIdQuery;
import com.flabum.squidzbackend.shopping.domain.services.shoppingcar.ShoppingCarCommandService;
import com.flabum.squidzbackend.shopping.domain.services.shoppingcar.ShoppingCarQueryService;
import com.flabum.squidzbackend.shopping.interfaces.rest.resources.shoppingcar.CreateShoppingCarResource;
import com.flabum.squidzbackend.shopping.interfaces.rest.resources.shoppingcar.ShoppingCarResource;
import com.flabum.squidzbackend.shopping.interfaces.rest.transform.shoppingcar.CreateShoppingCarCommandFromResourceAssembler;
import com.flabum.squidzbackend.shopping.interfaces.rest.transform.shoppingcar.ShoppingCarResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/shopping_car", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Shopping Cars", description = "Shopping Car Management Endpoint")
public class ShoppingCarsController {
    private final ShoppingCarCommandService shoppingCarCommandService;
    private final ShoppingCarQueryService shoppingCarQueryService;

    @PostMapping
    public ResponseEntity<ShoppingCarResource> createShoppingCar(@RequestBody CreateShoppingCarResource createShoppingCarResource) {
        var createShoppingCarCommand = CreateShoppingCarCommandFromResourceAssembler.toCommandFromResource(createShoppingCarResource);
        var shoppingCar = shoppingCarCommandService.handle(createShoppingCarCommand);
        if (shoppingCar.isEmpty()) { return ResponseEntity.badRequest().build(); }
        var shoppingCarResource = ShoppingCarResourceFromEntityAssembler.toResourceFromEntity(shoppingCar.get());
        return new ResponseEntity<>(shoppingCarResource, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShoppingCarResource> getShoppingCarById(@PathVariable Long id) {
        var getShoppingCarById = new GetShoppingCarByIdQuery(id);
        var shoppingCar = shoppingCarQueryService.handle(getShoppingCarById);
        if (shoppingCar.isEmpty()) { return ResponseEntity.notFound().build(); }
        var shoppingCarResource = ShoppingCarResourceFromEntityAssembler.toResourceFromEntity(shoppingCar.get());
        return ResponseEntity.ok(shoppingCarResource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteShoppingCar(@PathVariable Long id) {
        var deleteShoppingCarCommand = new DeleteShoppingCarCommand(id);
        shoppingCarCommandService.handle(deleteShoppingCarCommand);
        return ResponseEntity.ok("Shopping car deleted successfully.");
    }
}
