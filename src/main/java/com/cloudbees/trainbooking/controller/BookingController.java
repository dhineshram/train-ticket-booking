package com.cloudbees.trainbooking.controller;

import com.cloudbees.trainbooking.entity.Booking;
import com.cloudbees.trainbooking.entity.BookingView;
import com.cloudbees.trainbooking.exception.CustomException;
import com.cloudbees.trainbooking.exception.NotFoundException;
import com.cloudbees.trainbooking.model.booking.CreateBookingRequest;
import com.cloudbees.trainbooking.model.booking.UpdateBookingRequest;
import com.cloudbees.trainbooking.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookingController {

    private final BookingService service;

    public BookingController(BookingService service) {
        this.service = service;
    }

    @GetMapping("/bookings/{id}")
    @Operation(summary = "API for - #6, #3",
            description = "6. An API that shows the details of the receipt for the user \n" +
                    "3. Details included in the receipt are (From, To, User, price paid)")
    public ResponseEntity<BookingView> getBooking(@PathVariable int id) throws NotFoundException {
        var bookingView = service.getView(id);
        return new ResponseEntity<>(bookingView, HttpStatus.OK);
    }

    @PostMapping("/booking")
    @Operation(summary = "API for - #2",
            description = "2. Create API where you can submit a purchase for a ticket")
    public ResponseEntity<BookingView> createBooking(@RequestBody CreateBookingRequest request) throws CustomException {
        var bookingView = service.create(request);
        return new ResponseEntity<>(bookingView, HttpStatus.CREATED);
    }

    @PatchMapping("/bookings/{id}")
    @Operation(summary = "API for - #9",
            description = "9. An API to modify a user's seat")
    public ResponseEntity<BookingView> updateBooking(@PathVariable int id, @RequestBody UpdateBookingRequest request) throws CustomException {
        var bookingView = service.update(id, request);
        return new ResponseEntity<>(bookingView, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/bookings/{id}")
    @Operation(summary = "API for - #8",
            description = "8. An API to remove a user from the train")
    public ResponseEntity<?> deleteBooking(@PathVariable int id) throws NotFoundException {
        service.delete(id);
        return ResponseEntity.accepted().build();
    }

}
