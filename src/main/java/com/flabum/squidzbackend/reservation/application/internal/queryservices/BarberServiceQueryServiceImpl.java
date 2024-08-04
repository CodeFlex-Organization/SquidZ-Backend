package com.flabum.squidzbackend.reservation.application.internal.queryservices;

import com.flabum.squidzbackend.reservation.domain.model.entities.BarberService;
import com.flabum.squidzbackend.reservation.domain.model.queries.GetAllBarberServicesQuery;
import com.flabum.squidzbackend.reservation.domain.services.BarberServiceQueryService;
import com.flabum.squidzbackend.reservation.infrastructure.persistence.jpa.repositories.BarberServiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BarberServiceQueryServiceImpl implements BarberServiceQueryService {
    private final BarberServiceRepository barberServiceRepository;

    public BarberServiceQueryServiceImpl(BarberServiceRepository barberServiceRepository) {
        this.barberServiceRepository = barberServiceRepository;
    }

    @Override
    public List<BarberService> handle(GetAllBarberServicesQuery command) {
        return barberServiceRepository.findAll();
    }

}
