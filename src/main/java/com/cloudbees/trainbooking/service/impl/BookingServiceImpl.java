package com.cloudbees.trainbooking.service.impl;

import com.cloudbees.trainbooking.entity.*;
import com.cloudbees.trainbooking.exception.BadRequestException;
import com.cloudbees.trainbooking.exception.CustomException;
import com.cloudbees.trainbooking.exception.DuplicateException;
import com.cloudbees.trainbooking.exception.NotFoundException;
import com.cloudbees.trainbooking.model.booking.CreateBookingRequest;
import com.cloudbees.trainbooking.model.booking.UpdateBookingRequest;
import com.cloudbees.trainbooking.repository.*;
import com.cloudbees.trainbooking.service.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository repository;
    private final ScheduleService scheduleService;
    private final SectionService sectionService;
    private final BookingViewService bookingViewService;

    public BookingServiceImpl(BookingRepository repository, ScheduleService scheduleService, SectionService sectionService, BookingViewService bookingViewService) {
        this.repository = repository;
        this.scheduleService = scheduleService;
        this.sectionService = sectionService;
        this.bookingViewService = bookingViewService;
    }

    @Override
    public BookingView getView(int bookingId) throws NotFoundException {
        return bookingViewService.get(bookingId);
    }
    @Override
    public BookingView create(CreateBookingRequest request) throws CustomException {

        var section = sectionService.get(request.getSectionId());
        validateSeat(request.getScheduleId(), request, section);

        var schedule = scheduleService.get(request.getScheduleId());
        if (schedule.getAvailableSeats() == 0) {
            throw new BadRequestException("All seats are full");
        }

        schedule.setAvailableSeats(schedule.getAvailableSeats() - 1);
        scheduleService.update(schedule);

        var booking = Booking.builder()
                .userId(request.getUserId())
                .scheduleId(request.getScheduleId())
                .sectionId(request.getSectionId())
                .seatNumber(request.getSeatNumber())
                .build();
        booking = create(booking);

        return bookingViewService.get(booking.getId());
    }
    @Override
    public BookingView update(int id, UpdateBookingRequest request) throws CustomException {

        var booking = get(id);
        var section = sectionService.get(request.getSectionId());

        validateSeat(booking.getScheduleId(), request, section);

        booking.setSectionId(request.getSectionId());
        booking.setSeatNumber(request.getSeatNumber());
        repository.save(booking);

        return bookingViewService.get(booking.getId());
    }

    private void validateSeat(int scheduleId, UpdateBookingRequest request, Section section) throws CustomException {

        var booking = repository.findByScheduleIdAndSectionIdAndSeatNumber(scheduleId, request.getSectionId(), request.getSeatNumber());
        if (booking.isPresent()) {
            var str = String.format("Requested seat '%s(%d)' already taken", section.getName(), request.getSeatNumber());
            throw new DuplicateException(str);
        }

        if (request.getSeatNumber() > section.getTotalSeats()) {
            var str = String.format("Requested seat number %s('%d') is invalid", section.getName(), request.getSeatNumber());
            throw new BadRequestException(str);
        }
    }

    @Override
    public void delete(int id) throws NotFoundException {

        var booking = get(id);
        var schedule = scheduleService.get(booking.getScheduleId());

        schedule.setAvailableSeats(schedule.getAvailableSeats() + 1);
        scheduleService.update(schedule);
        repository.delete(booking);
    }

    @Override
    public Booking create(Booking booking) {
        return repository.save(booking);
    }
    @Override
    public Booking get(int id) throws NotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("No booking found with id: " + id));
    }
    @Override
    public List<Booking> getAll() {
        return repository.findAll();
    }

}
