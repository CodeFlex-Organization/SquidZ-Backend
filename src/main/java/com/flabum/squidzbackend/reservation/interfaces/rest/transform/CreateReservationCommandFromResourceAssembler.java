package com.flabum.squidzbackend.reservation.interfaces.rest.transform;

import com.flabum.squidzbackend.iam.interfaces.acl.user.UserContextFacade;
import com.flabum.squidzbackend.reservation.domain.model.commands.CreateReservationCommand;
import com.flabum.squidzbackend.reservation.infrastructure.persistence.jpa.repositories.BarberServiceRepository;
import com.flabum.squidzbackend.reservation.infrastructure.persistence.jpa.repositories.LocalRepository;
import com.flabum.squidzbackend.reservation.interfaces.rest.resources.CreateReservationResource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CreateReservationCommandFromResourceAssembler {
    private final UserContextFacade userContextFacade;

    private final LocalRepository localRepository;

    private final BarberServiceRepository barberServiceRepository;

    public CreateReservationCommand toCommandFromResource(
            CreateReservationResource resource, HttpServletRequest request) {
        var user = userContextFacade.getUser(request);
        return new CreateReservationCommand(
                user.get(),
                localRepository.findById(resource.localId()).get(),
                resource.date(),
                resource.time(),
                barberServiceRepository.findById(resource.barberServiceId()).get());
    }
}
