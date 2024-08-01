package com.flabum.squidzbackend.shopping.application.internal.queryservices;

import com.flabum.squidzbackend.shopping.domain.model.entities.ShoppingCar;
import com.flabum.squidzbackend.shopping.domain.model.queries.shoppingcar.GetShoppingCarByIdQuery;
import com.flabum.squidzbackend.shopping.domain.services.shoppingcar.ShoppingCarQueryService;
import com.flabum.squidzbackend.shopping.infrastructure.persistence.jpa.repositories.ShoppingCarRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShoppingCarQueryServiceImpl implements ShoppingCarQueryService {
    private final ShoppingCarRepository shoppingCarRepository;

    public ShoppingCarQueryServiceImpl(ShoppingCarRepository shoppingCarRepository) {
        this.shoppingCarRepository = shoppingCarRepository;
    }

    @Override
    public Optional<ShoppingCar> handle(GetShoppingCarByIdQuery query) {
        return shoppingCarRepository.findById(query.id());
    }
}
