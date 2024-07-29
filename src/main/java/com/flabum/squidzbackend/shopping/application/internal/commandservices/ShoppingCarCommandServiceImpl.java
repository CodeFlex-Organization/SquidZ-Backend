package com.flabum.squidzbackend.shopping.application.internal.commandservices;

import com.flabum.squidzbackend.shopping.domain.model.commands.shoppingcar.CreateShoppingCarCommand;
import com.flabum.squidzbackend.shopping.domain.model.commands.shoppingcar.DeleteShoppingCarCommand;
import com.flabum.squidzbackend.shopping.domain.model.commands.shoppingcar.UpdateShoppingCarCommand;
import com.flabum.squidzbackend.shopping.domain.model.entities.ShoppingCar;
import com.flabum.squidzbackend.shopping.domain.services.shoppingcar.ShoppingCarCommandService;
import com.flabum.squidzbackend.shopping.infrastructure.persistence.jpa.repositories.ShoppingCarRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShoppingCarCommandServiceImpl implements ShoppingCarCommandService {
    private final ShoppingCarRepository shoppingCarRepository;

    public ShoppingCarCommandServiceImpl(ShoppingCarRepository shoppingCarRepository) {
        this.shoppingCarRepository = shoppingCarRepository;
    }

    @Override
    public Optional<ShoppingCar> handle(CreateShoppingCarCommand command) {
        var shoppingCar = new ShoppingCar();
        shoppingCarRepository.save(shoppingCar);
        return Optional.of(shoppingCar);
    }

    @Override
    public Optional<ShoppingCar> handle(UpdateShoppingCarCommand command) {
        var shoppingCarFound = shoppingCarRepository.findById(command.id());
        if (shoppingCarFound.isEmpty()) {
            throw new IllegalArgumentException("This shopping car does not exists.");
        }
        var shoppingCarToUpdate = shoppingCarFound.get();
        try {
            var updatedShoppingCar = shoppingCarRepository.save(shoppingCarToUpdate.updateShoppingCar(command.items()));
            return Optional.of(updatedShoppingCar);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating shopping car: " + e.getMessage() + ".");
        }
    }

    @Override
    public void handle(DeleteShoppingCarCommand command) {
        if (!shoppingCarRepository.existsById(command.id())) {
            throw new IllegalArgumentException("This shopping car does not exists.");
        }
        try {
            shoppingCarRepository.deleteById(command.id());
        } catch (Exception e){
            throw new IllegalArgumentException("Error while deleting shopping car: " + e.getMessage() + ".");
        }
    }
}
