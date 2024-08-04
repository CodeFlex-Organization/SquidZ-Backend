package com.flabum.squidzbackend.reservation.interfaces.rest;

import com.flabum.squidzbackend.reservation.domain.model.queries.GetAllBarberServicesQuery;
import com.flabum.squidzbackend.reservation.domain.model.queries.GetAllReservationsQuery;
import com.flabum.squidzbackend.reservation.domain.services.BarberServiceQueryService;
import com.flabum.squidzbackend.reservation.domain.services.ReservationQueryService;
import com.flabum.squidzbackend.reservation.interfaces.rest.resources.BarberServiceResource;
import com.flabum.squidzbackend.reservation.interfaces.rest.resources.ReservationResource;
import com.flabum.squidzbackend.reservation.interfaces.rest.transform.BarberServiceResourceFromEntityAssembler;
import com.flabum.squidzbackend.reservation.interfaces.rest.transform.ReservationResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/barberService")
@Tag(name = "Barber Services", description = "")
public class BarberServiceController {

    private final BarberServiceQueryService barberServiceQueryService;


    public BarberServiceController(BarberServiceQueryService barberServiceQueryService) {
        this.barberServiceQueryService = barberServiceQueryService;
    }

    @GetMapping
    public ResponseEntity<List<BarberServiceResource>> getAllBarberServices() {
        var getAllBarberServicesQuery = new GetAllBarberServicesQuery();
        var barberServices = barberServiceQueryService.handle(getAllBarberServicesQuery);
        var barberServiceResources = barberServices.stream().map(BarberServiceResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(barberServiceResources);
    }


}
