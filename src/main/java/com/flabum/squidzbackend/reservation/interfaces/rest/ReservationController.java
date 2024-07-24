package com.flabum.squidzbackend.reservation.interfaces.rest;


import com.flabum.squidzbackend.reservation.domain.model.aggregates.Reservation;
import com.flabum.squidzbackend.reservation.domain.model.commands.ConfirmReservationCommand;
import com.flabum.squidzbackend.reservation.domain.model.queries.GetReservationByIdQuery;
import com.flabum.squidzbackend.reservation.domain.services.ReservationCommandService;
import com.flabum.squidzbackend.reservation.domain.services.ReservationQueryService;
import com.flabum.squidzbackend.reservation.interfaces.rest.resources.CreateReservationResource;
import com.flabum.squidzbackend.reservation.interfaces.rest.resources.ReservationResource;
import com.flabum.squidzbackend.reservation.interfaces.rest.transform.CreateReservationCommandFromResourceAssembler;
import com.flabum.squidzbackend.reservation.interfaces.rest.transform.ReservationResourceFromEntityAssembler;
import com.flabum.squidzbackend.shared.interfaces.rest.resources.MessageResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/reservation")
@Tag(name = "Reservations", description = "")
public class ReservationController {
    private final ReservationCommandService reservationCommandService;

    private final ReservationQueryService reservationQueryService;


    public ReservationController(ReservationCommandService reservationCommandService,
                                 ReservationQueryService reservationQueryService) {
        this.reservationCommandService = reservationCommandService;
        this.reservationQueryService= reservationQueryService;
    }

    @PostMapping("/{reservationId}")
    public ResponseEntity<ReservationResource> createReservation(@RequestBody CreateReservationResource createReservationResource) {
        var createReservationCommand = CreateReservationCommandFromResourceAssembler.toCommandFromResource(createReservationResource);
        var reservationId = reservationCommandService.handle(createReservationCommand);
        if (reservationId==0L) {
            return ResponseEntity.badRequest().build();
        }
        var getReservationByIdQuery = new GetReservationByIdQuery(reservationId);
        var reservation = reservationQueryService.handle(getReservationByIdQuery);
        if (reservation.isEmpty()) return ResponseEntity.badRequest().build();
        var reservationResource = ReservationResourceFromEntityAssembler.toResourceFromEntity(reservation.get());
        return new ResponseEntity<>(reservationResource, HttpStatus.CREATED);
    }

//    @GetMapping
//    public ResponseEntity<List<ReservationResource>> getAllReservations() {
//        var getAllCoursesQuery = new GetAllCoursesQuery();
//        var courses = courseQueryService.handle(getAllCoursesQuery);
//        var courseResources = courses.stream().map(CourseResourceFromEntityAssembler::toResourceFromEntity).toList();
//        return ResponseEntity.ok(courseResources);
//    }

//    @PostMapping("/{reservationId}/confirmations")
//    public ResponseEntity<MessageResource> confirmReservation(@PathVariable Long reservationId) {
//        var confirmReservartionCommand = new ConfirmReservationCommand(reservationId);
//        reservationCommandService.handle(confirmReservartionCommand);
//        return ResponseEntity.ok(new MessageResource("Confirmed Reservation ID: " + reservationId));
//    }

//    @DeleteMapping("/{courseId}")
//    public ResponseEntity<?> deleteCourse(@PathVariable Long courseId) {
//        var deleteCourseCommand = new DeleteCourseCommand(courseId);
//        courseCommandService.handle(deleteCourseCommand);
//        return ResponseEntity.ok("Course with given id successfully deleted");
//    }
}
