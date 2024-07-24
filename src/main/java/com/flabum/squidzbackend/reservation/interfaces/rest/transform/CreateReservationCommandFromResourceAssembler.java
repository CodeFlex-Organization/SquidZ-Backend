package com.flabum.squidzbackend.reservation.interfaces.rest.transform;

import com.flabum.squidzbackend.reservation.domain.model.commands.CreateReservationCommand;
import com.flabum.squidzbackend.reservation.interfaces.rest.resources.CreateReservationResource;

public class CreateReservationCommandFromResourceAssembler {
    public static CreateReservationCommand toCommandFromResource(CreateReservationResource resource) {
        return new CreateReservationCommand(resource.date(), resource.time());
    }
}
