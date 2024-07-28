package com.flabum.squidzbackend.reservation.application.internal.commandservices;

import com.flabum.squidzbackend.reservation.domain.model.aggregates.Reservation;
import com.flabum.squidzbackend.reservation.domain.model.commands.ConfirmReservationCommand;
import com.flabum.squidzbackend.reservation.domain.model.commands.CreateReservationCommand;
import com.flabum.squidzbackend.reservation.domain.model.commands.DeleteReservationCommand;
import com.flabum.squidzbackend.reservation.domain.model.entities.BarberService;
import com.flabum.squidzbackend.reservation.domain.services.ReservationCommandService;
import com.flabum.squidzbackend.reservation.infrastructure.persistence.jpa.repositories.BarberServiceRepository;
import com.flabum.squidzbackend.reservation.infrastructure.persistence.jpa.repositories.LocalRepository;
import com.flabum.squidzbackend.reservation.infrastructure.persistence.jpa.repositories.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReservationCommandServiceImpl implements ReservationCommandService {

    private final ReservationRepository reservationRepository;
    private final LocalRepository localRepository;
    private final BarberServiceRepository barberServiceRepository;

    @Override
    public Optional<Reservation> handle(CreateReservationCommand command) {
        var local = localRepository.findById(command.local().getId());

        if (local.isEmpty()){
            throw new RuntimeException("Local not exist");
        }

        var barberService = local.get().getBarberServices().stream()
                .filter(aux -> Objects.equals(aux.getId(), command.barberService().getId()))
                .findFirst();

        if (barberService.isEmpty()){
            throw new RuntimeException("BarberService not exist");
        }

        var reservation = new Reservation(
                command.user(),
                local.get(),
                command.date(),
                command.time(),
                barberService.get()
        );
        reservationRepository.save(reservation);
        return Optional.of(reservation);
    }

    @Override
    public void handle(DeleteReservationCommand command) {
        if (!reservationRepository.existsById(command.reservationId())) {
            throw new IllegalArgumentException("Reservation does not exist");
        }
        reservationRepository.deleteById(command.reservationId());

    }

    @Override
    public void handle(ConfirmReservationCommand command) {
    }
}
